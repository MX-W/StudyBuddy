package pme.ai.fhe.de.studybuddy.model.Daos;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import pme.ai.fhe.de.studybuddy.model.Category;
import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.model.DependentLecture;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.Lecturer;
import pme.ai.fhe.de.studybuddy.model.Module;

@Database(entities = {Category.class, City.class, CourseOfStudies.class, DependentLecture.class, Lecture.class, Lecturer.class, Module.class}, version = 1)
public abstract class DataBase extends RoomDatabase{

    public abstract CourseOfStudiesDao getCourseOfStudiesDao();

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