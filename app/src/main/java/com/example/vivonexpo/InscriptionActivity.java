package com.example.vivonexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class InscriptionActivity extends AppCompatActivity {
    String responseStr ;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        try {
            remplisageSpinnerSecteurs();
        }catch (IOException e) {
            e.printStackTrace();
        }

        //je recupere mon bouton avec son ID
        final Button buttonInscriptionQuitter = (Button) findViewById(R.id.buttonInscriptionQuitter);
        //je rajoute un evenement click sur le boutton de l'inscription
        buttonInscriptionQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //J'appelle la classe InscriptionActivity
                Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        final Button buttonInscriptionValider = (Button)findViewById(R.id.buttonInscriptionValider);
        buttonInscriptionValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Appel de la function responsable de l'inscription
                try {
                    inscription();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void inscription() throws IOException {


        final EditText textRaisonSociale = findViewById(R.id.editTextTextInscriptionRaisonSociale);
        final EditText textRaisonActivite = findViewById(R.id.editTextTextInscriptionActiviteEntreprise);
        final EditText textNom = findViewById(R.id.editTextTextInscriptionNom);
        final EditText textPrenom = findViewById(R.id.editTextTextInscriptionPrenom);
        final EditText textMail = findViewById(R.id.editTextTextInscriptionMail);
        final EditText textLogin = findViewById(R.id.editTextTextInscriptionLogin);
        final EditText textMdp = findViewById(R.id.editTextTextInscriptionMdp);
        final EditText textTelephone = findViewById(R.id.editTextTextInscriptionTelephone);
        final EditText textSite = findViewById(R.id.editTextTextInscriptionSite);

        if (textRaisonSociale.getText().toString().equals("") ||textRaisonActivite.getText().toString().equals("") || textNom.getText().toString().equals("") || textPrenom.getText().toString().equals("") ||
        textMail.getText().toString().equals("") || textLogin.getText().toString().equals("") || textMdp.getText().toString().equals("") || textTelephone.getText().toString().equals("") ||
        textSite.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs dans un format correct", Toast.LENGTH_LONG).show();

        } else {

            Spinner mySpinner = (Spinner) findViewById(R.id.spinnerSecteur);

            Switch switchExposition = (Switch) findViewById(R.id.switchInscriptionExposition);
            String switchExpositionState = "0";
            if (switchExposition.isChecked()){
                switchExpositionState = "1";
            }

            RequestBody formBody = new FormBody.Builder()
                    .add("login", textLogin.getText().toString())
                    .add("mdp",  textMdp.getText().toString())
                    .add("nom", textNom.getText().toString())
                    .add("prenom",  textPrenom.getText().toString())
                    .add("telephone", textTelephone.getText().toString())
                    .add("mail",  textMail.getText().toString())
                    .add("raisonSociale",  textRaisonSociale.getText().toString())
                    .add("activite", textRaisonActivite.getText().toString())
                    .add("siteInternet",  textSite.getText().toString())
                    .add("dejaExpose",  switchExpositionState)
                    .add("libelleSecteur", mySpinner.getSelectedItem().toString())
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.1.91/vivonExpo/web/controleurs/inscription.php")
                    .post(formBody)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                public  void onResponse(Call call, Response response) throws IOException {

                }

                public void onFailure(Call call, IOException e)
                {
                    Log.d("Test","erreur!!! connexion impossible");
                    Log.d("Test",e.getMessage());
                }
            });
        }
    }


    public void remplisageSpinnerSecteurs() throws IOException {

        Request request = new Request.Builder()
                .url("http://192.168.1.91/vivonExpo/web/controleurs/getLibelleSecteurs.php")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public  void onResponse(Call call, Response response) throws IOException {
                responseStr = response.body().string();

                try {
                    JSONArray arrayJson = new JSONArray(responseStr);
                    ArrayList<String> arrayLibelleStecteurs = new ArrayList<String>();
                    JSONObject row;
                    for (int i = 0; i < arrayJson.length(); i++) {
                        row = arrayJson.getJSONObject(i);
                        arrayLibelleStecteurs.add(row.getString("libelleS"));
                    }

                    Spinner s = (Spinner) findViewById(R.id.spinnerSecteur);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(InscriptionActivity.this,
                            android.R.layout.simple_spinner_item, arrayLibelleStecteurs);
                    s.setAdapter(adapter);

                } catch (JSONException e) {
                    Log.d("Test", "Pas de secteurs");
                    e.printStackTrace();
                }

            }

            public void onFailure(Call call, IOException e)
            {
                Log.d("Test","erreur!!! connexion impossible");
                Log.d("Test",e.getMessage());
            }
        });
    }





}