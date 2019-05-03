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

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Modifier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);
        // Ici on appelle tous les inputs pour l'ajout d'un fournisseur et on met les valeurs dans les inputs
        final EditText nomfourni = findViewById(R.id.INPnommodif);
        nomfourni.setText(getIntent().getStringExtra("fourninom"));
        final EditText descfourni = findViewById(R.id.INPdescmodif);
        descfourni.setText(getIntent().getStringExtra("fournidescription"));
        final EditText telfourni = findViewById(R.id.INPtelmodif);
        telfourni.setText(getIntent().getStringExtra("fourniadresse"));
        final EditText adressfourni = findViewById(R.id.INPadressmodif);
        adressfourni.setText(getIntent().getStringExtra("fournitelephone"));
        final EditText mailfourni = findViewById(R.id.INPmailmodif);
        mailfourni.setText(getIntent().getStringExtra("fournimail"));
        final String id = getIntent().getStringExtra("fourniId");
        //Appel du bouton
        Button ButtonConnexion = (Button) findViewById(R.id.BTNmodif);
        // Listener sur le bouton
        ButtonConnexion.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            public void onClick(View actuelView)    //au clic sur le bouton
            {
                //On recupère leurs valeurs qu'on met dans vName,vDescription,...
                final String vName = String.valueOf(nomfourni.getText());
                final String vDescription = String.valueOf(descfourni.getText());
                final String vAdresse = String.valueOf(telfourni.getText());
                final String vTelephone = String.valueOf(adressfourni.getText());
                final String vMail = String.valueOf(mailfourni.getText());
                //Création de la méthode put, Méthode Asynchrone d'où la présence du Thread
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //Connexion à l'api et création du header de la requête.
                            URL url = new URL("http://projetdev2019api.herokuapp.com/fournisseur/"+id);
                            Log.d("modifurl", "run: "+url);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("PUT");
                            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                            conn.setRequestProperty("Accept","application/json");
                            conn.setDoOutput(true);
                            conn.setDoInput(true);
                            // Création du body "JSON" pour la requête nous utilisons les valeurs récupérées avec les inputs
                            JSONObject jsonParam = new JSONObject();
                            jsonParam.put("nom", vName);
                            jsonParam.put("description", vDescription);
                            jsonParam.put("adresse", vAdresse);
                            jsonParam.put("telephone", vTelephone);
                            jsonParam.put("mail", vMail);

                            // Exécution de la requête et fermeture de la connexion
                            Log.i("JSON", jsonParam.toString());
                            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                            os.writeBytes(jsonParam.toString());

                            os.flush();
                            os.close();

                            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                            Log.i("MSG" , conn.getResponseMessage());

                            conn.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                //Lancement du thread et éxécution des actions supplémentaires comme le toast qui pop-up un message
                thread.start();
                Context context = getApplicationContext();
                CharSequence text = "Vous avez modifier un fournisseur !";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finish();
            }
        });
    }
}
