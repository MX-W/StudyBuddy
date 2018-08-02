package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * This class represents the university entity. It has a foreigen key connection to the city class.
 * This refers to the city in which the university is.
 */
@Entity(
        tableName = "university",
        foreignKeys = @ForeignKey(entity = City.class, parentColumns = "city_id", childColumns = "city_id")
)
public class University {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "university_id")
    private int universityId;

    @ColumnInfo
    private String name;

    @ColumnInfo(name = "city_id")
    private int cityId;

    /**
     * Create a new university.
     * @param name the university name
     * @param cityId the city id the university belongs to
     */
    public University(String name, int cityId) {
        this.name = name;
        this.cityId = cityId;
    }

    /**
     * Getter for the university id
     * @return the university id
     */
    public int getUniversityId() {
        return universityId;
    }

    /**
     * Setter for the university id
     * @param cityId the new university id
     */
    public void setUniversityId(int cityId) {
        this.universityId = cityId;
    }

    /**
     * Getter for the university name
     * @return the university name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the university name
     * @param name the new university name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the city id of the university
     * @return the university city id
     */
    public int getCityId() {
        return cityId;
    }

    /**
     * Setter for the city id of the university
     * @param cityId the university city id
     */
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
