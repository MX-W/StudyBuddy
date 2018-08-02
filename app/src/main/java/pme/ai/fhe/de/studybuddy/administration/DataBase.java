package pme.ai.fhe.de.studybuddy.administration;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import pme.ai.fhe.de.studybuddy.administration.daos.SemesterDao;
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
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.Module;
import pme.ai.fhe.de.studybuddy.model.Semester;
import pme.ai.fhe.de.studybuddy.model.University;
import pme.ai.fhe.de.studybuddy.model.UserData;

/**
 * The DataBase class represents the room database of the project. It gets a list with all
 * entities and creates a database out of them. It also generates all the necessary daos.
 */
@Database(entities = {Category.class, City.class, CourseOfStudies.class, Lecture.class,
        Module.class, University.class, UserData.class, Semester.class}, version = 11)
public abstract class DataBase extends RoomDatabase{

    public abstract CourseOfStudiesDao getCourseOfStudiesDao();
    public abstract CityDao getCityDao();
    public abstract UniversityDao getUniversityDao();
    public abstract UserDataDao getUserDataDao();
    public abstract LectureDao getLectureDao();
    public abstract CategoryDao getCategoryDao();
    public abstract ModuleDao getModuleDao();
    public abstract SemesterDao getSemesterDao();

    private static DataBase INSTANCE;

    /**
     * This method creates the database by the means of the singleton principle. It creates an
     * instance of the DataBase if there is none existent right now. Otherwise returns
     * the instance of the databse.
     * @param context the context the database should be created in
     * @return the room database object
     */
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