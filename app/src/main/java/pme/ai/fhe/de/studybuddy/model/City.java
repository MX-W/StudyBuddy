package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class City {

    @PrimaryKey
    private int id;

    @ColumnInfo
    private String name;

    public int getCityId() {
        return id;
    }

    public void setCityId(int cityId) {
        this.id = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
