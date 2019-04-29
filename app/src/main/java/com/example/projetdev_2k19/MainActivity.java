package com.example.projetdev_2k19;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ButtonConnexion = (Button) findViewById(R.id.BTNvalider);   //Appel du "button1"
                ButtonConnexion.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
                {
                    public void onClick(View actuelView)    //au clic sur le bouton
                    {

                final EditText textnom = findViewById(R.id.INPId);
                final String identifiant = String.valueOf(textnom.getText());
                final EditText textpassword = findViewById(R.id.INPMdp);
                final String password = String.valueOf(textpassword.getText());

                // client HTTP :
                AsyncHttpClient client = new AsyncHttpClient();
                // Appel :
                client.get("http://projetdev2019api.herokuapp.com/user", new AsyncHttpResponseHandler() {
                    @SuppressLint("WrongViewCast")
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                        Log.i("RenduJson", "in success");
                        String retour = new String(response);
                        Gson gson = new Gson();
                        List<fournisseur> listeFourni = new ArrayList<>();
                        try {
                            JSONArray jsonArray = new JSONArray(retour);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                final JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String vName = jsonObject.getString("nom");
                                String vPass = jsonObject.getString("password");
                                Log.i("RenduJson", "vName"+vName+"="+identifiant);
                                Log.i("RenduJson", "vPass"+vPass+"="+password);
                                if (identifiant.equals(vName)&& password.equals(vPass)){
                                    Intent intent = new Intent(MainActivity.this, listeFourni.class);  //Lancer l'activité DisplayVue
                                    startActivity(intent);    //Afficher la vue7
                                }
                            }
                        } catch (Exception e) {
                            Log.i("RenduJson", "error" + String.valueOf(listeFourni));

                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          byte[] errorResponse, Throwable e) {
                        Log.e("infoJson", e.toString());
                    }

                });

            }

        });


    }
}
