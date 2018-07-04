package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.City;

@Dao
public abstract class CityDao implements IGenericDao<City> {


    @Query("SELECT * FROM city;")
    public abstract List<City> getAll();

    @Query("SELECT city_id FROM city WHERE name = :cityName")
    public abstract int getCityIdByName(String cityName);

    @Query("SELECT name FROM city WHERE city_id = :cityId")
    public abstract String getCityNameById( int cityId);
}