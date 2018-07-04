package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.University;

@Dao
public abstract class UniversityDao implements IGenericDao<University> {

    @Query("SELECT * FROM university;")
    public abstract List<University> getAllUniversities();

    @Query("SELECT university_id FROM university WHERE name = :universityName")
    public abstract int getUniversityIdByName(String universityName);

    @Query("SELECT name FROM university WHERE city_id = :cityId;")
    public abstract List<String> getUniversitiesByCityId(int cityId);

    @Query("SELECT name FROM university WHERE university_id = :universityId;")
    public abstract String getUniversitiyById(int universityId);
}
