package pme.ai.fhe.de.studybuddy.activities;

import android.os.Bundle;

import pme.ai.fhe.de.studybuddy.R;

public class Overview extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        openMenu();
    }
}
