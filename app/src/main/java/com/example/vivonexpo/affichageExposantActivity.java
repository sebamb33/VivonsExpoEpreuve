package com.example.vivonexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class affichageExposantActivity extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_exposant);
        //Instancie les textView
        TextView txtTitre =(TextView) findViewById(R.id.texteNomEntreprise);
        TextView txtSIte=(TextView) findViewById(R.id.texteSite);
        TextView txtAnInscription=(TextView)findViewById(R.id.texteAnneInscription);
        Intent intentResultat = getIntent();
        //J'enleve la partie nom de l'entreprise avec le substring
        String nomEntreprise = intentResultat.getExtras().getString("raisonSoc").substring(22).toLowerCase();
        txtTitre.setText(nomEntreprise);

        //J'effectue ma requete pour réucpérer toutes les informations  avec le nom de l'entreprise
        RequestBody formBody = new FormBody.Builder()
                .add("rs", nomEntreprise)
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.1.2/vivonexpo/vivonexpo/web/controleurs/affichageExposant.php")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {


            @Override

            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                JSONObject retourRequete= null;
                try {
                    retourRequete = new JSONObject(response.body().string());
                    txtTitre.setText("activite : "+retourRequete.getString("activite"));
                    txtSIte.setText(" site : "+retourRequete.getString("siteInternet"));
                    txtAnInscription.setText("année d'inscription : "+retourRequete.getString("anneeInscription"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //rien
            }
        });

    }
}