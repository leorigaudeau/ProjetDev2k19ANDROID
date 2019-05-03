package com.example.projetdev_2k19;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class desc_fourni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_fourni);

        //Log.d("recup", "getIncomingIntent: "+ getIntent().getStringExtra("fourninom"));
        //Log.d("recup", "getIncomingIntent: "+ getIntent().getStringExtra("fournidescription"));
        //Log.d("recup", "getIncomingIntent: "+ getIntent().getStringExtra("fourniadresse"));
        //Log.d("recup", "getIncomingIntent: "+ getIntent().getStringExtra("fournimail"));
        //Log.d("recup", "getIncomingIntent: "+ getIntent().getStringExtra("fournitelephone"));
        //Log.d("recup", "getIncomingIntent: "+ getIntent().getStringExtra("fourniid"));

        if (getIntent().hasExtra("fourninom")){
            //Initialisation des valeurs qui affecte les valeurs du fournisseur aux différentes zones de texte.
            final String fourniId = getIntent().getStringExtra("fourniid");
            final String fourninom = getIntent().getStringExtra("fourninom");
            final String fournidescription = getIntent().getStringExtra("fournidescription");
            final String fourniadresse = getIntent().getStringExtra("fourniadresse");
            final String fournitelephone = getIntent().getStringExtra("fournitelephone");
            final String fournimail = getIntent().getStringExtra("fournimail");

            //Log.d("delFournisse", "onCreate: "+fourniId );
            TextView Textnom = findViewById(R.id.nomfournisolo);
            TextView Textdesc = findViewById(R.id.desctext);
            TextView Textadresse = findViewById(R.id.adresse);
            TextView Texttel = findViewById(R.id.tel);
            TextView Textmail = findViewById(R.id.mail);


            Textnom.setText(fourninom);
            Textdesc.setText(fournidescription);
            Textadresse.setText(fourniadresse);
            Texttel.setText(fournitelephone);
            Textmail.setText(fournimail);

            final Button modifier = findViewById(R.id.BTNModif);
            //Au clique on envoie les données dans la page modifier fournisseur.
            modifier.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(desc_fourni.this, Modifier.class);
                    intent.putExtra("fourninom", getIntent().getStringExtra("fourninom"));
                    intent.putExtra("fournidescription", getIntent().getStringExtra("fournidescription"));
                    intent.putExtra("fourniadresse", getIntent().getStringExtra("fourniadresse"));
                    intent.putExtra("fournitelephone", getIntent().getStringExtra("fournitelephone"));
                    intent.putExtra("fournimail", getIntent().getStringExtra("fournimail"));
                    intent.putExtra("fourniId", getIntent().getStringExtra("fourniid"));
                    startActivity(intent);
                }
            });

            final Button supprimer = findViewById(R.id.BTNSuppr);
            supprimer.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            //Création de la méthode delete, Méthode Asynchrone d'où la présence du Thread
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        //Connexion à l'api et création du header de la requête. On crée aussi l'url avec l'id du fournisseur actuel.
                                        URL url = new URL("http://projetdev2019api.herokuapp.com/fournisseur/"+fourniId);
                                        Log.d("modifurl", "run: "+url);
                                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                        conn.setRequestMethod("DELETE");
                                        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                                        conn.setRequestProperty("Accept","application/json");
                                        conn.setDoOutput(true);
                                        conn.setDoInput(true);
                                        // Exécution de la requête et fermeture de la connexion
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
                    CharSequence text = "Vous avez supprimer un fournisseur !";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    finish();
                }
            });

        }
    }

    private void setImage(String nomFournisseur){
        TextView name = findViewById(R.id.textView);
    }
}
