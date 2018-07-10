package pme.ai.fhe.de.studybuddy.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.UserData;

public class InsertGrade extends MenuActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_grade);
        openMenu();

        setTitle("Noten eintragen");


        final Spinner lectureSpinner = findViewById(R.id.spinnerLecture);
        Button insertButton = findViewById(R.id.buttonInsertGrade);
        Button insertNoGradeButton = findViewById(R.id.buttonInsertNoGrade);
        final EditText editTextGrade = findViewById(R.id.insertGrade);
        findViewById(R.id.mainLayout).requestFocus();

        UserData userData = controller.getUserData();

        final List<Lecture> lectureList = controller.getLecturesByCourseId(userData.getCourseId());

        final List<String> lectureNameSpinner = new ArrayList<>();
        lectureNameSpinner.add("Vorlesungsfach wählen");
        List<Lecture> lectureGrade = new ArrayList<>();

        for (Lecture lecture : lectureList) {
            if(lecture.getGrade() != 0.0) {
                lectureGrade.add(lecture);
            }
            lectureNameSpinner.add(lecture.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.simple_custom_spinner_item, lectureNameSpinner);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lectureSpinner.setAdapter(dataAdapter);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!lectureSpinner.getSelectedItem().toString().equals(lectureNameSpinner.get(0)) || !editTextGrade.getText().toString().equals("")) {
                    String selectedItem = lectureSpinner.getSelectedItem().toString();
                    String grade = editTextGrade.getText().toString();
                    Lecture selectedLecture = null;
                    for(Lecture lecture : lectureList){
                        if(lecture.getName().equals(selectedItem)) {
                            selectedLecture = lecture;
                            break;
                        }
                    }

                    if(selectedLecture != null) {
                        selectedLecture.setGrade(Float.parseFloat(grade));
                        controller.updateLecture(selectedLecture);
                    } else {
                        Log.i("Grade insert", "Grade couldn't be inserted");
                    }

                } else {
                    Log.i("Grade insert", "Please fill all required panels");
                }
            }
        });

        insertNoGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!lectureSpinner.getSelectedItem().toString().equals(lectureNameSpinner.get(0)) || !editTextGrade.getText().toString().equals("")) {
                    String selectedItem = lectureSpinner.getSelectedItem().toString();
                    String grade = editTextGrade.getText().toString();
                    Lecture selectedLecture = null;
                    for(Lecture lecture : lectureList){
                        if(lecture.getName().equals(selectedItem)) {
                            selectedLecture = lecture;
                            break;
                        }
                    }

                    if(selectedLecture != null) {
                        selectedLecture.setGrade(-1.0f);
                        controller.updateLecture(selectedLecture);
                    } else {
                        Log.i("Grade insert", "Grade couldn't be inserted");
                    }

                } else {
                    Log.i("Grade insert", "Please fill all required panels");
                }
            }
        });
    }
}
