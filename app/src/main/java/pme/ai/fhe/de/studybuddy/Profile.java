package pme.ai.fhe.de.studybuddy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Profile extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        openMenu();

        final Button button = findViewById(R.id.newstudy);
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Profile.this.onClick();
            }
        }));

    }


    void onClick()
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
    }

    void startNewStudyActivity()
    {
        startActivity(new Intent(this, NewStudy.class));

    }
}



