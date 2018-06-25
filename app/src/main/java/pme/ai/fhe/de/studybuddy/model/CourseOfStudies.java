package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "course_of_studies",
        primaryKeys = { "course_id" }
        /*foreignKeys = {
            @ForeignKey(entity = City.class, parentColumns = "id", childColumns = "city_id")
        }*/)
public class CourseOfStudies {

    @ColumnInfo(name = "course_id")
    private int courseId;

    @ColumnInfo
    private String name;

    @ColumnInfo(name = "name_count")
    private int semesterCount;

    @ColumnInfo
    private int credits;

    @ColumnInfo
    private String faculty;

    @ColumnInfo(name = "city_id")
    private int cityId;

    @ColumnInfo(name = "univeristy_id")
    private int universityId;

    public CourseOfStudies(String name, int semesterCount, int credits, String faculty, int cityId, int universityId) {
        this.name = name;
        this.semesterCount = semesterCount;
        this.credits = credits;
        this.faculty = faculty;
        this.cityId = cityId;
        this.universityId = universityId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemesterCount() {
        return semesterCount;
    }

    public void setSemesterCount(int semesterCount) {
        this.semesterCount = semesterCount;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
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
}
