package pme.ai.fhe.de.studybuddy.model.Daos;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;

public class DataController {

    private CourseOfStudiesDao mCourseOfStudiesDao;


    public DataController(Application application) {
        DataBase db = DataBase.getDatabase(application);
        mCourseOfStudiesDao = db.getCourseOfStudiesDao();
    }

    public void insertCourse(CourseOfStudies course) {
        new insertAsyncTask(mCourseOfStudiesDao).execute(course);
    }

    public List<CourseOfStudies> getAllCourses() {
        return mCourseOfStudiesDao.getAll();
    }

    private static class insertAsyncTask extends AsyncTask<CourseOfStudies, Void, Void> {

        private CourseOfStudiesDao mAsyncTaskDao;

        insertAsyncTask(CourseOfStudiesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CourseOfStudies... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}


