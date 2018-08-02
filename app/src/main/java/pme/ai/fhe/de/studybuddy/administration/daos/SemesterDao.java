package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.Semester;

/**
 * The dao for the semester model
 */
@Dao
public abstract class SemesterDao implements IGenericDao<Semester> {

    /**
     * Returns all semester
     * @return list of semester
     */
    @Query("SELECT * FROM semester")
    public abstract List<Semester> getAllSemester();

    /**
     * Returns a semester id by its name
     * @param semesterName
     * @return semester id
     */
    @Query("SELECT semester_id FROM semester WHERE name = :semesterName")
    public abstract int getSemesterIdByName(String semesterName);

    /**
     * Returns a semester name by id
     * @param semesterId
     * @return semester name
     */
    @Query("SELECT name FROM semester WHERE semester_id = :semesterId")
    public abstract String getSemesterById(int semesterId);
}