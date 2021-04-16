package com.example.vivonexpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
        Intent ir= getIntent();
        RequestBody formBody = new FormBody.Builder()
                .add("univers", (ir.getExtras().getString("univers")))
                .build();
                Log.d("testUniv",ir.getExtras().getString("univers"));
        Request request = new Request.Builder()
                .url("http://192.168.1.2/vivonExpo/vivonExpo/web/controleurs/exposant.php")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            public  void onResponse(Call call, Response response) throws IOException {
                retourRequete = response.body().string();
                if (retourRequete.compareTo("false")!=0)
                {
                    try {
                        JSONObject exposant= new JSONObject(retourRequete);
                        Log.d("expoTest", String.valueOf(exposant));
                    } catch (JSONException e) {
                        //exposant vide

                    }
                }
            }
        });
    }
}