package pme.ai.fhe.de.studybuddy.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.model.UserData;

public class Profile extends MenuActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        openMenu();
        setRightData();



        controller.getUserData();

        final Button button = findViewById(R.id.newstudy);
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Profile.this.onClick();
            }
        }));

    }

    private void setRightData() {
        UserData dataset = controller.getUserData();

        TextView univserity = (TextView) findViewById(R.id.highschool_answer);
        univserity.setText(controller.getUniversityById(dataset.getUniversityId()));

        TextView course = (TextView) findViewById(R.id.study_answer);
        course.setText(controller.getCourseById(dataset.getCourseId())); //aus DB

        TextView semester = (TextView) findViewById(R.id.semester_answer);
        semester.setText(dataset.getSemester() + ". Semester"); //aus DB

        TextView studystart = (TextView) findViewById(R.id.studystart_answer);
        studystart.setText(controller.getSemesterById((dataset.getCurrentSemesterId() - (dataset.getSemester() - 1)))); //aus DB
    }


    boolean onClick()
    {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(Profile.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(Profile.this);
        }
        builder.setTitle("Neues Studium")
                .setMessage("Bist du dir sicher, dass du dein aktuellen Studium löschen und ein Neues starten willst?")
                .setPositiveButton("Ja, Neuanfang!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        startNewStudyActivity();
                    }
                })
                .setNegativeButton("Oh, lieber doch nicht!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

        return true;
    }

    boolean startNewStudyActivity()
    {
        startActivity(new Intent(this, SetupActivity.class));
        return true;
    }
}



