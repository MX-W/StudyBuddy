package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(
        tableName = "semester"
)
public class Semester {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "semester_id")
    private int semesterId;

    private String name;

    @ColumnInfo(name = "start_date")
    private Date startDate;

    @ColumnInfo(name = "end_date")
    private Date endDate;

    public Semester(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
