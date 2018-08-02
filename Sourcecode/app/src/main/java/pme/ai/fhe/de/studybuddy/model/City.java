package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * This class represents the city entity.
 */
@Entity(
        tableName = "city"
)
public class City {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "city_id")
    private int id;

    @ColumnInfo
    private String name;

    /**
     * Create new city
     * @param name city name
     */
    public City(String name){
        this.name = name;
    }

    /**
     * Getter for city name
     * @return the city name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for city name
     * @param name the new city name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for city id
     * @return the city id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for city id
     * @param id the new city id
     */
    public void setId(int id) {
        this.id = id;
    }
}
