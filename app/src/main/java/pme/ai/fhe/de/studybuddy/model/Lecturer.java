package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Lecturer {

    @PrimaryKey
    private int id;

    @ColumnInfo
    private String name;

    public int getLecturerId() {
        return id;
    }

    public void setLecturerId(int lecturerId) {
        this.id = lecturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
