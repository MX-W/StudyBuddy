package pme.ai.fhe.de.studybuddy.utilities;

import android.os.AsyncTask;

import pme.ai.fhe.de.studybuddy.Modules;
import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.model.Daos.CityDao;
import pme.ai.fhe.de.studybuddy.model.Daos.CourseOfStudiesDao;
import pme.ai.fhe.de.studybuddy.model.Daos.ModuleDao;
import pme.ai.fhe.de.studybuddy.model.Daos.UniversityDao;
import pme.ai.fhe.de.studybuddy.model.Module;
import pme.ai.fhe.de.studybuddy.model.University;

public class GenericAsyncTask {

    private CityDao cityDao;
    private UniversityDao universityDao;
    private CourseOfStudiesDao courseOfStudiesDao;
    private ModuleDao moduleDao;

    public GenericAsyncTask(CityDao cityDao, UniversityDao universityDao, CourseOfStudiesDao courseOfStudiesDao, ModuleDao moduleDao) {
        this.cityDao = cityDao;
        this.universityDao = universityDao;
        this.courseOfStudiesDao = courseOfStudiesDao;
        this.moduleDao = moduleDao;
    }

    private static class insertCities extends AsyncTask<City, Void, Void> {

        private CityDao mAsyncTaskDao;

        insertCities (CityDao cityDao) {
            mAsyncTaskDao = cityDao;
        }

        @Override
        protected Void doInBackground(final City[] params) {
            for(int i = 0; i < params.length; i++) {
                //Log.i("inserting ", params[i].getName());
                mAsyncTaskDao.insert(params[i]);
            }

            return null;
        }
    }

    public void insertCities(City[] cities) {
        new insertCities(this.cityDao).execute(cities);
    }

    private static class insertUniversities extends AsyncTask<University, Void, Void> {

        private UniversityDao mAsyncTaskDao;

        insertUniversities (UniversityDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final University[] params) {
            for(int i = 0; i < params.length; i++) {
                //Log.i("inserting ", params[i].getName());
                mAsyncTaskDao.insert(params[i]);
            }

            return null;
        }
    }

    public void insertUniversities(University[] universities) {
        new insertUniversities(this.universityDao).execute(universities);
    }

    private static class insertCourse extends AsyncTask<CourseOfStudies, Void, Void> {

        private CourseOfStudiesDao mAsyncTaskDao;

        insertCourse (CourseOfStudiesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CourseOfStudies[] params) {
            for(int i = 0; i < params.length; i++) {
                //Log.i("inserting ", params[i].getName());
                mAsyncTaskDao.insert(params[i]);
            }
            return null;
        }
    }

    public void insertCourses(CourseOfStudies[] courseOfStudies) {
        new insertCourse(this.courseOfStudiesDao).execute(courseOfStudies);
    }

    private static class insertModules extends AsyncTask<Module, Void, Void> {

        private ModuleDao mAsyncTaskDao;

        insertModules (ModuleDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Module[] params) {
            for(int i = 0; i < params.length; i++) {
                //Log.i("inserting ", params[i].getName());
                mAsyncTaskDao.insert(params[i]);
            }
            return null;
        }
    }

    public void insertModules(Module[] modules) {
        new insertModules(this.moduleDao).execute(modules);
    }
}


