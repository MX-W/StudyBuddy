package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import java.util.Collection;


/**
 * Interface for basic Dao functions which can be used in every Dao
 * @param <T> The model for which the Dao is created
 */
interface IGenericDao<T> {

    /**
     * Insert a single object.
     * @param entity an object of a model
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert( T entity);

    /**
     * Insert a collection of many objects.
     * @param entity a collection of model objects
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Collection<T> entity);

    /**
     * Update a single object
     * @param entity a object of a model
     */
    @Update
    void update( T entity);

    /**
     * Delete a single object
     * @param entity a object of a model
     */
    @Delete
    void delete( T entity);
}
