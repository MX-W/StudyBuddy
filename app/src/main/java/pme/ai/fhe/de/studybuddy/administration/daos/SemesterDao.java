package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.Semester;

@Dao
public abstract class SemesterDao implements IGenericDao<Semester> {

    @Query("SELECT * FROM semester")
    public abstract List<Semester> getAllSemester();
}
