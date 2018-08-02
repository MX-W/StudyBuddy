package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * This class represents the category entity.
 */
@Entity(
        tableName = "category"
)
public class Category {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "category_id")
    private int id;

    @ColumnInfo
    private String name;

    /**
     * Create new Category
     * @param name Name of the category
     */
    public Category(String name) {
        this.name = name;
    }

    /**
     * Getter for the name
     * @return the name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for category name
     * @param name the new name of the category
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for category id
     * @return the category id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for category id
     * @param id the new category id
     */
    public void setId(int id) {
        this.id = id;
    }
}
