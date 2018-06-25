package pme.ai.fhe.de.studybuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.model.Daos.DataBase;
import pme.ai.fhe.de.studybuddy.model.Daos.DataController;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DataController controller = new DataController(getApplication());
        DataBase db = DataBase.getDatabase(getApplicationContext());
        List<CourseOfStudies> list = controller.getAllCourses();

        Log.i("All courses: ", list.toString());

        for(CourseOfStudies cours : list) {
            String course = Integer.toString(cours.getCourseId());
            Log.i("CourseOfStudies-Dao: ", course);
        }
    }
}
