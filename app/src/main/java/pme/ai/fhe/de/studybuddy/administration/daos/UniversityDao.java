package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.University;

/**
 * The dao for the university model
 */
@Dao
public abstract class UniversityDao implements IGenericDao<University> {

    /**
     * Returns all universities
     * @return list of universites
     */
    @Query("SELECT * FROM university;")
    public abstract List<University> getAllUniversities();

    /**
     * Returns a university id by its name
     * @param universityName
     * @return university id
     */
    @Query("SELECT university_id FROM university WHERE name = :universityName")
    public abstract int getUniversityIdByName(String universityName);

    /**
     * Returns a list of universities by a city id
     * @param cityId
     * @return list of universities
     */
    @Query("SELECT name FROM university WHERE city_id = :cityId;")
    public abstract List<String> getUniversitiesByCityId(int cityId);

    /**
     * Returns a university name by its id
     * @param universityId
     * @return university name
     */
    @Query("SELECT name FROM university WHERE university_id = :universityId;")
    public abstract String getUniversitiyById(int universityId);
}
