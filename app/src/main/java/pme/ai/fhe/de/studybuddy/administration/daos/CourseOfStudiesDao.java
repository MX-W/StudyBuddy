package pme.ai.fhe.de.studybuddy.model.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Collection;
import java.util.List;

import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;

@Dao
public abstract class CourseOfStudiesDao implements IGenericDao<CourseOfStudies> {


    @Query("SELECT * from course_of_studies")
    abstract List<CourseOfStudies> getAll();

    @Query("SELECT course_id FROM course_of_studies WHERE name = :courseName")
    abstract int getCourseIdByName(String courseName);

    @Query("SELECT name FROM course_of_studies WHERE university_id = :universityId;")
    abstract List<String> getCoursesByUniversityId(int universityId);

    @Query("SELECT name FROM course_of_studies WHERE course_id = :courseId;")
    abstract String getCourseById(int courseId);
}
