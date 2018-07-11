package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.Module;
@Dao
public abstract class ModuleDao implements IGenericDao <Module>{

    @Query("SELECT * FROM module WHERE course_id = :courseId")
    public abstract List<Module> getModulesByCourseId(int courseId);
}
