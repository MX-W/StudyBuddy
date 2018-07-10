package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;

@Dao
public abstract class CourseOfStudiesDao implements IGenericDao<CourseOfStudies> {


    @Query("SELECT * from course_of_studies")
    public abstract List<CourseOfStudies> getAll();

    @Query("SELECT course_id FROM course_of_studies WHERE name = :courseName")
    public abstract int getCourseIdByName(String courseName);

    @Query("SELECT name FROM course_of_studies WHERE university_id = :universityId;")
    public abstract List<String> getCoursesByUniversityId(int universityId);

    @Query("SELECT name FROM course_of_studies WHERE course_id = :courseId;")
    public abstract String getCourseById(int courseId);
}
