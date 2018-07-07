package pme.ai.fhe.de.studybuddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.model.City;
import pme.ai.fhe.de.studybuddy.administration.DataController;
import pme.ai.fhe.de.studybuddy.model.UserData;

public class SetupActivity extends AppCompatActivity {

    DataController controller;
    int selectedCityId, selectedUniversityId, selectedCourseId, semesterCountStarting, startingSemesterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_setup);
        controller = DataController.getInstance(getApplication());

        findViewById(R.id.setupButtonForward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserData data = new UserData(selectedCityId, selectedUniversityId, selectedCourseId, semesterCountStarting, startingSemesterId);

                controller.insertUserData(data);


                Intent homeIntent = new Intent (SetupActivity.this, Overview.class);
                startActivity(homeIntent);
            }
        });

        List<City> allCities = controller.getAllCities();
        final List<String> cityNames = new ArrayList<>();
        cityNames.add("Bitte wähle deine Stadt");
        for(City city : allCities) {
            cityNames.add(city.getName());
        }
        final Spinner spinnerCities = findViewById(R.id.spinnerCity);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.simple_custom_spinner_item, cityNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCities.setAdapter(dataAdapter);

        setCitySpinnerSelectionListener(spinnerCities, cityNames);
    }

    private void setCitySpinnerSelectionListener(Spinner spinnerCities, final List<String> cityNames) {
        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCity = parentView.getItemAtPosition(position).toString();
                Spinner spinnerUniversity = (Spinner) findViewById(R.id.spinnerUniversity);
                TextView universityTextView = (TextView) findViewById(R.id.textViewUniversity);

                if(cityNames.get(0).equals(selectedCity)) {
                    spinnerUniversity.setVisibility(View.INVISIBLE);
                    universityTextView.setVisibility(View.INVISIBLE);
                    findViewById(R.id.setupButtonForward).setVisibility(View.INVISIBLE);
                    findViewById(R.id.spinnerStartSemester).setVisibility(View.INVISIBLE);
                    findViewById(R.id.spinnerBegin).setVisibility(View.INVISIBLE);
                    findViewById(R.id.spinnerCourse).setVisibility(View.INVISIBLE);
                    findViewById(R.id.textViewStartSemester).setVisibility(View.INVISIBLE);
                    findViewById(R.id.textViewBegin).setVisibility(View.INVISIBLE);
                    findViewById(R.id.textViewCourse).setVisibility(View.INVISIBLE);
                } else {
                    selectedCityId = controller.getCityIdByName(selectedCity);
                    List<String> availableUniversities = new ArrayList<>();
                    availableUniversities.add("Bitte wähle deine Universität");
                    availableUniversities.addAll(controller.getUniversitiesByCityId(selectedCityId));

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(parentView.getContext(),
                            R.layout.simple_custom_spinner_item, availableUniversities);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerUniversity.setAdapter(dataAdapter);

                    setUniversitySpinnerSelectionListener(spinnerUniversity, availableUniversities);

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

    private void setUniversitySpinnerSelectionListener(Spinner spinnerUniversity, final List<String> universityNames) {
        spinnerUniversity.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedUniversity = parent.getItemAtPosition(position).toString();
                Spinner spinnerCourse = (Spinner) findViewById(R.id.spinnerCourse);
                TextView textViewCourse = (TextView) findViewById(R.id.textViewCourse);

                if(universityNames.get(0).equals(selectedUniversity)) {
                    spinnerCourse.setVisibility(View.INVISIBLE);
                    textViewCourse.setVisibility(View.INVISIBLE);
                    findViewById(R.id.setupButtonForward).setVisibility(View.INVISIBLE);
                    findViewById(R.id.spinnerStartSemester).setVisibility(View.INVISIBLE);
                    findViewById(R.id.spinnerBegin).setVisibility(View.INVISIBLE);
                    findViewById(R.id.textViewStartSemester).setVisibility(View.INVISIBLE);
                    findViewById(R.id.textViewBegin).setVisibility(View.INVISIBLE);
                } else {
                    selectedUniversityId = controller.getUniversityIdByName(selectedUniversity);
                    List<String> availableCourses = new ArrayList<>();
                    availableCourses.add("Bitte wähle deinen Studiengang aus");
                    availableCourses.addAll(controller.getCoursesByUniversityId(selectedUniversityId));

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(parent.getContext(),
                            R.layout.simple_custom_spinner_item, availableCourses);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCourse.setAdapter(dataAdapter);

                    setCourseSpinnerSelectionListener(spinnerCourse, availableCourses);

                    spinnerCourse.setVisibility(View.VISIBLE);
                    textViewCourse.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setCourseSpinnerSelectionListener(final Spinner spinnerCourse, final List<String> courseNames) {
        spinnerCourse.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCourse = parent.getItemAtPosition(position).toString();
                Spinner spinnerBegin = (Spinner) findViewById(R.id.spinnerBegin);
                TextView textViewBegin = (TextView) findViewById(R.id.textViewBegin);

                if(courseNames.get(0).equals(selectedCourse)) {
                    spinnerBegin.setVisibility(View.INVISIBLE);
                    textViewBegin.setVisibility(View.INVISIBLE);
                    findViewById(R.id.setupButtonForward).setVisibility(View.INVISIBLE);
                    findViewById(R.id.spinnerStartSemester).setVisibility(View.INVISIBLE);
                    findViewById(R.id.textViewStartSemester).setVisibility(View.INVISIBLE);
                } else {
                    selectedCourseId = controller.getCourseIdByName(selectedCourse);

                    List<String> semester = new ArrayList<>();
                    controller.getAllSemester();
                    semester.add("Bitte wähle einen Studienbeginn aus");
                    semester.addAll();

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(parent.getContext(),
                            R.layout.simple_custom_spinner_item, semester);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerBegin.setAdapter(dataAdapter);

                    setBeginSpinnerSelectionListener(spinnerBegin, semester);

                    spinnerBegin.setVisibility(View.VISIBLE);
                    textViewBegin.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setBeginSpinnerSelectionListener(Spinner spinnerBegin, final List<String> semester) {
        spinnerBegin.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSemester = parent.getItemAtPosition(position).toString();
                Spinner spinnerStart = (Spinner) findViewById(R.id.spinnerStartSemester);
                TextView textViewStart = (TextView) findViewById(R.id.textViewStartSemester);

                if(semester.get(0).equals(selectedSemester)) {
                    spinnerStart.setVisibility(View.INVISIBLE);
                    textViewStart.setVisibility(View.INVISIBLE);
                    findViewById(R.id.setupButtonForward).setVisibility(View.INVISIBLE);
                } else {
                    startingSemesterId = selectedSemester;

                    List<String> semesterCount = new ArrayList<>();
                    semesterCount.add("Wähle dein Startsemester");
                    semesterCount.addAll(getSemesterCount());

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(parent.getContext(),
                            R.layout.simple_custom_spinner_item, semesterCount);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerStart.setAdapter(dataAdapter);

                    setStartSemesterSpinnerSelectionListener(spinnerStart, semesterCount);

                    spinnerStart.setVisibility(View.VISIBLE);
                    textViewStart.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setStartSemesterSpinnerSelectionListener(Spinner spinnerStart, final List<String> semesterCount) {
        spinnerStart.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedStartSemester = parent.getItemAtPosition(position).toString();
                Button button = (Button) findViewById(R.id.setupButtonForward);

                if(semesterCount.get(0).equals(selectedStartSemester)) {
                    button.setVisibility(View.INVISIBLE);
                } else {
                    semesterCountStarting = Integer.parseInt(selectedStartSemester);
                    button.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private List<String> getSemester() {
        Calendar now = Calendar.getInstance();

        int year = now.get(Calendar.YEAR);
        List<String> semester = new ArrayList<>();
        year += 1;

        for(int i = 0; i <= 7; i++) {
            String yearString = Integer.toString(year).substring(2,4);
            String yearStringPlus = Integer.toString(year+1).substring(2,4);
            semester.add("WS-" + yearString + "/" + yearStringPlus);
            semester.add("SS-" + yearString);
            year--;
        }

        return semester;

    }

    private List<String> getSemesterCount() {
        List<String> semesterCount = new ArrayList<>();
        for( int i = 1; i<= 10; i++) {
            semesterCount.add(Integer.toString(i));
        }
        return semesterCount;
    }
}
