package pme.ai.fhe.de.studybuddy;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.model.Daos.DataBase;
import pme.ai.fhe.de.studybuddy.model.Daos.DataController;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DataController controller = new DataController(getApplication());
        DataBase db = DataBase.getDatabase(getApplicationContext());
        List<CourseOfStudies> list = controller.getAllCourses();

        Log.i("All courses: ", list.toString());

        for(CourseOfStudies cours : list) {
            String course = Integer.toString(cours.getCourseId());
            Log.i("CourseOfStudies-Dao: ", course);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
