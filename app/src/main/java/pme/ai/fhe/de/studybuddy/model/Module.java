package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * This class represents the module entity. It has a foreign key connection to a course_id. This
 * stands for the course to which the lecture belongs.
 */
@Entity (
        tableName = "module",
        foreignKeys = {
        @ForeignKey(entity = CourseOfStudies.class, parentColumns = "course_id", childColumns = "course_id")
        })
public class Module {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "module_id")
    private int id;

    @ColumnInfo(name = "course_id")
    private int courseOfStudyId;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String description;

    @ColumnInfo
    private int credits;

    @ColumnInfo
    private float grade;

    @ColumnInfo(name = "module_code")
    private String moduleCode;

    /**
     * Creates a new module.
     * @param courseOfStudyId the course id of the course the module belongs to
     * @param name the name of the module
     * @param credits total credits of the module
     * @param moduleCode the module code
     */
    public Module(int courseOfStudyId,String name, int credits, String moduleCode)
    {
        this.name = name;
        this.credits = credits;
        this.moduleCode = moduleCode;
        this.courseOfStudyId = courseOfStudyId;
    }

    /**
     * Getter for the name of the module
     * @return the module name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the module name
     * @param name the new module name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the description of the module
     * @return the module description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the module description
     * @param description new module description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the credits of the module
     * @return the module credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Setter for the module credits
     * @param credits the new module credits
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Getter for the grade of the module
     * @return the module grade
     */
    public float getGrade() {
        return grade;
    }

    /**
     * Setter for the module grade
     * @param grade the new module grade
     */
    public void setGrade(float grade) {
        this.grade = grade;
    }

    /**
     * Getter for the code of the module
     * @return the module code
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Setter for the module code
     * @param moduleCode the new module code
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Setter for the module id
     * @param id the new module id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the id of the module
     * @return the module id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the course id the module belongs to
     * @return the course id of the module
     */
    public int getCourseOfStudyId() {
        return courseOfStudyId;
    }

    /**
     * Setter for the course id of the module
     * @param courseOfStudyId the module course id
     */
    public void setCourseOfStudyId(int courseOfStudyId) {
        this.courseOfStudyId = courseOfStudyId;
    }

}
