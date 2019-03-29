package com.example.projetdev_2k19;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.os.Build;
import android.preference.PreferenceActivity;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;


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

        // client HTTP :
        AsyncHttpClient client = new AsyncHttpClient();
        // paramètres :
        RequestParams requestParams = new RequestParams();
        requestParams.put("parametre", "1234");
        Log.i("RenduJson", "in enters");
        // Appel :
        client.post("https://projetdev2019api.herokuapp.com/fournisseur", requestParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.i("RenduJson", "in success");
                String retour = new String(responseBody);
                Gson gson = new Gson();
                List<fournisseur> listeFournisseur = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(retour);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        final JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String vName = jsonObject.getString("nom");



                        fournisseur fourni = new fournisseur(vName);
                        listeFournisseur.add(fourni);



                        RecyclerView recyclerView = findViewById(R.id.list_Fournisseur);
                        // à ajouter pour de meilleures performances :
                        recyclerView.setHasFixedSize(true);
                        // layout manager, décrivant comment les items sont disposés :
                        LinearLayoutManager layoutManager = new LinearLayoutManager(listeFourni.this);
                        recyclerView.setLayoutManager(layoutManager);
                        FournisseurAdapter fourniAdapter = new FournisseurAdapter(listeFournisseur);
                        recyclerView.setAdapter(fourniAdapter);

                    }
                } catch (Exception e) {
                    Log.i("RenduJson", "error" + String.valueOf(listeFournisseur));

                    e.printStackTrace();
                }
                Log.i("RenduJson", "final" + String.valueOf(listeFournisseur));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("RenduJson","fail" +  error.toString());
            }
        });

    }
}
