package pme.ai.fhe.de.studybuddy;

import android.os.Bundle;

public class Overview extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        openMenu();
    }
}