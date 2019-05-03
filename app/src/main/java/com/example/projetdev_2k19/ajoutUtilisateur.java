package com.example.projetdev_2k19;

import android.content.Context;
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

public class ajoutUtilisateur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_utilisateur);

        //Appel du bouton
        Button ButtonConnexion = (Button) findViewById(R.id.BTNCreerUser);
        // Listener sur le bouton
        ButtonConnexion.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            public void onClick(View actuelView)    //au clic sur le bouton
            {
                // Ici on appelle tous les inputs pour l'ajout d'un fournisseur et on recupère leurs valeurs que l'on met dans vName,vDescription,...
                final EditText nomfourni = findViewById(R.id.INPidenti);
                final String vIdent = String.valueOf(nomfourni.getText());
                final EditText descfourni = findViewById(R.id.INPmdp);
                final String vMdp = String.valueOf(descfourni.getText());

                //Création de la méthode post, Méthode Asynchrone d'où la présence du Thread
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //Connexion a l'api et création du header de la requête.
                            URL url = new URL("http://projetdev2019api.herokuapp.com/user");
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                            conn.setRequestProperty("Accept","application/json");
                            conn.setDoOutput(true);
                            conn.setDoInput(true);
                            // Création du body "JSON" pour la requête nous utilisons les valeurs récupérées avec les inputs
                            JSONObject jsonParam = new JSONObject();
                            jsonParam.put("nom", vIdent);
                            jsonParam.put("password", vMdp);



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
                // Lancement du thread et éxécution des actions supplémentaires comme le toast qui pop-up un message
                thread.start();
                Context context = getApplicationContext();
                CharSequence text = "Vous avez créé un nouvelle utilisateur !";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finish();
            }
        });
    }
}
