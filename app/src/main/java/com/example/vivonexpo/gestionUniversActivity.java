package com.example.vivonexpo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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


public class gestionUniversActivity extends AppCompatActivity {
    String responseStr ;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionuniversactivity);


        try {
            fecthLesUnivers();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void fecthLesUnivers( ) throws IOException{

        RequestBody formBody = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.11.87/vivonExpo/web/controleurs/staff.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public  void onResponse(Call call, Response response) throws IOException {
                responseStr = response.body().string();
                if (responseStr.length()>0){
                    try {
//
                        //Je recupere mes données et les transforme en arrayList
                        ArrayList arrayListUnivers = new ArrayList<String>();
                        JSONArray univers = new JSONArray(responseStr);

                        //je parcours mon tableau
                        for(int i=0;i<univers.length();i++){
                            JSONObject objetTemp = univers.getJSONObject(i);
                            arrayListUnivers.add(objetTemp.getString("libelleU"));
                        }


                        ListView listViewClasses = findViewById(R.id.listViewUnivers);

                        ArrayAdapter<String> arrayAdapterUnivers = new ArrayAdapter<String>(gestionUniversActivity.this,
                                android.R.layout.simple_list_item_1, arrayListUnivers);

                        listViewClasses.setAdapter(arrayAdapterUnivers);

                    }
                    catch(JSONException e){
                        Log.d("Test",e.getMessage());
//                         Toast.makeText(MainActivity.this, "Erreur de connexion !!!! !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("Test","Erreur dans la récuperation des Univers");
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
