package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.Lecture;

/**
 * The dao for the lecture model
 */
@Dao
public abstract class LectureDao implements IGenericDao<Lecture> {

    /**
     * Returns a list of lectures by an course id
     * @param courseId
     * @return list of lectures
     */
    @Query("SELECT * FROM lecture WHERE course_id = :courseId")
    public abstract List<Lecture> getLecturesByCourseId(int courseId);

    /**
     * Returns list of lectures with grade
     * @param courseId
     * @return list of lectures
     */
    @Query("SELECT * FROM lecture WHERE course_id = :courseId AND grade != 0.0")
    public abstract List<Lecture> getAllLecturesWithGrade(int courseId);

    /**
     * Returns a list of lectures with grade orded by semester
     * @param courseId
     * @return list of lectures
     */
    @Query("SELECT * FROM lecture WHERE course_id = :courseId AND grade != 0.0 ORDER BY semester_passed ASC")
    public abstract List<Lecture> getAllLecturesWithGradeOrderBySemester(int courseId);

    /**
     * Returns a list of lectures by a module id
     * @param moduleId
     * @return list of lectures
     */
    @Query("SELECT * FROM lecture WHERE module_id = :moduleId")
    public abstract List<Lecture> getLectureByModuleId(int moduleId);

    /**
     * Resets all grades in the database
     */
    @Query("UPDATE lecture SET grade = 0.0 WHERE grade != 0.0")
    public abstract void resetAllGrades();
}
