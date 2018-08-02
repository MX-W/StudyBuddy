package pme.ai.fhe.de.studybuddy.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.model.Lecture;
import pme.ai.fhe.de.studybuddy.model.UserData;

/**
 * In this Activity is it possible to calculate which average is needed in the rest of the exams, to reach
 * the grade you want in the end
 */
public class GradeCalculationActivity extends MenuActivity {

    /**
     * sets title and opens the menu
     * @param savedInstanceState reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_calculation);

        setTitle("Wunschnote");

        openMenu();

        final Button button = findViewById(R.id.buttonCalculateGrade);
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradeCalculationActivity.this.onClick();
            }
        }));
    }


    /**
     * Is called when the button of the activity is pushed. It loads the data and calculates what grade is needed.
     * In the end it opens a dialog with the user to communicate him the answer
     * @return returns if the method works correctly
     */
    boolean onClick()
    {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


        final EditText calculationGrade = (EditText) findViewById(R.id.calculationGradeInput);

        if(!calculationGrade.getText().toString().isEmpty())
        {


            if(Float.valueOf(calculationGrade.getText().toString())>=1 && Float.valueOf(calculationGrade.getText().toString())<=4)
            {
                    //Note berechnen

                    int actualCredits = 0;
                    int actualCreditsWithGrade = 0;
                    float averageGrade = 0;
                    UserData dataset = controller.getUserData(); //gets all User Data
                    List<Lecture> allLecturesWithGrade = controller.getAllLecturesWithGrade(dataset.getCourseId()); //gets List with All Courses from User

                    for(Lecture l : allLecturesWithGrade)
                    {
                        actualCredits+=l.getCredits();

                        if(l.getGrade()>0)
                        {
                            averageGrade += l.getGrade()*l.getCredits();
                            actualCreditsWithGrade += l.getCredits();
                        }

                    }

                    averageGrade = averageGrade/actualCreditsWithGrade;

                    float neededGrade = (Float.valueOf(calculationGrade.getText().toString()) * 180 - averageGrade*actualCredits)/(180-actualCredits);

                    String message;

                    if (neededGrade >= 1.0 && neededGrade <= 4.0)
                        message = "Du brauchst einen Durschnitt von " + Math.round(neededGrade * 100) / 100.0+ " um eine Endnote von " + calculationGrade.getText() + " zu erreichen." ;
                    else if(neededGrade > 4.0)
                    {
                        message = "Du musst die restlichen Prüfungen nur bestehen!";

                    }
                    else
                        message = "Der Zug ist abgefahren...";



                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(GradeCalculationActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(GradeCalculationActivity.this);
                    }
                    builder.setTitle("Wunschnote")
                            .setMessage(message)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })

                            .show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Gib eine sinnvolle Note ein", Toast.LENGTH_LONG).show();

            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Du musst schon was eingeben!", Toast.LENGTH_LONG).show();

        }
        return true;
    }
}
