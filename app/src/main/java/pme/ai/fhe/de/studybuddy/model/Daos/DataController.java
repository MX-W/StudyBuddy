package pme.ai.fhe.de.studybuddy.model.Daos;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;

public class DataController {

    private CourseOfStudiesDao courseOfStudiesDao;
    private CityDao cityDao;
    private UniversityDao universityDao;

    private static DataController INSTANCE;


    public DataController(Application application) {
        DataBase db = DataBase.getDatabase(application);
        courseOfStudiesDao = db.getCourseOfStudiesDao();
        cityDao = db.getCityDao();
        universityDao = db.getUniversityDao();
        generateData();
    }

    public static DataController getInstance(Application application) {
        if(INSTANCE == null) {
            INSTANCE = new DataController(application);
        }
        return INSTANCE;
    }

    public List<City> getAllCities() {
        return cityDao.getAll();
    }

    private void generateData() {
        new insertCities(cityDao).execute(generateCities());

    }

    private static City[] generateCities() {
        City[] allCities = new City[3];
        City city = new City("Erfurt");
        allCities[0] = city;
        city = new City("Jena");
        allCities[1] = city;
        city = new City("Weimar");
        allCities[2] = city;
        return allCities;
    }

    public List<CourseOfStudies> getAllCourses() {
        return courseOfStudiesDao.getAll();
    }

    private static class insertCities extends AsyncTask<City, Void, Void> {

        private CityDao mAsyncTaskDao;

        insertCities (CityDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final City[] params) {
            for(int i = 0; i < params.length; i++) {
                mAsyncTaskDao.insert(params[i]);
            }

            return null;
        }
    }
}


