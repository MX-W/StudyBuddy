package pme.ai.fhe.de.studybuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.model.Daos.DataController;

public class SetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        final DataController controller = DataController.getInstance(getApplication());
        List<City> allCities = controller.getAllCities();
        List<String> cityNames = new ArrayList<>();
        for(City city : allCities) {
            cityNames.add(city.getName());
        }
        final Spinner spinnerCities = findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cityNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCities.setAdapter(dataAdapter);

        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCity = parentView.getItemAtPosition(position).toString();
                List<String> availableUniversities = controller.getUniversitiesByCityName(selectedCity);
                Spinner spinnerUniversity = (Spinner) findViewById(R.id.spinnerUniversity);

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(parentView.getContext(),
                        android.R.layout.simple_spinner_item, availableUniversities);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerUniversity.setAdapter(dataAdapter);

                spinnerUniversity.setVisibility(View.VISIBLE);
                findViewById(R.id.textView4).setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
}
