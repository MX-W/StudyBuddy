package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

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

    public Module(int courseOfStudyId,String name, int credits, String moduleCode)
    {
        this.name = name;
        this.credits = credits;
        this.moduleCode = moduleCode;
        this.courseOfStudyId = courseOfStudyId;
    }


    public int getModuleId() {
        return id;
    }

    public void setModuleId(int moduleId) {
        this.id = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCourseOfStudyId() {
        return courseOfStudyId;
    }

    public void setCourseOfStudyId(int courseOfStudyId) {
        this.courseOfStudyId = courseOfStudyId;
    }

}
