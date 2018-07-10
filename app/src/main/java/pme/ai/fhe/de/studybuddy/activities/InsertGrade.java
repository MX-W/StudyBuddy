package pme.ai.fhe.de.studybuddy.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.Semester;
import pme.ai.fhe.de.studybuddy.model.UserData;
import pme.ai.fhe.de.studybuddy.utilities.AddSpinnerItems;

public class InsertGrade extends MenuActivity {

    private static String LECTURE_SPINNER_DEFAULT = "Vorlesungsfach wählen";
    private static String SEMESTER_SPINNER_DEFAULT = "Semester wählen";

    private Spinner lectureSpinner, semesterSpinner;
    private EditText editTextGrade;
    private Switch semesterSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_grade);
        openMenu();

        lectureSpinner = findViewById(R.id.spinnerLecture);
        setTitle("Noten eintragen");


        final Spinner lectureSpinner = findViewById(R.id.spinnerLecture);
        Button insertButton = findViewById(R.id.buttonInsertGrade);
        Button insertNoGradeButton = findViewById(R.id.buttonInsertNoGrade);
        editTextGrade = findViewById(R.id.insertGrade);
        findViewById(R.id.mainLayout).requestFocus();
        semesterSwitch = findViewById(R.id.switchOtherSemester);
        semesterSpinner = findViewById(R.id.spinnerSemester);

        UserData userData = controller.getUserData();

        final List<Lecture> lectureList = controller.getLecturesByCourseId(userData.getCourseId());

        final List<String> lectureNameSpinner = new ArrayList<>();
        lectureNameSpinner.add(LECTURE_SPINNER_DEFAULT);

        for (Lecture lecture : lectureList) {
            lectureNameSpinner.add(lecture.getName());
        }

        lectureSpinner.setAdapter(AddSpinnerItems.setArrayAdapter(this, lectureNameSpinner));


        setupInsertGradeButton(insertButton, lectureList);
        setupInsertNoGradeButton(insertNoGradeButton, lectureList);
        setupSemesterSwitchListener(this);

    }


    private void setupInsertGradeButton(Button insertButton, final List<Lecture> lectureList) {
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!lectureSpinner.getSelectedItem().toString().equals(LECTURE_SPINNER_DEFAULT) || !editTextGrade.getText().toString().equals("")) {
                    String selectedLectureString = lectureSpinner.getSelectedItem().toString();
                    String grade = editTextGrade.getText().toString();
                    Lecture selectedLecture = null;
                    for(Lecture lecture : lectureList){
                        if(lecture.getName().equals(selectedLectureString)) {
                            selectedLecture = lecture;
                            break;
                        }
                    }

                    if(selectedLecture != null) {
                        if(semesterSwitch.isChecked()) {
                            String selectedSemester = semesterSpinner.getSelectedItem().toString();
                            if(!selectedSemester.equals(SEMESTER_SPINNER_DEFAULT)) {
                                int semesterId = controller.getSemesterIdByName(selectedSemester);
                                selectedLecture.setSemesterPassed(semesterId);
                            } else {
                                Toast.makeText(getApplicationContext(), "Bitte Semester wählen", Toast.LENGTH_LONG).show();
                                return;
                            }
                        } else {
                            selectedLecture.setSemesterPassed(controller.getUserData().getCurrentSemesterId());
                        }

                        selectedLecture.setGrade(Float.parseFloat(grade));
                        controller.updateLecture(selectedLecture);

                        Toast.makeText(getApplicationContext(), "Note wurde erfolgreich eingetragen", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Bitte eine Vorlesung wählen", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setupInsertNoGradeButton(Button insertNoGradeButton, final List<Lecture> lectureList) {
        insertNoGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!lectureSpinner.getSelectedItem().toString().equals(LECTURE_SPINNER_DEFAULT) || !editTextGrade.getText().toString().equals("")) {
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
                        if(semesterSwitch.isChecked()) {
                            String selectedSemester = semesterSpinner.getSelectedItem().toString();
                            if(!selectedSemester.equals(SEMESTER_SPINNER_DEFAULT)) {
                                int semesterId = controller.getSemesterIdByName(selectedSemester);
                                selectedLecture.setSemesterPassed(semesterId);
                            } else {
                                Toast.makeText(getApplicationContext(), "Bitte Semester wählen", Toast.LENGTH_LONG).show();
                                return;
                            }
                        } else {
                            selectedLecture.setSemesterPassed(controller.getUserData().getCurrentSemesterId());
                        }
                        selectedLecture.setGrade(-1.0f);
                        controller.updateLecture(selectedLecture);

                        Toast.makeText(getApplicationContext(), "Note wurde erfolgreich eingetragen", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Bitte eine Vorlesung wählen", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setupSemesterSwitchListener(final Context context) {
        semesterSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(semesterSwitch.isChecked()) {
                    List<Semester> allSemester = controller.getAllSemester();
                    List<String> spinnerSemesterItems = new ArrayList<>();
                    UserData userData = controller.getUserData();
                    int userSemesterCount = userData.getSemester();
                    int userStartingSemesterId = userData.getCurrentSemesterId() - (userSemesterCount);

                    spinnerSemesterItems.add(SEMESTER_SPINNER_DEFAULT);

                    for(int i = 0; i < allSemester.size(); i++) {
                        if(allSemester.get(i).getSemesterId() > userStartingSemesterId && spinnerSemesterItems.size() <= (userSemesterCount +1)) {
                            spinnerSemesterItems.add(allSemester.get(i).getName());
                            Log.i("Adding", allSemester.get(i).getName());
                        }
                        if(spinnerSemesterItems.size() >= (userSemesterCount + 1)) {
                            Log.i("Adding", "break");
                            break;
                        }
                    }

                    semesterSpinner.setAdapter(AddSpinnerItems.setArrayAdapter(context, spinnerSemesterItems));

                    semesterSpinner.setVisibility(View.VISIBLE);
                } else {
                    semesterSpinner.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
