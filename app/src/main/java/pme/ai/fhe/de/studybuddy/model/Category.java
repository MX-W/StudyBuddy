package pme.ai.fhe.de.studybuddy.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(
        tableName = "category",
        primaryKeys = "category_id"
)
public class Category {

    @ColumnInfo (name = "category_id")
    private int id;

    @ColumnInfo
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
