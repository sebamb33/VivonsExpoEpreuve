package com.example.vivonexpo;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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






public class ConnexionActivity extends AppCompatActivity{

    String responseStr ;
    OkHttpClient client = new OkHttpClient();


    //charge le layout connexion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        //Mon bouton de connexion
        final Button buttonValiderAuthentification = (Button)findViewById(R.id.buttonValiderAuthentification);
        buttonValiderAuthentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Appel de la function authentification
                try {
                    authentification();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



        public void authentification() throws IOException {
            final EditText textLogin = findViewById(R.id.editTextLogin);
            final EditText textMdp = findViewById(R.id.editTextMdp);
            RequestBody formBody = new FormBody.Builder()
                    .add("login", textLogin.getText().toString())
                    .add("mdp",  textMdp.getText().toString())
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.43.238/vivonExpo/web/controleurs/authentication.php")
                    .post(formBody)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                public  void onResponse(Call call, Response response) throws IOException {
                    responseStr = response.body().string();


                    if (responseStr.compareTo("false")!=0){
                        try {
                            JSONObject etudiant = new JSONObject(responseStr);


                            if(etudiant.getString("statut").compareTo("staff")!=0) {
                                Intent intent = new Intent(ConnexionActivity.this, staff.class);
                                intent.putExtra("etudiant", etudiant.toString());
                                startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(ConnexionActivity.this, exposantActivity.class);
                                intent.putExtra("etudiant", etudiant.toString());
                                startActivity(intent);
                            }
                        }
                        catch(JSONException e){
//                         Toast.makeText(MainActivity.this, "Erreur de connexion !!!! !", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("Test","Login ou mot de  passe non valide !");
                    }
                }

                public void onFailure(Call call, IOException e)
                {
                    Log.d("Test","erreur!!! connexion impossible");
                    Log.d("Test",e.getMessage());
                }
            });
        }










        //je recupere mon bouton avec sont ID
        final Button buttonQuitter = (Button) findViewById(R.id.buttonQuitterAuthentification);
        //je rajoute un evenement click sur le boutton
        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //J'appelle la classe ConnexionActivity
                Intent intent = new Intent(ConnexionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
