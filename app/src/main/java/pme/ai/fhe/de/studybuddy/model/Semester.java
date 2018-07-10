package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import pme.ai.fhe.de.studybuddy.utilities.DateConverter;

@Entity(
        tableName = "semester"
)
public class Semester {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "semester_id")
    private int semesterId;

    private String name;

    public Semester(String name) {
        this.name = name;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
