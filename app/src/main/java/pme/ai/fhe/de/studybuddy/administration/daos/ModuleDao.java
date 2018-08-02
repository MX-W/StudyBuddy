package pme.ai.fhe.de.studybuddy.administration.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.Module;

/**
 * The dao for the module model
 */
@Dao
public abstract class ModuleDao implements IGenericDao <Module>{

    /**
     * Returns a list of modules by a course id
     * @param courseId
     * @return list of modules
     */
    @Query("SELECT * FROM module WHERE course_id = :courseId")
    public abstract List<Module> getModulesByCourseId(int courseId);
}
