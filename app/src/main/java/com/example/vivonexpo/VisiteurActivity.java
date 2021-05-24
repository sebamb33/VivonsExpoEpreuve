package com.example.vivonexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class VisiteurActivity extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();
    String retourRequete;
    ArrayList toutExposants = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiteur);
        try {
            chercheExposant();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void chercheExposant() throws IOException
    {
        Intent intentResultat = getIntent();
        String univers = intentResultat.getExtras().getString("univers");
        TextView entete=(TextView)findViewById(R.id.texteUnivers);
        entete.setText("univers "+univers);
        RequestBody formBody = new FormBody.Builder()
                .add("univers", univers)
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.1.2/vivonexpo/vivonexpo/web/controleurs/exposant.php")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            public  void onResponse(Call call, Response response) throws IOException {
                //Récupération de tout  les exposants de l'univers selectionnée par l'utilisateur
                String retourRequete = response.body().string();
                JSONArray mesExposants = new JSONArray();
                    try {
                        mesExposants = new JSONArray(retourRequete);
                     for(int i=0;i<mesExposants.length();i++)
                     {
                         JSONObject unExposant;
                         unExposant= mesExposants.getJSONObject(i);
                         toutExposants.add("Nom de l'entreprise : "+unExposant.getString("raisonSociale"));

                     }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ListView listViewVisiteur = findViewById(R.id.listView_listeExposant);
                                ArrayAdapter<String> arrayAdapterExposant = new ArrayAdapter<String>(VisiteurActivity.this, android.R.layout.simple_list_item_1, toutExposants);
                                listViewVisiteur.setAdapter(arrayAdapterExposant);
                            }
                        });
                            // Permettre à l'utilisateur de cliquer
                            ListView listViewVisiteur = findViewById(R.id.listView_listeExposant);
                            listViewVisiteur.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Object raisonSociale = listViewVisiteur.getItemAtPosition(i);
                                    Intent intent = new Intent(VisiteurActivity.this, affichageExposantActivity.class);
                                    intent.putExtra("raisonSoc",raisonSociale.toString());
                                    startActivity(intent);
                                }
                            });
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() { //Remplissage du spinner par les lettres de l'alphabet
                                String[] alphabet=new String[]{"Chercher par lettre une entreprise 🔽","a","b","c","d","e","f","g"
                                        ,"h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
                                Spinner s=(Spinner)findViewById(R.id.spinner_lettreAlphabet);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(VisiteurActivity.this,android.R.layout.simple_spinner_item, alphabet);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                s.setAdapter(adapter);
                            }
                        });
                        //gestion des recherche par lettre
                        Spinner s=(Spinner)findViewById(R.id.spinner_lettreAlphabet);
                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if(!s.getSelectedItem().toString().equals("Chercher par lettre une entreprise 🔽")) {
                                    //creation de la nouvelle Arraylist qui va être mise dans la listView
                                    ArrayList exposantLettre= new ArrayList<String>();
                                    ListView listeExposant =(ListView)findViewById(R.id.listView_listeExposant);
                                    //vide la liste des exposants
                                    listeExposant.setAdapter(null);
                                    for(int r=0;r<toutExposants.size();r++)
                                    {
                                        //Recupération de la premiere lettre du nom de l'entreprise
                                        char lettreRech=toutExposants.get(r).toString().substring(22).toLowerCase().charAt(0);
                                        if(lettreRech==(s.getSelectedItem().toString().charAt(0)))
                                        {
                                            exposantLettre.add(toutExposants.get(r));
                                        }
                                    }
                                    // Ajoute dans la liste view des entreprise  par lettre
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ListView listViewVisiteur = findViewById(R.id.listView_listeExposant);
                                            ArrayAdapter<String> arrayAdapterExposant = new ArrayAdapter<String>(VisiteurActivity.this, android.R.layout.simple_list_item_1, exposantLettre);
                                            listViewVisiteur.setAdapter(arrayAdapterExposant);
                                        }
                                    });
                                }else
                                    {
                                        //Si utilisateur retourne sur ChercheLettre...  recharge  toutes les entreprise de l'univers
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                ListView listViewVisiteur = findViewById(R.id.listView_listeExposant);
                                                ArrayAdapter<String> arrayAdapterExposant = new ArrayAdapter<String>(VisiteurActivity.this, android.R.layout.simple_list_item_1, toutExposants);
                                                listViewVisiteur.setAdapter(arrayAdapterExposant);
                                            }
                                        });
                                    }
                        }
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                return;
                            }
                        });
                    } catch (JSONException e) {
                        Log.d("ccr", String.valueOf(e));

                    }





            }

            });
    }


}