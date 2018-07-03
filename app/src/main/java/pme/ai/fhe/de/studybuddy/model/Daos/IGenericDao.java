package pme.ai.fhe.de.studybuddy.model.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import java.util.Collection;


interface IGenericDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert( T entity);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Collection<T> entity);

    @Update
    void update( T entity);

    @Delete
    void delete( T entity);
}
