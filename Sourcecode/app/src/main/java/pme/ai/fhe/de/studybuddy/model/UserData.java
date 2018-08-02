package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.GregorianCalendar;

import pme.ai.fhe.de.studybuddy.utilities.DateConverter;

/**
 * This class represents the user data entity. It stores all relevant data about the user.
 * That would be, the city id, the university id as well as the course id, the user chose. The
 * semester he is in and the id of the current semester as well as a the last login.
 */
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

    @ColumnInfo(name = "current_semester")
    private int currentSemesterId;

    @ColumnInfo(name = "last_login")
    @TypeConverters({DateConverter.class})
    private GregorianCalendar lastLogin;

    /**
     * Creates new user data
     * @param cityId the city the user chose
     * @param universityId the university the user chose
     * @param courseId the course the user chose
     * @param semester the semester he is in right now
     * @param currentSemesterId the id of the current semester
     * @param lastLogin the last login
     */
    public UserData(int cityId, int universityId, int courseId, int semester, int currentSemesterId, GregorianCalendar lastLogin) {
        this.cityId = cityId;
        this.universityId = universityId;
        this.courseId = courseId;
        this.semester = semester;
        this.currentSemesterId = currentSemesterId;
        this.lastLogin = lastLogin;
    }

    /**
     * Getter for the city id
     * @return the city id
     */
    public int getCityId() {
        return cityId;
    }

    /**
     * Setter for the city id
     * @param cityId the new city id
     */
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    /**
     * Getter for the university id
     * @return the university id
     */
    public int getUniversityId() {
        return universityId;
    }

    /**
     * Setter for the university id
     * @param universityId the new university id
     */
    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    /**
     * Getter for the course id
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
     * Getter for the current semester the user is in
     * @return the current semester the user is in
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Setter for the current semester the user is in
     * @param semester new current semester the user is in
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * Getter for the id of the user data
     * @return the id of the user data
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the id of the user data
     * @param id the new id of the user data
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter for the current semester id
     * @return the current semester id
     */
    public int getCurrentSemesterId() {
        return currentSemesterId;
    }

    /**
     * Setter for the current semester id
     * @param currentSemesterId the new current semester id
     */
    public void setCurrentSemesterId(int currentSemesterId) {
        this.currentSemesterId = currentSemesterId;
    }

    /**
     * Getter for the last login
     * @return the last login
     */
    public GregorianCalendar getLastLogin() {
        return lastLogin;
    }

    /**
     * Setter for the last login
     * @param lastLogin the last login
     */
    public void setLastLogin(GregorianCalendar lastLogin) {
        this.lastLogin = lastLogin;
    }
}
