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
import android.widget.TextView;

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
        //Appel du Bouton et Set onclick Listner
        Button ButtonConnexion = (Button) findViewById(R.id.BTNvalider);   //Appel du "button1"
                ButtonConnexion.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
                {
                    public void onClick(View actuelView)    //au clic sur le bouton
                    {
                //Récupération des données dans les inputs
                final EditText textnom = findViewById(R.id.INPId);
                final String identifiant = String.valueOf(textnom.getText());
                final EditText textpassword = findViewById(R.id.INPMdp);
                final String password = String.valueOf(textpassword.getText());

                //Création requête asynchrone ,Client HTTP :
                AsyncHttpClient client = new AsyncHttpClient();
                // Appel :
                client.get("http://projetdev2019api.herokuapp.com/user", new AsyncHttpResponseHandler() {
                    @SuppressLint("WrongViewCast")
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                        // Si la récupération à réussi
                        Log.i("RenduJson", "in success");
                        String retour = new String(response);
                        Gson gson = new Gson();
                        List<fournisseur> listeFourni = new ArrayList<>();
                        try {
                            JSONArray jsonArray = new JSONArray(retour);
                            //Check des identifiants saisie et on regarde si ils existent en base
                            //Si ils existent on autorise la connexion.
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
                        // Si la récupération à échouée
                        Log.e("infoJson", e.toString());
                    }

                });

            }

        });
        TextView nvUser =findViewById(R.id.nvuser);   //Appel du texte
        nvUser.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce texte
        {
            public void onClick(View actuelView)    //au clic sur le texte
            {
                Intent intent = new Intent(MainActivity.this, ajoutUtilisateur.class);  //Lancer l'activité ajoutUtilisateur
                startActivity(intent);    //Afficher la vue
            }

        });


    }
}
