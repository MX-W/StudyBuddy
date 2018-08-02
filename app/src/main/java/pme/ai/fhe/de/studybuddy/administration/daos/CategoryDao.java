package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import pme.ai.fhe.de.studybuddy.model.Category;

/**
 * The dao for the category model
 */
@Dao
public abstract class CategoryDao implements IGenericDao<Category> {

    /**
     * Returns the quantity of all categories.
     * @return number of all categories
     */
    @Query("SELECT COUNT(category_id) FROM category")
    public abstract int getNumberOfAll();

    /**
     * Returns the category name by an id
     * @param categoryId
     * @return name of a category
     */
    @Query("SELECT name FROM category WHERE category_id = :categoryId;")
    public abstract String getNameByID(int categoryId);
}
