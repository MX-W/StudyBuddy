package pme.ai.fhe.de.studybuddy.administration;

import android.app.Application;
import android.util.Log;

import java.util.List;

import pme.ai.fhe.de.studybuddy.administration.daos.CategoryDao;
import pme.ai.fhe.de.studybuddy.administration.daos.LectureDao;
import pme.ai.fhe.de.studybuddy.administration.daos.ModuleDao;
import pme.ai.fhe.de.studybuddy.administration.daos.SemesterDao;
import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.administration.daos.CityDao;
import pme.ai.fhe.de.studybuddy.administration.daos.CourseOfStudiesDao;
import pme.ai.fhe.de.studybuddy.administration.daos.UniversityDao;
import pme.ai.fhe.de.studybuddy.administration.daos.UserDataDao;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.Module;
import pme.ai.fhe.de.studybuddy.model.Semester;
import pme.ai.fhe.de.studybuddy.model.UserData;
import pme.ai.fhe.de.studybuddy.utilities.DataProvider;
import pme.ai.fhe.de.studybuddy.utilities.GenericAsyncTask;

/**
 * The DataController class handles all the accesses to the database. It creates the database itself
 * and inserts all data if the database is empty.
 * It also holds a reference to all daos through which the database actions are processed.
 */
public class DataController {

    private CourseOfStudiesDao courseOfStudiesDao;
    private CityDao cityDao;
    private UniversityDao universityDao;
    private UserDataDao userDataDao;
    private ModuleDao moduleDao;
    private CategoryDao categoryDao;
    private LectureDao lectureDao;
    private SemesterDao semesterDao;

    private static DataController INSTANCE;


    /**
     * Private constructor because the DataController follows the singleton principle.
     * @param application the application context in which the database should be initialised
     */
    private DataController(Application application) {
        DataBase db = DataBase.getDatabase(application);
        courseOfStudiesDao = db.getCourseOfStudiesDao();
        cityDao = db.getCityDao();
        universityDao = db.getUniversityDao();
        userDataDao = db.getUserDataDao();
        moduleDao = db.getModuleDao();
        lectureDao = db.getLectureDao();
        categoryDao = db.getCategoryDao();
        semesterDao = db.getSemesterDao();
        generateData();
    }

    /**
     * Creates an instance of the DataController if there is none existent right now and otherwise
     * returns always the same instance. Follows the singleton principle.
     * @param application the application context in which the database should be initialised
     * @return DataController instance
     */
    public static DataController getInstance(Application application) {
        if(INSTANCE == null) {
            INSTANCE = new DataController(application);
        }
        return INSTANCE;
    }

