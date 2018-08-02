package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import pme.ai.fhe.de.studybuddy.model.UserData;

/**
 * The dao for the user data model
 */
@Dao
public abstract class UserDataDao implements IGenericDao<UserData>{

    /**
     * Returns the user data
     * @return UserData object
     */
    @Query("SELECT * FROM user_data LIMIT 1")
    public abstract UserData getUserData();

    /**
     * Deletes a specific user data at a id
     * @param userId
     */
    @Query("DELETE FROM user_data WHERE user_data_id = :userId")
    public abstract void deleteUserData(int userId);
}
