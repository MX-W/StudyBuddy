package pme.ai.fhe.de.studybuddy.model.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.City;

@Dao
abstract class CityDao implements IGenericDao<City> {


    @Query("Select * from city")
    abstract List<City> getAll();
}
