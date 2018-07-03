package pme.ai.fhe.de.studybuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.stetho.Stetho;

import java.util.List;

import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.model.Daos.DataBase;
import pme.ai.fhe.de.studybuddy.model.Daos.DataController;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_home);

        DataController controller = DataController.getInstance(getApplication());
        int id = controller.getCityIdByName("Jena");
        Log.i("Erfurt", Integer.toString(id));
    }
}
