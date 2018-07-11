package pme.ai.fhe.de.studybuddy.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.util.List;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.UserData;


public class Grades extends MenuActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        tableLayout = (TableLayout) findViewById(R.id.tableElement);

        tableLayout.getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorDarkerGrey));
        tableLayout.getChildAt(2).setBackgroundColor(getResources().getColor(R.color.colorMiddleGrey));

        setTitle("Notenübersicht");

        openMenu();

        TextView allCreditsView = findViewById(R.id.allCredits);
        TextView averageGradeView = findViewById(R.id.averageGrade);

        UserData userData = controller.getUserData();

        final List<Lecture> lectureList = controller.getAllLecturesWithGradeOrderBySemester(userData.getCourseId());

        setAverageGrade(lectureList, allCreditsView, averageGradeView);

        for (int i = 4; i < tableLayout.getChildCount(); i++) {
            if(i%2 == 0) {
                tableLayout.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
            } else {
                tableLayout.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.colorMiddleGrey));
            }
        }

    }

    private void setAverageGrade(List<Lecture> lectures, TextView allCreditsView, TextView averageGradeView) {
        int allCredits = 0;
        float allCreditsGrade = 0;
        float allGrades = 0;
        DecimalFormat formatOne = new DecimalFormat("#.#");
        DecimalFormat formatTwo = new DecimalFormat("#.##");

        for (Lecture lecture : lectures) {
            float grade = 0.0f;
            grade = lecture.getGrade();
            if (grade != 0.0 && grade < 4.0) {
                String gradeText = "";
                int credits = lecture.getCredits();
                allCredits += credits;
                if(grade == -1.0f) {
                    gradeText = "Bestanden";
                } else {
                    allGrades += grade * credits;
                    allCreditsGrade += credits;
                    gradeText = String.valueOf(formatOne.format(grade));
                }
                updateGradeTable(gradeText, lecture.getName(), lecture.getCredits(), lecture.getSemesterPassed());
            }
        }
        allCreditsView.setText(String.valueOf(allCredits));

        averageGradeView.setText(String.valueOf(formatTwo.format(allGrades/allCreditsGrade)));
    }

    private void updateGradeTable(String gradeText, String lectureName, int credits, int semesterId) {

        TableRow newRow = new TableRow(this);

        TextView lectureNameView = new TextView(this);
        lectureNameView.setText(lectureName);
        lectureNameView.setPadding(3,3,3,3);
        lectureNameView.setTextColor(getResources().getColor(R.color.colorBlack));

        TextView semesterTextView = new TextView(this);
        semesterTextView.setText(controller.getSemesterById(semesterId));
        semesterTextView.setPadding(3,3,3,3);
        semesterTextView.setGravity(Gravity.END);
        semesterTextView.setTextColor(getResources().getColor(R.color.colorBlack));


        TextView lectureCreditsView = new TextView(this);
        lectureCreditsView.setText(String.valueOf(credits));
        lectureCreditsView.setPadding(3,3,3,3);
        lectureCreditsView.setGravity(Gravity.END);
        lectureCreditsView.setTextColor(getResources().getColor(R.color.colorBlack));

        TextView gradeView = new TextView(this);
        gradeView.setText(gradeText);
        gradeView.setPadding(3,3,3,3);
        gradeView.setGravity(Gravity.END);
        gradeView.setTextColor(getResources().getColor(R.color.colorBlack));

        newRow.addView(lectureNameView);
        newRow.addView(semesterTextView);
        newRow.addView(lectureCreditsView);
        newRow.addView(gradeView);

        tableLayout.addView(newRow);
    }
}
