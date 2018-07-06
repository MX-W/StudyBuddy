package pme.ai.fhe.de.studybuddy.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import org.w3c.dom.Text;

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
                        controller.updateGrade(selectedLecture);
                        updateGradeTable(String.valueOf(selectedLecture.getGrade()), selectedLecture.getName(), selectedLecture.getId());
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
                        controller.updateGrade(selectedLecture);
                        updateGradeTable("Keine Note", selectedLecture.getName(), selectedLecture.getId());
                    } else {
                        Log.i("Grade insert", "Grade couldn't be inserted");
                    }

                } else {
                    Log.i("Grade insert", "Please fill all required panels");
                }
            }
        });


        String gradeText = "";

        for(Lecture lecture : lectureGrade) {
            float grade = lecture.getGrade();
            if(grade == -1.0f) {
                gradeText = "Keine Note";
            } else {
                gradeText = String.valueOf(grade);
            }
            updateGradeTable(gradeText, lecture.getName(), lecture.getId());
        }
    }

    private void updateGradeTable(String gradeText, String lectureName, int lectureId) {

        TableLayout tableLayout = (TableLayout) findViewById(R.id.gradesTable);
        boolean alreadyExisting = false;
        TableRow existingRow = null;

        for(int i = 0; i < tableLayout.getChildCount(); i++) {
            View v = (View) tableLayout.getChildAt(i);
            if (v instanceof TableRow) {
                TableRow row = (TableRow) v;
                TextView existingLectureIdView = (TextView) row.getChildAt(0);
                String existingId = existingLectureIdView.getText().toString();
                if(Integer.parseInt(existingId) == lectureId) {
                    alreadyExisting = true;
                    existingRow = row;
                    break;
                }
            }
        }

        if(alreadyExisting) {
            TextView viewAtIndex = (TextView) existingRow.getChildAt(2);
            viewAtIndex.setText(gradeText);
        }else {
            TableRow newRow = new TableRow(this);

            TextView idView = new TextView(this);
            idView.setText(String.valueOf(lectureId));
            idView.setVisibility(View.GONE);

            TextView lectureNameView = new TextView(this);
            lectureNameView.setText(lectureName);
            lectureNameView.setPadding(3,3,3,3);
            lectureNameView.setTextColor(getResources().getColor(R.color.colorBlack));

            TextView gradeView = new TextView(this);
            gradeView.setText(gradeText);
            gradeView.setPadding(3,3,3,3);
            gradeView.setGravity(Gravity.RIGHT);
            gradeView.setTextColor(getResources().getColor(R.color.colorBlack));

            newRow.addView(idView);
            newRow.addView(lectureNameView);
            newRow.addView(gradeView);

            tableLayout.addView(newRow);
        }
    }
}
