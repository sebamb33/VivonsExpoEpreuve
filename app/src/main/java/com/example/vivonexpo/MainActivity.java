package com.example.vivonexpo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

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



        //je recupere mon bouton avec son ID
        final Button buttonInscription = (Button) findViewById(R.id.buttonInscription);
        //je rajoute un evenement click sur le boutton
        buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //J'appelle la classe InscriptionActivity
                Intent intent = new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });

        // Tout les bouttonUnivers Vont vers VisiteurActivity
        final Button buttonUniversVivonMaison =(Button) findViewById((R.id.buttonUniversMaison));
        final Button buttonUniversSport=(Button)findViewById(R.id.buttonUniversSport);
        final Button buttonUniversFetes=(Button)findViewById(R.id.buttonUniversFetes);
        final Button buttonUniversAuto=(Button)findViewById(R.id.buttonUniversAuto);
        final Button buttonUniversEvaion=(Button)findViewById(R.id.buttonUniversEvasion);

        buttonUniversVivonMaison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String univ = "Maison";
                Intent intent= new Intent(MainActivity.this,VisiteurActivity.class);
                intent.putExtra("univers", univ );
                startActivity(intent);
            }
        });


        buttonUniversAuto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String univ = "Auto";
                Intent intent= new Intent(MainActivity.this,VisiteurActivity.class);
                intent.putExtra("univers", univ );
                startActivity(intent);
            }
        });
        buttonUniversFetes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String univ = "Les fêtes";
                Intent intent= new Intent(MainActivity.this,VisiteurActivity.class);
                intent.putExtra("univers", univ );
                startActivity(intent);
            }
        });
        buttonUniversEvaion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String univ ="Evasion";
                Intent intent= new Intent(MainActivity.this,VisiteurActivity.class);
                intent.putExtra("univers", univ );
                startActivity(intent);
            }
        });
        buttonUniversSport.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String univ ="Sport";
                Intent intent= new Intent(MainActivity.this,VisiteurActivity.class);
                intent.putExtra("univers", univ );
                startActivity(intent);
            }
        });


    }


}
