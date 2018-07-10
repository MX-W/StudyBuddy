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

import java.text.DecimalFormat;
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

        TextView allCreditsView = findViewById(R.id.allCredits);
        TextView averageGradeView = findViewById(R.id.averageGrade);

        UserData userData = controller.getUserData();

        final List<Lecture> lectureList = controller.getLecturesByCourseId(userData.getCourseId());

        List<Lecture> lectureGrade = new ArrayList<>();

        int allCredits = 0;
        float allCreditsGrade = 0;
        float allGrades = 0;
        DecimalFormat formatOne = new DecimalFormat("#.#");
        DecimalFormat formatTwo = new DecimalFormat("#.##");

        for (Lecture lecture : lectureList) {
            float grade = 0.0f;
            grade = lecture.getGrade();
            if (grade != 0.0) {
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
                updateGradeTable(gradeText, lecture.getName(), lecture.getCredits());
            }
        }

        allCreditsView.setText(String.valueOf(allCredits));

        averageGradeView.setText(String.valueOf(formatTwo.format(allGrades/allCreditsGrade)));
    }

    private void updateGradeTable(String gradeText, String lectureName, int credits) {

        TableLayout tableLayout = (TableLayout) findViewById(R.id.gradesTable);

        TableRow newRow = new TableRow(this);

        TextView lectureNameView = new TextView(this);
        lectureNameView.setText(lectureName);
        lectureNameView.setPadding(3,3,3,3);
        lectureNameView.setTextColor(getResources().getColor(R.color.colorBlack));

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
        newRow.addView(lectureCreditsView);
        newRow.addView(gradeView);

        tableLayout.addView(newRow);
    }
}
