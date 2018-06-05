package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "dependent_lecture",
        foreignKeys = {
                @ForeignKey(entity = Lecture.class, parentColumns = "id", childColumns = "lecture_id"),
                @ForeignKey(entity = Lecture.class, parentColumns = "id", childColumns = "dependent_id")
        })
public class DependentLecture {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "lecture_id")
    private int lectureId;

    @ColumnInfo(name = "dependent_id")
    private int dependentId;
}