    /**
     * Generates the semester, categories, course of studies, cities, lectures, modules and
     * universities.
     * They are all inserted in async tasks because the app would crash if they were inserted
     * in the main thread. Therefore the asyncHandler exists. For all foreign keys to function,
     * there is a little bit of a delay between the insertion of each model type.
     * The DataProvider supplies the needed data.
     */
    private void generateData() {
        DataProvider provider = new DataProvider();
        GenericAsyncTask asyncHandler = new GenericAsyncTask(cityDao, universityDao, courseOfStudiesDao, moduleDao, categoryDao, lectureDao, semesterDao);
        if(!"Erfurt".equals(cityDao.getCityNameById(1))) {
            asyncHandler.insertSemester(provider.generateSemester());
            asyncHandler.insertCities(provider.generateCities());
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                Log.i("Thread sleep", e.toString());
            }
            asyncHandler.insertUniversities(provider.generateUniversities(cityDao));
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                Log.i("Thread sleep", e.toString());
            }
            asyncHandler.insertCourses(provider.generateCourseOfStudies(universityDao));
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                Log.i("Thread sleep", e.toString());
            }
            asyncHandler.insertModules(provider.generateModules());
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                Log.i("Thread sleep", e.toString());
            }
            asyncHandler.insertCategories(provider.generateCategories());
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                Log.i("Thread sleep", e.toString());
            }
            asyncHandler.insertLectures(provider.generateLectures());
        }

    }

    /**
     * Insertion of UserData
     * @param data UserData to be inserted
     */
    public void insertUserData(UserData data) {
        userDataDao.insert(data);
    }

    /**
     * Returns the UserData
     * @return UserData of the current user
     */
    public UserData getUserData() {
        return userDataDao.getUserData();
    }

    /**
     * Update of UserData
     * @param userData UserData to be updated
     */
    public void updateUserData(UserData userData) {
        userDataDao.update(userData);
    }

    /**
     *
     * @return An ArrayList of all cities
     */
    public List<City> getAllCities() {
        return cityDao.getAll();
    }

    /**
     *
     * @return An ArrayList of all courses
     */
    public List<CourseOfStudies> getAllCourses() {
        return courseOfStudiesDao.getAll();
    }

    /**
     * Get a specific city id by its name
     * @param name city name
     * @return the city id adequate to the city name
     */
    public int getCityIdByName(String name) { return cityDao.getCityIdByName(name); }

    /**
     * Get a specific university id by its name
     * @param name university name
     * @return the university id adequate to the university name
     */
    public int getUniversityIdByName(String name) {
        return universityDao.getUniversityIdByName(name);
    }

    /**
     * Get a specific course id by its name
     * @param name course name
     * @return the course id adequate to the course name
     */
    public int getCourseIdByName(String name) {
        return courseOfStudiesDao.getCourseIdByName(name);
    }

    /**
     * Get all universities by a city id
     * @param cityId city id
     * @return An ArrayList of universities adequate to the city id
     */
    public List<String> getUniversitiesByCityId(int cityId) {
        return universityDao.getUniversitiesByCityId(cityId);
    }

    /**
     * Get all lectures by a course id
     * @param courseId course id
     * @return An ArrayList of lectures adequate to the course id
     */
    public List<Lecture> getLecturesByCourseId(int courseId) {
        return lectureDao.getLecturesByCourseId(courseId);
    }

    /**
     * Get all lectures with a grade
     * @param courseId course id
     * @return An ArrayList of all lectures with a grade
     */
    public List<Lecture> getAllLecturesWithGrade(int courseId) {
        return lectureDao.getAllLecturesWithGrade(courseId);
    }

    /**
     * Get all lectures with grade orderd by the semester passed
     * @param courseId course id
     * @return An ArrayList of all lectures with grades orderd by semester passed
     */
    public List<Lecture> getAllLecturesWithGradeOrderBySemester(int courseId) {
        return lectureDao.getAllLecturesWithGradeOrderBySemester(courseId);
    }

    /**
     * Get all courses by university id
     * @param universityId university id
     * @return An ArrayList of all courses adequate the university id
     */
    public List<String> getCoursesByUniversityId(int universityId) {
        return courseOfStudiesDao.getCoursesByUniversityId(universityId);
    }

    /**
     *
     * @param cityId
     * @return city name adequate to the id
     */
    public String getCityById(int cityId) {
        return cityDao.getCityNameById(cityId);
    }

    /**
     *
     * @param universityId
     * @return university name adequate to the university id
     */
    public String getUniversityById(int universityId) {
        return universityDao.getUniversitiyById(universityId);
    }

    /**
     *
     * @param courseId
     * @return course name adequate to the course name
     */
    public String getCourseById(int courseId) {
        return courseOfStudiesDao.getCourseById(courseId);
    }

    /**
     * Updates the given lecture
     * @param updated lecture to update
     */
    public void updateLecture(Lecture updated) {
        lectureDao.update(updated);
    }

    /**
     *
     * @return the quantity of all categories
     */
    public int getNumberOfCategories()
    {
        return categoryDao.getNumberOfAll();
    }

    /**
     *
     * @param categoryId
     * @return category name adequate to the category id
     */
    public String getCategoryNameById(int categoryId)
    {
        return categoryDao.getNameByID(categoryId);
    }

    /**
     *
     * @return An array of all semester
     */
    public List<Semester> getAllSemester() {
        return semesterDao.getAllSemester();
    }

    /**
     *
     * @param name semester name
     * @return semester id adequate to the semester name
     */
    public int getSemesterIdByName(String name) {
        return semesterDao.getSemesterIdByName(name);
    }

    /**
     *
     * @param semesterId
     * @return semester name adequate to the semester id
     */
    public String getSemesterById(int semesterId) {
        return semesterDao.getSemesterById(semesterId);
    }

    /**
     *
     * @param moduleId
     * @return An ArrayList of lectures adequate to the module id
     */
    public List<Lecture> getLectureByModuleId(int moduleId) {
        return lectureDao.getLectureByModuleId(moduleId);
    }

    /**
     *
     * @param courseId
     * @return an ArrayList of modules adequate to the course id
     */
    public List<Module> getModulesByCourseId(int courseId) {
        return moduleDao.getModulesByCourseId(courseId);
    }

    /**
     * resets the grades of all lectures
     */
    public void resetAllGrades() {
        lectureDao.resetAllGrades();
    }

    /**
     * deletes the UserData at a certain id
     * @param userId
     */
    public void deleteUserData(int userId) {
        userDataDao.deleteUserData(userId);
    }
}

