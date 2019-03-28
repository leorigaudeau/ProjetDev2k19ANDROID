package com.example.projetdev_2k19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class listeFourni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_fourni);
        RecyclerView recyclerView = findViewById(R.id.list_Fournisseur);
        // à ajouter pour de meilleures performances :
        recyclerView.setHasFixedSize(true);
        // layout manager, décrivant comment les items sont disposés :
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // contenu d'exemple :
        List<fournisseur> listCourses = new ArrayList<>();
        listCourses.add(new fournisseur("Pommes"));
        listCourses.add(new fournisseur("Poires"));
        // adapter :
        FournisseurAdapter coursesAdapter = new FournisseurAdapter(listCourses);
        recyclerView.setAdapter(coursesAdapter);
    }
}
