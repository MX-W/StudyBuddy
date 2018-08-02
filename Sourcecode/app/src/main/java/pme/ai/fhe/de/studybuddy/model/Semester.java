package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * This class represents the semester entity.
 */
@Entity(
        tableName = "semester"
)
public class Semester {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "semester_id")
    private int semesterId;

    private String name;

    /**
     * Create a new semester
     * @param name semester name
     */
    public Semester(String name) {
        this.name = name;
    }

    /**
     * Getter for the semester id
     * @return the semester id
     */
    public int getSemesterId() {
        return semesterId;
    }

    /**
     * Setter for the semester id
     * @param semesterId the new semester id
     */
    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    /**
     * Getter for the semester name
     * @return the semester name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the semester name
     * @param name the new semester name
     */
    public void setName(String name) {
        this.name = name;
    }
}
