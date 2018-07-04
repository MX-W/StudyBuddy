package pme.ai.fhe.de.studybuddy.model.Daos;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import pme.ai.fhe.de.studybuddy.model.Category;
import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.model.DependentLecture;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.Lecturer;
import pme.ai.fhe.de.studybuddy.model.Module;
import pme.ai.fhe.de.studybuddy.model.University;
import pme.ai.fhe.de.studybuddy.model.UserData;

@Database(entities = {Category.class, City.class, CourseOfStudies.class, DependentLecture.class, Lecture.class, Lecturer.class, Module.class, University.class, UserData.class}, version = 6)
public abstract class DataBase extends RoomDatabase{

    public abstract CourseOfStudiesDao getCourseOfStudiesDao();
    public abstract CityDao getCityDao();
    public abstract UniversityDao getUniversityDao();
    public abstract UserDataDao getUserDataDao();

    private static DataBase INSTANCE;


    public static DataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class, "study_buddy_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}