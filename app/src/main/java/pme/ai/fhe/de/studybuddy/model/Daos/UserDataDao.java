package pme.ai.fhe.de.studybuddy.model.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import pme.ai.fhe.de.studybuddy.model.UserData;

@Dao
public abstract class UserDataDao implements IGenericDao<UserData>{
    
}
