package pme.ai.fhe.de.studybuddy.utilities;

import android.os.AsyncTask;
import android.util.Log;

import pme.ai.fhe.de.studybuddy.administration.daos.CategoryDao;
import pme.ai.fhe.de.studybuddy.administration.daos.LectureDao;
import pme.ai.fhe.de.studybuddy.administration.daos.ModuleDao;
import pme.ai.fhe.de.studybuddy.administration.daos.SemesterDao;
import pme.ai.fhe.de.studybuddy.model.Category;
import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.administration.daos.CityDao;
import pme.ai.fhe.de.studybuddy.administration.daos.CourseOfStudiesDao;
import pme.ai.fhe.de.studybuddy.administration.daos.UniversityDao;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.Module;
import pme.ai.fhe.de.studybuddy.model.Semester;
import pme.ai.fhe.de.studybuddy.model.University;

public class GenericAsyncTask {

    private CityDao cityDao;
    private UniversityDao universityDao;
    private CourseOfStudiesDao courseOfStudiesDao;
    private ModuleDao moduleDao;
    private CategoryDao categoryDao;
    private LectureDao lectureDao;
    private SemesterDao semesterDao;

    public GenericAsyncTask(CityDao cityDao, UniversityDao universityDao, CourseOfStudiesDao courseOfStudiesDao, ModuleDao moduleDao, CategoryDao categoryDao, LectureDao lectureDao, SemesterDao semesterDao) {
        this.cityDao = cityDao;
        this.universityDao = universityDao;
        this.courseOfStudiesDao = courseOfStudiesDao;
        this.moduleDao = moduleDao;
        this.categoryDao = categoryDao;
        this.lectureDao = lectureDao;
        this.semesterDao = semesterDao;
    }

    private static class insertCities extends AsyncTask<City, Void, Void> {

        private CityDao mAsyncTaskDao;

        insertCities (CityDao cityDao) {
            mAsyncTaskDao = cityDao;
        }

        @Override
        protected Void doInBackground(final City[] params) {
            for(int i = 0; i < params.length; i++) {
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

        insertModules(ModuleDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Module[] params) {
            for(int i = 0; i < params.length; i++) {
                mAsyncTaskDao.insert(params[i]);
            }
            return null;
        }
    }

    public void insertModules(Module[] modules) {
        new insertModules(this.moduleDao).execute(modules);
    }

    private static class insertCategories extends AsyncTask<Category, Void, Void> {

        private CategoryDao mAsyncTaskDao;

        insertCategories(CategoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Category[] params) {
            for(int i = 0; i < params.length; i++) {
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
                mAsyncTaskDao.insert(params[i]);
            }
            return null;
        }
    }

    public void insertLectures(Lecture[] lectures) {
        new insertLectures(this.lectureDao).execute(lectures);
    }

    private static class insertSemester extends AsyncTask<Semester, Void, Void> {

        private SemesterDao mAsyncTaskDao;

        insertSemester(SemesterDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Semester[] params) {
            for(int i = 0; i < params.length; i++) {
                mAsyncTaskDao.insert(params[i]);
            }
            return null;
        }
    }

    public void insertSemester(Semester[] semesters) {
        new insertSemester(this.semesterDao).execute(semesters);
    }
}


