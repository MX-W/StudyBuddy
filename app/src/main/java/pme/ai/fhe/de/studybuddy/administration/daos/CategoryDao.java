package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import pme.ai.fhe.de.studybuddy.model.Category;

@Dao
public abstract class CategoryDao implements IGenericDao<Category> {

    @Query("SELECT COUNT(category_id) FROM category")
    public abstract int getNumberOfAll();

    @Query("SELECT name FROM category WHERE category_id = :categoryId;")
    public abstract String getNameByID(int categoryId);
}
