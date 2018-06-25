package pme.ai.fhe.de.studybuddy;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import pme.ai.fhe.de.studybuddy.model.CourseOfStudies;
import pme.ai.fhe.de.studybuddy.model.Daos.DataBase;
import pme.ai.fhe.de.studybuddy.model.Daos.DataController;

public class MainActivity extends AppCompatActivity {
private static int SPLASH_TIME_OUT = 4000;
private static String DATABASE_NAME = "StudyBuddy_DB";
private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        iv.startAnimation(myanim);

        DataBase db = DataBase.getDatabase(getApplicationContext());

        CourseOfStudies computerScience = new CourseOfStudies("Angewandte Informatik", 7, 210, "GET", 1, 1);
        CourseOfStudies computerScience2 = new CourseOfStudies("Medieninformatik", 7, 210, "GET", 5, 2);
        DataController controller = new DataController(getApplication());
        controller.insertCourse(computerScience);
        controller.insertCourse(computerScience2);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent (MainActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }

        },SPLASH_TIME_OUT);

    }
}
