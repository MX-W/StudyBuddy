package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.Lecture;

@Dao
public abstract class LectureDao implements IGenericDao<Lecture> {

    @Query("SELECT * FROM lecture WHERE course_id = :courseId")
    public abstract List<Lecture> getLecturesByCourseId(int courseId);
}
