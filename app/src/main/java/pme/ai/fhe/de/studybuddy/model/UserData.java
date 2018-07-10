package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity( tableName = "user_data")
public class UserData {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_data_id")
    private int id;

    @ColumnInfo(name = "city_id")
    private int cityId;

    @ColumnInfo(name = "university_id")
    private int universityId;

    @ColumnInfo(name = "course_id")
    private int courseId;

    private int semester;

    @ColumnInfo(name = "starting_semester_id")
    private int startingSemesterId;

    public UserData(int cityId, int universityId, int courseId, int semester, int startingSemesterId) {
        this.cityId = cityId;
        this.universityId = universityId;
        this.courseId = courseId;
        this.semester = semester;
        this.startingSemesterId = startingSemesterId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartingSemesterId() {
        return startingSemesterId;
    }

    public void setStartingSemester(int startingSemesterId) {
        this.startingSemesterId = startingSemesterId;
    }
}
