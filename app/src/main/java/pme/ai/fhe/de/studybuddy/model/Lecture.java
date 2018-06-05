package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "lecture",
        foreignKeys = {
            @ForeignKey(entity = CourseOfStudies.class, parentColumns = "id", childColumns = "course_id"),
            @ForeignKey(entity = Module.class, parentColumns = "id", childColumns = "module_id"),
            @ForeignKey(entity = Lecturer.class, parentColumns = "id", childColumns = "lecturer_id"),
            @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id")
        })
public class Lecture {

    @PrimaryKey
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

    @ColumnInfo(name = "lecturer_id")
    private String lecturerId;

    @ColumnInfo
    private float grade;

    @ColumnInfo(name = "category_id")
    private int categoryId;

    @ColumnInfo
    private int attempt;

    @ColumnInfo (name = "exam_type")
    private String examType;

    @ColumnInfo(name = "periods_per_week")
    private int periodsPerWeek;

    @ColumnInfo
    private String language;

    @ColumnInfo
    private boolean practice;

    @ColumnInfo(name = "length")
    private int lengthSemester;

    @ColumnInfo
    private String content;

    @ColumnInfo(name = "information")
    private String furtherInformation;



    public int getLectureId() {
        return id;
    }

    public void setLectureId(int lectureId) {
        this.id = lectureId;
    }

    public int getCourseOfStudiesId() {
        return courseOfStudiesId;
    }

    public void setCourseOfStudiesId(int courseOfStudiesId) {
        this.courseOfStudiesId = courseOfStudiesId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
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

    public boolean isObligation() {
        return obligation;
    }

    public void setObligation(boolean obligation) {
        this.obligation = obligation;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public int getPeriodsPerWeek() {
        return periodsPerWeek;
    }

    public void setPeriodsPerWeek(int periodsPerWeek) {
        this.periodsPerWeek = periodsPerWeek;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isPractice() {
        return practice;
    }

    public void setPractice(boolean practice) {
        this.practice = practice;
    }

    public int getLengthSemester() {
        return lengthSemester;
    }

    public void setLengthSemester(int lengthSemester) {
        this.lengthSemester = lengthSemester;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFurtherInformation() {
        return furtherInformation;
    }

    public void setFurtherInformation(String furtherInformation) {
        this.furtherInformation = furtherInformation;
    }
}
