package pme.ai.fhe.de.studybuddy.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.facebook.stetho.Stetho;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.administration.DataController;

/**
 * The Activity is called in every other activity. Is opens the menu, so you can swipe it and call it
 */
public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


public DrawerLayout mDrawerLayout;
public ActionBarDrawerToggle mToggle;
    public DataController controller;

    /**
     * connects the layout with the xml files and opens the menu
     * @param savedInstanceState reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Stetho.initializeWithDefaults(this);

        setContentView(R.layout.activity_home);

        controller = DataController.getInstance(getApplication());

        mDrawerLayout = findViewById(R.id.drawerLayout);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);


        openMenu();

    }

    /**
     * checks which item is selected and opens the right activity therefore
     * @param item the item, that the user has selected
     * @return returns if everything went well
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.uebersicht:
                startActivity(new Intent(this, OverviewActivity.class));
                return true;
            case R.id.noten:
                startActivity(new Intent(this, GradesActivity.class));
                return true;
            case R.id.moduluebersicht:
                startActivity(new Intent(this, ModulesActivity.class));
                return true;
            case R.id.profil:
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            case R.id.insertGrade:
                startActivity(new Intent(this, InsertGradeActivity.class));
                return true;
            case R.id.gradecalculation:
                startActivity(new Intent(this, GradeCalculationActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }

    /**
     * inflates the menu
     * @return return if everything went well
     */
    public boolean openMenu()
    {
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        return true;
    }

}
