package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * This class represents the model of the lecture entity. If has a foreign key connection to
 * course of studies, module and category. These are all to locate where the lecture stands within
 * the whole model.
 */
@Entity(tableName = "lecture",
        foreignKeys = {
            @ForeignKey(entity = CourseOfStudies.class, parentColumns = "course_id", childColumns = "course_id"),
            @ForeignKey(entity = Module.class, parentColumns = "module_id", childColumns = "module_id"),
            @ForeignKey(entity = Category.class, parentColumns = "category_id", childColumns = "category_id"),
        })
public class Lecture {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lecture_id")
    private int id;

    @ColumnInfo(name = "course_id")
    private int courseOfStudiesId;

    @ColumnInfo(name = "module_id")
    private int moduleId;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String description;

    @ColumnInfo
    private int credits;

    @ColumnInfo
    private boolean obligation;

    @ColumnInfo
    private float grade;

    @ColumnInfo(name = "category_id")
    private int categoryId;

    @ColumnInfo
    private String language;

    @ColumnInfo(name = "semester_passed")
    private int semesterPassed;

    /**
     * Create a new lecture. The grade is set to 0 at the beginning.
     * @param courseOfStudiesId the id of the course of studies the lecture belongs to
     * @param moduleId the id of the module the lecture belongs to
     * @param name the name of the lecture
     * @param credits the credits of the lecture
     * @param obligation if the lecture is obligate
     * @param categoryId the id of the category the lecture belongs to
     * @param language the language the lecture is in
     */
    public Lecture(int courseOfStudiesId, int moduleId, String name, int credits, boolean obligation, int categoryId, String language)
    {
        this.courseOfStudiesId = courseOfStudiesId;
        this.moduleId = moduleId;
        this.name = name;
        this.credits = credits;
        this.obligation = obligation;
        this.categoryId = categoryId;
        this.language = language;
        this.semesterPassed = 0;

    }

    /**
     * Getter for the id of the course the lecture belongs to
     * @return the course the lecture belongs to
     */
    public int getCourseOfStudiesId() {
        return courseOfStudiesId;
    }

    /**
     * Setter for the course id of the lecture
     * @param courseOfStudiesId the new course id
     */
    public void setCourseOfStudiesId(int courseOfStudiesId) {
        this.courseOfStudiesId = courseOfStudiesId;
    }

    /**
     * Getter for the id of the module the lecture belongs to
     * @return the module the lecture belongs to
     */
    public int getModuleId() {
        return moduleId;
    }

    /**
     * Setter for the module id of the lecture
     * @param moduleId the new module id
     */
    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * Getter for the name of the lecture
     * @return the name of the lecture
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the lecture
     * @param name the new name of the lecture
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the description of the lecture
     * @return the description of the lecture
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of the lecture
     * @param description the new description of the lecture
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the credits of the lecture
     * @return the credits of the lecture
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Setter for the credits of the lecture
     * @param credits the new credits of the lecture
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Getter the lecture is obligate
     * @return the obligation of the lecture
     */
    public boolean isObligation() {
        return obligation;
    }

    /**
     * Setter for the obligation of the lecture
     * @param obligation the new obligation
     */
    public void setObligation(boolean obligation) {
        this.obligation = obligation;
    }

    /**
     * Getter for the grade of the lecture
     * @return the grade of the lecture
     */
    public float getGrade() {
        return grade;
    }

    /**
     * Setter for the grade of the lecture
     * @param grade the new grade of the lecture
     */
    public void setGrade(float grade) {
        this.grade = grade;
    }

    /**
     * Getter for the category id of the lecture
     * @return the category id of the lecture
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Setter for the category id of the lecture
     * @param categoryId the new category id of the lecture
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Getter for the language of the lecture
     * @return the language of the lecture
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Setter for the language of the lecture
     * @param language the new language of the lecture
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Getter for the id of the lecture
     * @return the id of the lecture
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the id of the lecture
     * @param id the new id of the lecture
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the semester in which the lecture was passed
     * @return the semester in which the lecture was passed
     */
    public int getSemesterPassed() {
        return semesterPassed;
    }

    /**
     * Setter for the semester in which the lecture was passed
     * @param semesterPassed the new semester in which the lecture was passed
     */
    public void setSemesterPassed(int semesterPassed) {
        this.semesterPassed = semesterPassed;
    }
}
