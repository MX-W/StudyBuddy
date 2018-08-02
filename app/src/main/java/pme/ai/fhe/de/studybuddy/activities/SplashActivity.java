package pme.ai.fhe.de.studybuddy.activities;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.administration.DataController;
import pme.ai.fhe.de.studybuddy.model.UserData;


/**
 * in the splash screen the logo of the application is shown
 */
public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000; //Time of the Splash Screen
    private static String DATABASE_NAME = "StudyBuddy_DB"; //Includes Database
    private ImageView iv;
    private DataController controller;

    /**
     * connects the layout with the xml files and opens the menu
     * loads the user data and checks if a new semester has started
     * @param savedInstanceState reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        controller = DataController.getInstance(getApplication());

        UserData userData = controller.getUserData();

        if(userData != null) {
            int currentSemester = userData.getSemester();
            GregorianCalendar now = new GregorianCalendar();
            GregorianCalendar database = userData.getLastLogin();

            if(database.get(Calendar.YEAR) < now.get(Calendar.YEAR)) {
                if(now.get(Calendar.MONTH) >= 4) {
                    currentSemester += 2;
                } else {
                    currentSemester++;
                }
            } else if(now.get(Calendar.MONTH) >= 4 && now.get(Calendar.MONTH) <= 9) {
                currentSemester++;
            } else if (now.get(Calendar.MONTH) >= 10) {
                currentSemester += 2;
            }

            userData.setSemester(currentSemester);
            userData.setLastLogin(now);

            controller.updateUserData(userData);

        }

        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        iv.startAnimation(myanim);

        /**
         * handler brings splash screen to the right next activity after the set time
         */
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                if(controller.getUserData() == null) {
                    Intent homeIntent = new Intent (SplashActivity.this, SetupActivity.class);
                    startActivity(homeIntent);
                } else {
                    Intent homeIntent = new Intent (SplashActivity.this, OverviewActivity.class);
                    startActivity(homeIntent);
                }
                finish();
            }

        },SPLASH_TIME_OUT);

    }
}
