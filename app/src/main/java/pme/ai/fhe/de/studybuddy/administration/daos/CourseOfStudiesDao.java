package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;

/**
 * The dao for the course of studies model
 */
@Dao
public abstract class CourseOfStudiesDao implements IGenericDao<CourseOfStudies> {

    /**
     *
     * @return list of all courses
     */
    @Query("SELECT * from course_of_studies")
    public abstract List<CourseOfStudies> getAll();

    /**
     * Returns course id by a name
     * @param courseName
     * @return course id
     */
    @Query("SELECT course_id FROM course_of_studies WHERE name = :courseName")
    public abstract int getCourseIdByName(String courseName);

    /**
     * Returns course names by university id
     * @param universityId
     * @return list of strings
     */
    @Query("SELECT name FROM course_of_studies WHERE university_id = :universityId;")
    public abstract List<String> getCoursesByUniversityId(int universityId);

    /**
     * Returns course name by id
     * @param courseId
     * @return course name
     */
    @Query("SELECT name FROM course_of_studies WHERE course_id = :courseId;")
    public abstract String getCourseById(int courseId);
}
