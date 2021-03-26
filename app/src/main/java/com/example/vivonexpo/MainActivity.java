package com.example.vivonexpo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //je recupere mon bouton avec sont ID
        final Button buttonConnexion = (Button) findViewById(R.id.buttonConnexion);
        //je rajoute un evenement click sur le boutton
        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //J'appelle la classe ConnexionActivity
                Intent intent = new Intent(MainActivity.this, ConnexionActivity.class);
                startActivity(intent);
            }
        });
        // Tout les bouttonUnivers Vont vers
        final Button buttonUniversVivonMaison =(Button) findViewById((R.id.buttonUniversMaison));

        buttonUniversVivonMaison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,VisiteurActivity.class);
                startActivity(intent);
            }
        });


    }


    }
