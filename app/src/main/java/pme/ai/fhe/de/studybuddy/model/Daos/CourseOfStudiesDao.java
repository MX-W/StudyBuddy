package pme.ai.fhe.de.studybuddy.model.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Collection;
import java.util.List;

import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;

@Dao
public interface CourseOfStudiesDao{


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(CourseOfStudies entity);


    @Query("SELECT * from course_of_studies")
    public List<CourseOfStudies> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertAll(Collection<CourseOfStudies> entity);


    @Update
    public void update(CourseOfStudies entity);


    @Delete
    public void delete(CourseOfStudies entity);
}
