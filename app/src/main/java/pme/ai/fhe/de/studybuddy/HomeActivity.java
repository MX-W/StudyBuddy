package pme.ai.fhe.de.studybuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.model.Daos.DataBase;
import pme.ai.fhe.de.studybuddy.model.Daos.DataController;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DataController controller = DataController.getInstance(getApplication());
        List<City> list = controller.getAllCities();

        Log.i("All cities: ", list.toString());

        for(City cours : list) {
            String course = cours.getName();
            Log.i("Cities: ", course);
        }
    }
}
