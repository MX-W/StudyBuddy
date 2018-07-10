package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.Lecture;

@Dao
public abstract class LectureDao implements IGenericDao<Lecture> {

    @Query("SELECT * FROM lecture WHERE course_id = :courseId")
    public abstract List<Lecture> getLecturesByCourseId(int courseId);

    @Query("SELECT * FROM lecture WHERE course_id = :courseId AND grade != 0.0")
    public abstract List<Lecture> getAllLecturesWithGrade(int courseId);

    @Query("SELECT * FROM lecture WHERE course_id = :courseId AND grade != 0.0 ORDER BY semester_passed ASC")
    public abstract List<Lecture> getAllLecturesWithGradeOrderBySemester(int courseId);

    @Query("SELECT * FROM lecture WHERE module_id = :moduleId")
    public abstract List<Lecture> getLectureByModuleId(int moduleId);
}
