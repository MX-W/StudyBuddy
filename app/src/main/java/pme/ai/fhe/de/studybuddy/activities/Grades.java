package pme.ai.fhe.de.studybuddy.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.UserData;


public class Grades extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        openMenu();

        final Spinner lectureSpinner = findViewById(R.id.spinnerLecture);
        Button insertButton = findViewById(R.id.buttonInsertGrade);
        final EditText editTextGrade = findViewById(R.id.insertGrade);

        UserData userData = controller.getUserData();

        final List<Lecture> lectureList = controller.getLecturesByCourseId(userData.getCourseId());

        final List<String> lectureNameList = new ArrayList<>();
        lectureNameList.add("Vorlesungsfach wählen");
        for (Lecture lecture : lectureList) {
            lectureNameList.add(lecture.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.simple_custom_spinner_item, lectureNameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lectureSpinner.setAdapter(dataAdapter);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!lectureSpinner.getSelectedItem().toString().equals(lectureNameList.get(0)) || !editTextGrade.getText().toString().equals("")) {
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
                        controller.updateGrade(selectedLecture);
                    } else {
                        Log.i("Grade insert", "Grade couldn't be inserted");
                    }

                } else {
                    Log.i("Grade insert", "Please fill all required panels");
                }
            }
        });

        TableLayout tableLayout = (TableLayout) findViewById(R.id.gradesTable);

        TableRow newRow = new TableRow(this);

        TextView lectureName = new TextView(this);
        lectureName.setText(R.string.test);
        lectureName.setPadding(3,3,3,3);
        lectureName.setTextColor(getResources().getColor(R.color.colorBlack));

        TextView grade = new TextView(this);
        grade.setText(R.string.test);
        grade.setPadding(3,3,3,3);
        grade.setGravity(Gravity.RIGHT);
        grade.setTextColor(getResources().getColor(R.color.colorBlack));

        newRow.addView(lectureName);
        newRow.addView(grade);

        tableLayout.addView(newRow);
    }
}
