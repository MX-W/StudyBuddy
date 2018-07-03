package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


@Entity(
        tableName = "university",
        primaryKeys = "university_id",
        foreignKeys = @ForeignKey(entity = City.class, parentColumns = "id", childColumns = "city_id")
)
public class University {

    @ColumnInfo(name = "university_id")
    private int universityId;

    @ColumnInfo
    private String name;

    @ColumnInfo(name = "city_id")
    private int cityId;

    public University(String name, int cityId) {
        this.name = name;
        this.cityId = cityId;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int cityId) {
        this.universityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
