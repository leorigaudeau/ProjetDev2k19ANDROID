package com.example.projetdev_2k19;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class ajoutFournisseur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_fournisseur);
        Button ButtonConnexion = (Button) findViewById(R.id.BTNCreer);   //Appel du "button1"
        ButtonConnexion.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            public void onClick(View actuelView)    //au clic sur le bouton
            {
                final EditText nomfourni = findViewById(R.id.INPnom);
                final String vName = String.valueOf(nomfourni.getText());
                final EditText descfourni = findViewById(R.id.INPdesc);
                final String vDescription = String.valueOf(descfourni.getText());
                final EditText telfourni = findViewById(R.id.INPtel);
                final String vAdresse = String.valueOf(telfourni.getText());
                final EditText adressfourni = findViewById(R.id.INPadress);
                final String vTelephone = String.valueOf(adressfourni.getText());
                final EditText mailfourni = findViewById(R.id.INPmail);
                final String vMail = String.valueOf(mailfourni.getText());

                AsyncHttpClient client = new AsyncHttpClient();
// paramètres :
                RequestParams requestParams = new RequestParams();
                requestParams.put("nom", vName);
                requestParams.put("description", vDescription);
                requestParams.put("adresse", vAdresse);
                requestParams.put("telephone", vTelephone);
                requestParams.put("mail", vMail);
                Log.i("POSTJSON", String.valueOf(requestParams));
// appel :
                client.post("http://projetdev2019api.herokuapp.com/fournisseur", requestParams, new AsyncHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] response)
                    {
                        String retour = new String(response);
                        Log.i("POSTJSON", retour);
                        Log.i("POSTJSON", "onsuccess");
                        Context context = getApplicationContext();
                        CharSequence text = "Vous avez créé un nouveaux fournisseur !";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Intent intent = new Intent(ajoutFournisseur.this, MainActivity.class);  //Lancer l'activité DisplayVue
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          byte[] errorResponse, Throwable e)
                    {
                        Log.e("POSTJSON", e.toString());
                        Log.i("POSTJSON", "on failure");
                        Context context = getApplicationContext();
                        CharSequence text = "Problème de Connexion a L'API!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });
            }
        });

    }
}
