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


/**
 * In this activity the chosed data can be watched and a new study started
 */
public class ProfileActivity extends MenuActivity {


    UserData userData;

    /**
     * connects the layout with the xml files and opens the menu
     * @param savedInstanceState reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        openMenu();
        setTitle("Informationen");

        userData = controller.getUserData();

        setRightData();


        final Button button = findViewById(R.id.newstudy);
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ProfileActivity.this.onClick();
            }
        }));

    }

    /**
     * connects to database and get the userchosen data
     */
    private void setRightData() {

        TextView univserity = (TextView) findViewById(R.id.highschool_answer);
        univserity.setText(controller.getUniversityById(userData.getUniversityId()));

        TextView course = (TextView) findViewById(R.id.study_answer);
        course.setText(controller.getCourseById(userData.getCourseId())); //aus DB

        TextView semester = (TextView) findViewById(R.id.semester_answer);
        semester.setText(userData.getSemester() + ". Semester"); //aus DB

        TextView studystart = (TextView) findViewById(R.id.studystart_answer);
        studystart.setText(controller.getSemesterById((userData.getCurrentSemesterId() - (userData.getSemester() - 1)))); //aus DB
    }


    /**
     * function listens to the button and if he´s clicked it starts a dialog if he really want to stop is actual study
     * @return returns if it worked
     */
    boolean onClick()
    {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(ProfileActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(ProfileActivity.this);
        }
        builder.setTitle("Neues Studium")
                .setMessage("Bist du dir sicher, dass du dein aktuellen Studium löschen und ein Neues starten willst?")
                .setPositiveButton("Ja, Neuanfang!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        controller.resetAllGrades();
                        controller.deleteUserData(userData.getId());
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


    /**
     * calls the setup activity
     * @return returns if it worked
     */
    boolean startNewStudyActivity()
    {
        startActivity(new Intent(this, SetupActivity.class));
        return true;
    }
}



