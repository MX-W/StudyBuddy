package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "dependent_lecture",
        foreignKeys = {
                @ForeignKey(entity = Lecture.class, parentColumns = "lecture_id", childColumns = "lecture_id"),
                @ForeignKey(entity = Lecture.class, parentColumns = "lecture_id", childColumns = "dependent_id")
        })
public class DependentLecture {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "lecture_id")
    private int lectureId;

    @ColumnInfo(name = "dependent_id")
    private int dependentId;

    public int getId() {
        return id;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public void setDependentId(int dependentId) {
        this.dependentId = dependentId;
    }

    public int getLectureId() {
        return lectureId;
    }

    public int getDependentId() {
        return dependentId;
    }

    public void setId(int id) {
        this.id = id;
    }
}
