package pme.ai.fhe.de.studybuddy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.model.Daos.DataBase;
import pme.ai.fhe.de.studybuddy.model.Daos.DataController;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


public DrawerLayout mDrawerLayout;
public ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);


        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        openMenu();



        /*DataController controller = new DataController(getApplication());
        DataBase db = DataBase.getDatabase(getApplicationContext());
        List<CourseOfStudies> list = controller.getAllCourses();

        Log.i("All courses: ", list.toString());

        for(CourseOfStudies cours : list) {
            String course = Integer.toString(cours.getCourseId());
            Log.i("CourseOfStudies-Dao: ", course);
        }*/
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.uebersicht:
                startActivity(new Intent(this, Overview.class));
                return true;
            case R.id.noten:
                startActivity(new Intent(this, Grades.class));
                return true;
            case R.id.moduluebersicht:
                startActivity(new Intent(this, Modules.class));
                return true;
            case R.id.profil:
                startActivity(new Intent(this, Profile.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


        //return false;
    }

    public boolean openMenu()
    {
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        Log.i("Drawer:", mDrawerLayout.toString());
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        return true;
    }

}
