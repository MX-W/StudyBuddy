package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * This class represents the course of studies entity. It has a foreign key connection to the
 * university_id of the University model. That represents, which university the course belongs to.
 */
@Entity(tableName = "course_of_studies",
        foreignKeys = {
            @ForeignKey(entity = University.class, parentColumns = "university_id", childColumns = "university_id")
        })
public class CourseOfStudies {

    @PrimaryKey(autoGenerate = true)
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

    @ColumnInfo(name = "university_id")
    private int universityId;

    /**
     * Create a new CourseOfStudies
     * @param name course name
     * @param semesterCount number of semester the course has
     * @param credits total number of credits for the course
     * @param faculty faculty the course belongs to
     * @param universityId university id the course belongs to
     */
    public CourseOfStudies(String name, int semesterCount, int credits, String faculty, int universityId) {
        this.name = name;
        this.semesterCount = semesterCount;
        this.credits = credits;
        this.faculty = faculty;
        this.universityId = universityId;
    }

    /**
     * Getter for the id of the course
     * @return the course id
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Setter for the course id
     * @param courseId the new course id
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Getter for the name of the course
     * @return the course name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the course name
     * @param name the new course name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the semester count of the course
     * @return the course semester count
     */
    public int getSemesterCount() {
        return semesterCount;
    }

    /**
     * Setter for the semester count of the course
     * @param semesterCount the new semester count
     */
    public void setSemesterCount(int semesterCount) {
        this.semesterCount = semesterCount;
    }

    /**
     * Getter for the total credits of the course
     * @return the courses total credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Setter for the credits of the course
     * @param credits the new credit count
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Getter for the faculty of the course
     * @return the course faculty
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Setter for the faculty of the course
     * @param faculty the new faculty
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * Getter for the university id of the course
     * @return the course university id
     */
    public int getUniversityId() {
        return universityId;
    }

    /**
     * Setter for the university id of the course
     * @param universityId the new university id
     */
    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }
}
