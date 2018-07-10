package pme.ai.fhe.de.studybuddy.activities;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import pme.ai.fhe.de.studybuddy.R;
import pme.ai.fhe.de.studybuddy.administration.DataController;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000; //Time of the Splash Screen
    private static String DATABASE_NAME = "StudyBuddy_DB"; //Includes Database
    private ImageView iv;
    private DataController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        iv.startAnimation(myanim);

        controller = DataController.getInstance(getApplication());

       /* CourseOfStudies computerScience = new CourseOfStudies("Angewandte Informatik", 7, 210, "GET", 1, 1);
        CourseOfStudies computerScience2 = new CourseOfStudies("Medieninformatik", 7, 210, "GET", 5, 2);
        DataController controller = new DataController(getApplication());
        controller.insertCourse(computerScience);
        controller.insertCourse(computerScience2);*/


        //handler brings splash screen to the next activity
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                if(controller.getUserData() == null) {
                    Intent homeIntent = new Intent (SplashActivity.this, SetupActivity.class);
                    startActivity(homeIntent);
                } else {
                    Intent homeIntent = new Intent (SplashActivity.this, Overview.class);
                    startActivity(homeIntent);
                }
                finish();
            }

        },SPLASH_TIME_OUT);

    }
}
