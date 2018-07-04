package pme.ai.fhe.de.studybuddy.model.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import pme.ai.fhe.de.studybuddy.model.UserData;

@Dao
public abstract class UserDataDao implements IGenericDao<UserData>{

    @Query("SELECT * FROM user_data LIMIT 1")
    abstract UserData getUserData();
}
