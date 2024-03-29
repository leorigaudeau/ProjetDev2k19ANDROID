package com.example.projetdev_2k19;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceActivity;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class listeFourni extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_fourni);
        //Création requête asynchrone
        // client HTTP :
        AsyncHttpClient client = new AsyncHttpClient();
        // Appel en utilisant la methode GET :
        client.get("http://projetdev2019api.herokuapp.com/fournisseur", new AsyncHttpResponseHandler() {
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

                    for (int i = 0; i < jsonArray.length(); i++) {
                        //Initialisation des valeurs, récupérées avec le JSON, dans les valeurs vId,vNom,....
                        final JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String vId = jsonObject.getString("_id");
                        String vName = jsonObject.getString("nom");
                        String vDesc = jsonObject.getString("description");
                        String vAdresse = jsonObject.getString("adresse");
                        String vTelephone = jsonObject.getString("telephone");
                        String vMail = jsonObject.getString("mail");

                        //Création d'un fournisseur et ajout dans la liste
                        fournisseur vehicule = new fournisseur(vId,vName,vDesc,vAdresse,vTelephone,vMail);
                        listeFourni.add(vehicule);

                        //Envoi du fournisseur dans la recycler View
                        RecyclerView recyclerView = findViewById(R.id.list_Fournisseur);
                        // à ajouter pour de meilleures performances :
                        recyclerView.setHasFixedSize(true);
                        // layout manager, décrivant comment les items sont disposés :
                        LinearLayoutManager layoutManager = new LinearLayoutManager(listeFourni.this);
                        recyclerView.setLayoutManager(layoutManager);
                        FournisseurAdapter coursesAdapter = new FournisseurAdapter(listeFourni,listeFourni.this);
                        recyclerView.setAdapter(coursesAdapter);

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
        // Initialisation de la redirection sur ajout fournisseur.
        final Button button = findViewById(R.id.BTNvalider);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(listeFourni.this, ajoutFournisseur.class);
                startActivity(intent);
            }
        });

    };





}
