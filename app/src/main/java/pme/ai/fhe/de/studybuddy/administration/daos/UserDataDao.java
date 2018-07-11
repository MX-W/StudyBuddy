package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import pme.ai.fhe.de.studybuddy.model.UserData;

@Dao
public abstract class UserDataDao implements IGenericDao<UserData>{

    @Query("SELECT * FROM user_data LIMIT 1")
    public abstract UserData getUserData();

    @Query("DELETE FROM user_data WHERE user_data_id = :userId")
    public abstract void deleteUserData(int userId);
}
