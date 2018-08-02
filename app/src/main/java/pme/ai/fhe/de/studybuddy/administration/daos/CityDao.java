package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.City;

/**
 * The dao for the city model.
 */
@Dao
public abstract class CityDao implements IGenericDao<City> {


    /**
     *
     * @return list of all cities
     */
    @Query("SELECT * FROM city;")
    public abstract List<City> getAll();

    /**
     * Returns the city id by a name
     * @param cityName
     * @return city id
     */
    @Query("SELECT city_id FROM city WHERE name = :cityName")
    public abstract int getCityIdByName(String cityName);

    /**
     * Returns the city name by an id
     * @param cityId
     * @return city name
     */
    @Query("SELECT name FROM city WHERE city_id = :cityId")
    public abstract String getCityNameById( int cityId);
}
