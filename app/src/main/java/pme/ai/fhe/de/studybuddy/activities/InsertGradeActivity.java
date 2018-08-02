package pme.ai.fhe.de.studybuddy.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

/**
 * In this activity is it possible to insert lectures you passed. On the one hand you can insert grades or only that you passed
 * and on the other hand you can insert older grades, of the last semesters
 */
public class InsertGradeActivity extends MenuActivity {

    private static String LECTURE_SPINNER_DEFAULT = "Vorlesungsfach wählen";
    private static String SEMESTER_SPINNER_DEFAULT = "Semester wählen";

    private Spinner lectureSpinner, semesterSpinner;
    private EditText editTextGrade;
    private Switch semesterSwitch;


    /**
     * connects the layout with the xml files and opens the menu
     * @param savedInstanceState reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_grade);
        openMenu();

        lectureSpinner = findViewById(R.id.spinnerLecture);
        setTitle("Noteneingabe");


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


    /**
     * listens to the button to insert the lecture with a grade. Proves if everything is inserted right and
     * saves the grade in the database afterwards
     * @param insertButton the clicked button
     * @param lectureList list of all lectures
     */
    private void setupInsertGradeButton(Button insertButton, final List<Lecture> lectureList) {
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!lectureSpinner.getSelectedItem().toString().equals(LECTURE_SPINNER_DEFAULT) || !editTextGrade.getText().toString().equals("")) {
                    String selectedLectureString = lectureSpinner.getSelectedItem().toString();
                    float grade = Float.parseFloat(editTextGrade.getText().toString());
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
                                Toast.makeText(getApplicationContext(), "Bitte ein Semester wählen", Toast.LENGTH_LONG).show();
                                return;
                            }
                        } else {
                            selectedLecture.setSemesterPassed(controller.getUserData().getCurrentSemesterId());
                        }
                        if(grade >= 1.0 && grade <= 5.0) {
                            selectedLecture.setGrade(grade);
                            controller.updateLecture(selectedLecture);

                            resetView();

                            Toast.makeText(getApplicationContext(), "Note wurde erfolgreich eingetragen", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Bitte gib eine sinnvolle Note ein!", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Bitte eine Vorlesung wählen", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    /**
     * listens to the button to insert the lecture without a grade. Proves if everything is inserted right and
     * saves the grade in the database afterwards
     * @param insertNoGradeButton the clicked button
     * @param lectureList list of all lectures
     */
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

                        resetView();

                        Toast.makeText(getApplicationContext(), "Note wurde erfolgreich eingetragen", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Bitte eine Vorlesung wählen", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * if the switch is checked you can choose an older semester fpr inserting the grade
     * @param context the context in which this function is called
     */
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

    /**
     * resets the view after a grade was inserted, so you can choose another grade
     */
    private void resetView() {

        //hide keyboard
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


        semesterSpinner.setSelection(0);
        semesterSwitch.setChecked(false);
        lectureSpinner.setSelection(0);
        editTextGrade.setText("");
        findViewById(R.id.mainLayout).requestFocus();
    }
}
