package pme.ai.fhe.de.studybuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Overview extends HomeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        openMenu();
    }
}
