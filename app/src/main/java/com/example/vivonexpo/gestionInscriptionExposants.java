package com.example.vivonexpo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class gestionInscriptionExposants extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    ArrayList toutExposants = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_inscription_exposants);
        Request request = new Request.Builder()
                .url("http://192.168.1.2/vivonexpo/vivonexpo/web/controleurs/gestionInscriptionExposant.php")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //je recupère tout les exposants avec inscription non validé
                String retourRequete = response.body().string();
                JSONArray mesExposants = new JSONArray();
                try {
                    mesExposants = new JSONArray(retourRequete);
                    Log.d("ici", "onResponse: "+mesExposants.toString());
                    for(int i=0;i<mesExposants.length();i++)
                    {
                        JSONObject unExposant;
                        unExposant= mesExposants.getJSONObject(i);
                        toutExposants.add("Nom de l'entreprise : "+unExposant.getString("raisonSociale") +"/ Acitivité de l'entreprise : "+unExposant.getString("activite"));
                        Log.d("expo",toutExposants.toString());

                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ListView listViewVisiteur = (ListView) findViewById(R.id.affichageExposantInscription);
                            ArrayAdapter<String> arrayAdapterExposant = new ArrayAdapter<String>(gestionInscriptionExposants.this, android.R.layout.simple_list_item_1, toutExposants);
                            listViewVisiteur.setAdapter(arrayAdapterExposant);
                        }
                    });
                    ListView listViewVisiteur = (ListView) findViewById(R.id.affichageExposantInscription);
                    listViewVisiteur.setOnItemClickListener(new AdapterView.OnItemClickListener()
                            {
                                String txtSelect="";
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    txtSelect= listViewVisiteur.getItemAtPosition(i).toString();
                                    String[] txtSplit;
                                    txtSplit=txtSelect.split("[/]");
                                    txtSelect=txtSplit[0].substring(22).toLowerCase();
                                    Log.d("messag", "onItemClick: "+txtSelect);

                                    RequestBody formBody = new FormBody.Builder()
                                            .add("rs", txtSelect)
                                            .build();
                                    Request request = new Request.Builder()
                                            .url("http://192.168.1.2/vivonexpo/vivonexpo/web/controleurs/inscriptionExposant.php")
                                            .post(formBody)
                                            .build();
                                    Call call = client.newCall(request);
                                    call.enqueue(new Callback() {
                                        @Override
                                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(gestionInscriptionExposants.this);
                                            alertDialogBuilder.setMessage("l'exposant "+txtSelect+" à était ajouté");
                                            alertDialogBuilder.setTitle("Nouveaux exposant");
                                            alertDialogBuilder.setCancelable(true);
                                            alertDialogBuilder.show();
                                        }

                                        @Override
                                        public void onFailure(@NotNull Call call, @NotNull IOException e) {

                                        }
                                    });
                                }
                            });
            }catch(Exception $e)
                {
                    //Si ça marche pas
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }
}