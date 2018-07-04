package pme.ai.fhe.de.studybuddy.utilities;

import android.os.AsyncTask;

import pme.ai.fhe.de.studybuddy.model.Category;
import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.model.Daos.CategoryDao;
import pme.ai.fhe.de.studybuddy.model.Daos.CityDao;
import pme.ai.fhe.de.studybuddy.model.Daos.CourseOfStudiesDao;
import pme.ai.fhe.de.studybuddy.model.Daos.LectureDao;
import pme.ai.fhe.de.studybuddy.model.Daos.UniversityDao;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.University;

public class GenericAsyncTask {

    private CityDao cityDao;
    private UniversityDao universityDao;
    private CourseOfStudiesDao courseOfStudiesDao;
    private LectureDao lectureDao;
    private CategoryDao categoryDao;

    public GenericAsyncTask(CityDao cityDao, UniversityDao universityDao, CourseOfStudiesDao courseOfStudiesDao, LectureDao lectureDao, CategoryDao categoryDao) {
        this.cityDao = cityDao;
        this.universityDao = universityDao;
        this.courseOfStudiesDao = courseOfStudiesDao;
        this.lectureDao = lectureDao;
        this.categoryDao = categoryDao;
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

    private static class insertCategories extends AsyncTask<Category, Void, Void> {

        private CategoryDao mAsyncTaskDao;

        insertCategories(CategoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Category[] params) {
            for(int i = 0; i < params.length; i++) {
                //Log.i("inserting ", params[i].getName());
                mAsyncTaskDao.insert(params[i]);
            }
            return null;
        }
    }

    public void insertCategories(Category[] categories) {
        new insertCategories(this.categoryDao).execute(categories);
    }

    private static class insertLectures extends AsyncTask<Lecture, Void, Void> {

        private LectureDao mAsyncTaskDao;

        insertLectures(LectureDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Lecture[] params) {
            for(int i = 0; i < params.length; i++) {
                //Log.i("inserting ", params[i].getName());
                mAsyncTaskDao.insert(params[i]);
            }
            return null;
        }
    }

    public void insertLectures(Lecture[] lectures) {
        new insertLectures(this.lectureDao).execute(lectures);
    }
}


