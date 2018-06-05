package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Category {

    @PrimaryKey
    private int id;

    @ColumnInfo
    private String name;

    public int getCategoryId() {
        return id;
    }

    public void setCategoryId(int moduleId) {
        this.id = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
