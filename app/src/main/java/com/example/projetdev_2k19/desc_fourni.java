package com.example.projetdev_2k19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class desc_fourni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_fourni);


    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("nomFournie")){
            String nomFournisseur = getIntent().getStringExtra("nomFournie");
        }
    }


    private void setImage(String nomFournisseur){
        TextView name = findViewById(R.id.textView);
    }
}
