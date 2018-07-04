package pme.ai.fhe.de.studybuddy.administration;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import pme.ai.fhe.de.studybuddy.model.Category;
import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.administration.daos.CategoryDao;
import pme.ai.fhe.de.studybuddy.administration.daos.CityDao;
import pme.ai.fhe.de.studybuddy.administration.daos.CourseOfStudiesDao;
import pme.ai.fhe.de.studybuddy.administration.daos.LectureDao;
import pme.ai.fhe.de.studybuddy.administration.daos.ModuleDao;
import pme.ai.fhe.de.studybuddy.administration.daos.UniversityDao;
import pme.ai.fhe.de.studybuddy.administration.daos.UserDataDao;
import pme.ai.fhe.de.studybuddy.model.DependentLecture;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.Lecturer;
import pme.ai.fhe.de.studybuddy.model.Module;
import pme.ai.fhe.de.studybuddy.model.University;
import pme.ai.fhe.de.studybuddy.model.UserData;

@Database(entities = {Category.class, City.class, CourseOfStudies.class, DependentLecture.class, Lecture.class, Lecturer.class, Module.class, University.class, UserData.class}, version = 8)
public abstract class DataBase extends RoomDatabase{

    public abstract CourseOfStudiesDao getCourseOfStudiesDao();
    public abstract CityDao getCityDao();
    public abstract UniversityDao getUniversityDao();
    public abstract UserDataDao getUserDataDao();
    public abstract LectureDao getLectureDao();
    public abstract CategoryDao getCategoryDao();
    public abstract ModuleDao getModuleDao();

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