package pme.ai.fhe.de.studybuddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.security.auth.callback.Callback;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.administration.DataController;
import pme.ai.fhe.de.studybuddy.model.Semester;
import pme.ai.fhe.de.studybuddy.model.UserData;
import pme.ai.fhe.de.studybuddy.utilities.AddSpinnerItems;

public class SetupActivity extends AppCompatActivity {

    private static String CITY_DEFAULT_SPINNER = "Bitte wähle deine Stadt";
    private static String UNIVERSITY_DEFAULT_SPINNER = "Bitte wähle deine Universität";
    private static String COURSE_DEFAULT_SPINNER = "Bitte wähle deinen Studiengang aus";
    private static String SEMESTER_DEFAULT_SPINNER = "Bitte wähle dein Semester aus";

    private DataController controller;
    private int selectedCityId, selectedUniversityId, selectedCourseId, semester, currentSemesterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_setup);
        controller = DataController.getInstance(getApplication());

        findViewById(R.id.setupButtonForward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();

                int year = now.get(Calendar.YEAR);
                String yearString = Integer.toString(year).substring(2);
                String yearStringPlusOne = Integer.toString(year+1).substring(2);
                String semesterString = "";
                if(now.get(Calendar.MONTH) >= 10 || now.get(Calendar.MONTH) <= 4) {
                    semesterString = "WS-" + yearString + "/" + yearStringPlusOne;
                } else {
                    semesterString = "SS-" + yearString;
                }

                Log.i("Current Semester String", semesterString);

                currentSemesterId = controller.getSemesterIdByName(semesterString);


                UserData data = new UserData(selectedCityId, selectedUniversityId, selectedCourseId, semester, currentSemesterId);

                controller.insertUserData(data);


                Intent homeIntent = new Intent (SetupActivity.this, Overview.class);
                startActivity(homeIntent);
            }
        });

        List<City> allCities = controller.getAllCities();
        final List<String> cityNames = new ArrayList<>();
        cityNames.add(CITY_DEFAULT_SPINNER);
        for(City city : allCities) {
            cityNames.add(city.getName());
        }
        final Spinner spinnerCities = findViewById(R.id.spinnerCity);

        spinnerCities.setAdapter(AddSpinnerItems.setArrayAdapter(this, cityNames));

        setCitySpinnerSelectionListener(spinnerCities);
    }

    private void setCitySpinnerSelectionListener(Spinner spinnerCities) {
        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCity = parentView.getItemAtPosition(position).toString();
                Spinner spinnerUniversity = (Spinner) findViewById(R.id.spinnerUniversity);
                TextView universityTextView = (TextView) findViewById(R.id.textViewUniversity);

                if(CITY_DEFAULT_SPINNER.equals(selectedCity)) {
                    spinnerUniversity.setVisibility(View.INVISIBLE);
                    universityTextView.setVisibility(View.INVISIBLE);
                    findViewById(R.id.setupButtonForward).setVisibility(View.INVISIBLE);
                    findViewById(R.id.spinnerStartSemester).setVisibility(View.INVISIBLE);
                    findViewById(R.id.spinnerCourse).setVisibility(View.INVISIBLE);
                    findViewById(R.id.textViewStartSemester).setVisibility(View.INVISIBLE);
                    findViewById(R.id.textViewCourse).setVisibility(View.INVISIBLE);
                } else {
                    selectedCityId = controller.getCityIdByName(selectedCity);
                    List<String> availableUniversities = new ArrayList<>();
                    availableUniversities.add(UNIVERSITY_DEFAULT_SPINNER);
                    availableUniversities.addAll(controller.getUniversitiesByCityId(selectedCityId));

                    spinnerUniversity.setAdapter(AddSpinnerItems.setArrayAdapter(getApplication(), availableUniversities));

                    setUniversitySpinnerSelectionListener(spinnerUniversity);

                    spinnerUniversity.setVisibility(View.VISIBLE);
                    universityTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    private void setUniversitySpinnerSelectionListener(Spinner spinnerUniversity) {
        spinnerUniversity.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedUniversity = parent.getItemAtPosition(position).toString();
                Spinner spinnerCourse = (Spinner) findViewById(R.id.spinnerCourse);
                TextView textViewCourse = (TextView) findViewById(R.id.textViewCourse);

                if(UNIVERSITY_DEFAULT_SPINNER.equals(selectedUniversity)) {
                    spinnerCourse.setVisibility(View.INVISIBLE);
                    textViewCourse.setVisibility(View.INVISIBLE);
                    findViewById(R.id.setupButtonForward).setVisibility(View.INVISIBLE);
                    findViewById(R.id.spinnerStartSemester).setVisibility(View.INVISIBLE);
                    findViewById(R.id.textViewStartSemester).setVisibility(View.INVISIBLE);
                } else {
                    selectedUniversityId = controller.getUniversityIdByName(selectedUniversity);
                    List<String> availableCourses = new ArrayList<>();
                    availableCourses.add(COURSE_DEFAULT_SPINNER);
                    availableCourses.addAll(controller.getCoursesByUniversityId(selectedUniversityId));

                    spinnerCourse.setAdapter(AddSpinnerItems.setArrayAdapter(getApplication(), availableCourses));

                    setCourseSpinnerSelectionListener(spinnerCourse);

                    spinnerCourse.setVisibility(View.VISIBLE);
                    textViewCourse.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setCourseSpinnerSelectionListener(final Spinner spinnerCourse) {
        spinnerCourse.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCourse = parent.getItemAtPosition(position).toString();
                Spinner spinnerStart = (Spinner) findViewById(R.id.spinnerStartSemester);
                TextView textViewStart = (TextView) findViewById(R.id.textViewStartSemester);

                if(COURSE_DEFAULT_SPINNER.equals(selectedCourse)) {
                    spinnerStart.setVisibility(View.INVISIBLE);
                    textViewStart.setVisibility(View.INVISIBLE);
                    findViewById(R.id.setupButtonForward).setVisibility(View.INVISIBLE);
                    findViewById(R.id.spinnerStartSemester).setVisibility(View.INVISIBLE);
                    findViewById(R.id.textViewStartSemester).setVisibility(View.INVISIBLE);
                } else {
                    selectedCourseId = controller.getCourseIdByName(selectedCourse);

                    List<String> semesterNameString = new ArrayList<>();

                    semesterNameString.add(SEMESTER_DEFAULT_SPINNER);

                    semesterNameString.addAll(getSemesterCount());

                    spinnerStart.setAdapter(AddSpinnerItems.setArrayAdapter(getApplication(), semesterNameString));

                    setStartSemesterSpinnerSelectionListener(spinnerStart);

                    spinnerStart.setVisibility(View.VISIBLE);
                    textViewStart.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setStartSemesterSpinnerSelectionListener(Spinner spinnerStart) {
        spinnerStart.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedStartSemester = parent.getItemAtPosition(position).toString();
                Button button = (Button) findViewById(R.id.setupButtonForward);

                if(SEMESTER_DEFAULT_SPINNER.equals(selectedStartSemester)) {
                    button.setVisibility(View.INVISIBLE);
                } else {
                    semester = Integer.parseInt(selectedStartSemester);

                    button.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private List<String> getSemesterCount() {
        List<String> semesterCount = new ArrayList<>();
        for( int i = 1; i<= 10; i++) {
            semesterCount.add(Integer.toString(i));
        }
        return semesterCount;
    }
}
