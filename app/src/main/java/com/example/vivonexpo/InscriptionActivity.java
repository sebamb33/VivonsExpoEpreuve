package com.example.vivonexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        //je recupere mon bouton avec son ID
        final Button buttonQuitter = (Button) findViewById(R.id.buttonInscriptionQuitter);
        //je rajoute un evenement click sur le boutton
        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //J'appelle la classe ConnexionActivity
                Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}