package pme.ai.fhe.de.studybuddy.model.Daos;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pme.ai.fhe.de.studybuddy.Modules;
import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.Module;
import pme.ai.fhe.de.studybuddy.model.University;
import pme.ai.fhe.de.studybuddy.model.UserData;
import pme.ai.fhe.de.studybuddy.utilities.GenericAsyncTask;

public class DataController {

    private CourseOfStudiesDao courseOfStudiesDao;
    private CityDao cityDao;
    private UniversityDao universityDao;
    private UserDataDao userDataDao;
    private ModuleDao moduleDao;

    private static DataController INSTANCE;


    private DataController(Application application) {
        DataBase db = DataBase.getDatabase(application);
        courseOfStudiesDao = db.getCourseOfStudiesDao();
        cityDao = db.getCityDao();
        universityDao = db.getUniversityDao();
        userDataDao = db.getUserDataDao();
        moduleDao = db.getModuleDao();
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
        GenericAsyncTask asyncHandler = new GenericAsyncTask(cityDao, universityDao, courseOfStudiesDao, moduleDao);
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
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                Log.i("Thread sleep", e.toString());
            }
            asyncHandler.insertModules(generateModules());
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
        CourseOfStudies course = new CourseOfStudies("Angewandte Informatik", 6, 180, "Gebäudetechnik und Informatik", universityDao.getUniversityIdByName("FH Erfurt"));
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

    private Lecture[] generateLectures() {
        Lecture[] allLectures = new Lecture[2];
        Lecture lecture = new Lecture(1, 1, "Mathematik 1", 1, true, 1, "deutsch");
        allLectures[0] = lecture;
        lecture = new Lecture(1, 1, "Mathematik 1", 1, true, 1, "deutsch");
        allLectures[1] = lecture;


        return allLectures;
    }

    private Module[] generateModules() {
        Module[] allmodules = new Module[28];
        Module module = new Module(1,"Mathematik", 14, "1010");
        allmodules[0] = module;
        module = new Module(1,"Theoretische Informatik", 10, "1020");
        allmodules[1] = module;
        module = new Module(1,"Technische Informatik", 6, "1030");
        allmodules[2] = module;
        module = new Module(1,"Programmierung", 17, "1040");
        allmodules[3] = module;
        module = new Module(1,"Multimedia", 2, "1050");
        allmodules[4] = module;
        module = new Module(1,"Betriebswirtschaftslehre", 2, "1060");
        allmodules[5] = module;
        module = new Module(1,"Englisch", 2, "1070");
        allmodules[6] = module;
        module = new Module(1,"Betriebssysteme", 6, "1080");
        allmodules[7] = module;
        module = new Module(1,"Datenbanken", 7, "1090");
        allmodules[8] = module;
        module = new Module(1,"Softwaretechnik", 8, "1110");
        allmodules[9] = module;
        module = new Module(1,"Netze", 6, "1120");
        allmodules[10] = module;
        module = new Module(1,"Grafische Datenverarbeitung", 4, "1130");
        allmodules[11] = module;
        module = new Module(1,"IT-Kolloquium", 2, "1140");
        allmodules[12] = module;
        module = new Module(1,"IT-Sicherheit", 2, "1150");
        allmodules[13] = module;
        module = new Module(1,"IT-Recht", 2, "1160");
        allmodules[14] = module;
        module = new Module(1,"Praxisprojekt", 4, "1170");
        allmodules[15] = module;
        module = new Module(1,"Praxismodul", 22, "1180");
        allmodules[16] = module;
        module = new Module(1,"Bachelorarbeit", 10, "1190");
        allmodules[17] = module;
        module = new Module(1,"Medientechnik", 2, "2010");
        allmodules[18] = module;
        module = new Module(1,"Digitale Medien", 22, "2020");
        allmodules[19] = module;
        module = new Module(1,"Multimediaproduktion", 4, "2030");
        allmodules[20] = module;
        module = new Module(1,"Medienrecht", 4, "2040");
        allmodules[21] = module;
        module = new Module(1,"Existenzgründung", 2, "5280");
        allmodules[22] = module;
        module = new Module(1,"Zivilrecht", 4, "5270");
        allmodules[23] = module;
        module = new Module(1,"Marketing", 4, "5260");
        allmodules[24] = module;
        module = new Module(1,"Operative Anwendungssysteme", 4, "5250");
        allmodules[25] = module;
        module = new Module(1,"Programmierung Mobiler Endgeräte", 4, "5240");
        allmodules[26] = module;
        module = new Module(1,"Dynamische Webprogrammierung", 4, "5230");
        allmodules[27] = module;

        return allmodules;
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

    public String getCityById(int cityId) {
        return cityDao.getCityNameById(cityId);
    }

    public String getUniversityById(int universityId) {
        return universityDao.getUniversitiyById(universityId);
    }

    public String getCourseById(int courseID) {
        return courseOfStudiesDao.getCourseById(courseID);
    }
}