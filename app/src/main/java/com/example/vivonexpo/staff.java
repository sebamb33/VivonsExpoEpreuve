package com.example.vivonexpo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;


public class staff extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);



        //Je recupere ma textview
        final TextView textNomStaff = (TextView)findViewById(R.id.textNomStaff);
        //je recupere le getExtra de mon intent
        Intent intentResultat = getIntent();
        String stringStaff = intentResultat.getStringExtra("staff");

        //initialisation de mon objet de JSON
        JSONObject objectStaff = null;
        try {
            //Je set mon objet JSON
            objectStaff = new JSONObject(stringStaff);
            textNomStaff.setText(objectStaff.getString("prenom") +" "+objectStaff.getString("nom"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

