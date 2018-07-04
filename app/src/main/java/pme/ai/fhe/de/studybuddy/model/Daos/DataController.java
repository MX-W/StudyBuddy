package pme.ai.fhe.de.studybuddy.model.Daos;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.model.University;
import pme.ai.fhe.de.studybuddy.model.UserData;
import pme.ai.fhe.de.studybuddy.utilities.GenericAsyncTask;

public class DataController {

    private CourseOfStudiesDao courseOfStudiesDao;
    private CityDao cityDao;
    private UniversityDao universityDao;
    private UserDataDao userDataDao;

    private static DataController INSTANCE;


    private DataController(Application application) {
        DataBase db = DataBase.getDatabase(application);
        courseOfStudiesDao = db.getCourseOfStudiesDao();
        cityDao = db.getCityDao();
        universityDao = db.getUniversityDao();
        userDataDao = db.getUserDataDao();
        generateData();
    }

    public static DataController getInstance(Application application) {
        if(INSTANCE == null) {
            INSTANCE = new DataController(application);
        }
        return INSTANCE;
    }

    public void insertUserData(UserData data) {
        userDataDao.insert(data);
    }

    public UserData getUserData() {
        return userDataDao.getUserData();
    }

    public List<City> getAllCities() {
        return cityDao.getAll();
    }

    private void generateData() {
        GenericAsyncTask asyncHandler = new GenericAsyncTask(cityDao, universityDao, courseOfStudiesDao);
        if(!"Erfurt".equals(cityDao.getCityNameById(1))) {
            asyncHandler.insertCities(generateCities());
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                Log.i("Thread sleep", e.toString());
            }
            asyncHandler.insertUniversities(generateUniversities());
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                Log.i("Thread sleep", e.toString());
            }
            asyncHandler.insertCourses(generateCourseOfStudies());
        }

    }

    private City[] generateCities() {
        City[] allCities = new City[3];
        City city = new City("Erfurt");
        allCities[0] = city;
        city = new City("Jena");
        allCities[1] = city;
        city = new City("Weimar");
        allCities[2] = city;
        return allCities;
    }

    private University[] generateUniversities() {
        University[] allUniversities = new University[4];
        University university = new University("FH Erfurt", cityDao.getCityIdByName("Erfurt"));
        allUniversities[0] = university;
        university = new University("Universität Erfurt", cityDao.getCityIdByName("Erfurt"));
        allUniversities[1] = university;
        university = new University("FH Jena", cityDao.getCityIdByName("Jena"));
        allUniversities[2] = university;
        university = new University("Bauhausuniversität Weimar", cityDao.getCityIdByName("Weimar"));
        allUniversities[3] = university;
        return allUniversities;
    }

    private CourseOfStudies[] generateCourseOfStudies() {
        CourseOfStudies[] allCourses = new CourseOfStudies[5];
        CourseOfStudies course = new CourseOfStudies("Angewandte Informatik", 7, 210, "Gebäudetechnik und Informatik", universityDao.getUniversityIdByName("FH Erfurt"));
        allCourses[0] = course;
        course = new CourseOfStudies("Architektur", 6, 180, "Architektur und Stadtplanung", universityDao.getUniversityIdByName("FH Erfurt"));
        allCourses[1] = course;
        course = new CourseOfStudies("Soziale Arbeit", 6, 180, "Angewandte Sozialwissenschaften", universityDao.getUniversityIdByName("FH Erfurt"));
        allCourses[2] = course;
        course = new CourseOfStudies("Medieninformatik", 6, 180, "Medien", universityDao.getUniversityIdByName("Bauhausuniversität Weimar"));
        allCourses[3] = course;
        course = new CourseOfStudies("Laser- und Optotechnologien", 6, 180, "Elektrotechnik und Informationstechnik", universityDao.getUniversityIdByName("FH Jena"));
        allCourses[4] = course;

        return allCourses;
    }

    public List<CourseOfStudies> getAllCourses() {
        return courseOfStudiesDao.getAll();
    }

    public int getCityIdByName(String name) { return cityDao.getCityIdByName(name); }

    public int getUniversityIdByName(String name) {
        return universityDao.getUniversityIdByName(name);
    }

    public int getCourseIdByName(String name) {
        return courseOfStudiesDao.getCourseIdByName(name);
    }

    public List<String> getUniversitiesByCityId(int cityId) {
        return universityDao.getUniversitiesByCityId(cityId);
    }

    public List<String> getCoursesByUniversityId(int universityId) {
        return courseOfStudiesDao.getCoursesByUniversityId(universityId);
    }
}