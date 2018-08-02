package pme.ai.fhe.de.studybuddy.utilities;

import android.os.AsyncTask;

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


/**
 * This class provides async tasks for the insertion of every model type.
 * An insertion of many items at a time in the main thread causes the app to crash. So every
 * insertion is handled through an extra thread.
 */
public class GenericAsyncTask {

    private CityDao cityDao;
    private UniversityDao universityDao;
    private CourseOfStudiesDao courseOfStudiesDao;
    private ModuleDao moduleDao;
    private CategoryDao categoryDao;
    private LectureDao lectureDao;
    private SemesterDao semesterDao;

    /**
     * Constructor of GenericAsyncTask. It assigns every handed model dao to a instance variable
     * of the specific dao.
     * @param cityDao Dao for City model
     * @param universityDao Dao for University model
     * @param courseOfStudiesDao Dao for CourseOfStudies model
     * @param moduleDao Dao for Module model
     * @param categoryDao Dao for Category model
     * @param lectureDao Dao for Lecture model
     * @param semesterDao Dao for Semester model
     */
    public GenericAsyncTask(CityDao cityDao, UniversityDao universityDao, CourseOfStudiesDao courseOfStudiesDao, ModuleDao moduleDao, CategoryDao categoryDao, LectureDao lectureDao, SemesterDao semesterDao) {
        this.cityDao = cityDao;
        this.universityDao = universityDao;
        this.courseOfStudiesDao = courseOfStudiesDao;
        this.moduleDao = moduleDao;
        this.categoryDao = categoryDao;
        this.lectureDao = lectureDao;
        this.semesterDao = semesterDao;
    }

    /**
     * Async task for inserting cities.
     */
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

    /**
     * Public method for starting the city async task and inserting an array of cities into the database.
     * @param cities List of cities
     */
    public void insertCities(City[] cities) {
        new insertCities(this.cityDao).execute(cities);
    }

    /**
     * Async task for inserting universities.
     */
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

    /**
     * Public method for starting the university async task and inserting an array of universities into the database.
     * @param universities List of universities
     */
    public void insertUniversities(University[] universities) {
        new insertUniversities(this.universityDao).execute(universities);
    }

    /**
     * Async task for inserting course of studies.
     */
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

    /**
     * Public method for starting the course async task and inserting an array of courses into the database.
     * @param courseOfStudies List of courses
     */
    public void insertCourses(CourseOfStudies[] courseOfStudies) {
        new insertCourse(this.courseOfStudiesDao).execute(courseOfStudies);
    }

    /**
     * Async task for inserting modules.
     */
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

    /**
     * Public method for starting the module async task and inserting an array of modules into the database.
     * @param modules List of modules
     */
    public void insertModules(Module[] modules) {
        new insertModules(this.moduleDao).execute(modules);
    }

    /**
     * Async task for inserting categories.
     */
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

    /**
     * Public method for starting the category async task and inserting an array of categories into the database.
     * @param categories List of categories
     */
    public void insertCategories(Category[] categories) {
        new insertCategories(this.categoryDao).execute(categories);
    }

    /**
     * Async task for inserting lectures.
     */
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

    /**
     * Public method for starting the lecture async task and inserting an array of lectures into the database.
     * @param lectures List of lectures
     */
    public void insertLectures(Lecture[] lectures) {
        new insertLectures(this.lectureDao).execute(lectures);
    }

    /**
     * Async task for inserting semester.
     */
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

    /**
     * Public method for starting the semester async task and inserting an array of semester into the database.
     * @param semesters List of semester
     */
    public void insertSemester(Semester[] semesters) {
        new insertSemester(this.semesterDao).execute(semesters);
    }
}


