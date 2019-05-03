package com.example.projetdev_2k19;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


import java.util.List;

public class FournisseurAdapter extends RecyclerView.Adapter<FournisseurViewHolder>
{
    // Liste d'objets métier :
    private List<fournisseur> listeCourses;
    private Context mContext;

    // Constructeur :
    public FournisseurAdapter(List<fournisseur> listeCourses,Context context)
    {
        this.mContext= context;
        this.listeCourses = listeCourses;
    }

    @Override
    public FournisseurViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewCourse = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_fournisseur_item_liste, parent, false);
        return new FournisseurViewHolder(viewCourse,listeCourses);
    }
    @Override
    public void onBindViewHolder(FournisseurViewHolder holder, final int position)
    {
        // On recupere la liste de fournisseurs créés dans la méthode GET et on créé nos items que l'on injecte dans la Recycler view
        Log.d("onclickRECY", "onBindViewHolder: called.");
        holder.textViewNomfournisseur.setText(listeCourses.get(position).nom);
        holder.textViewdescriptionfournisseur.setText(listeCourses.get(position).description);
    }
    @Override
    public int getItemCount()
    {
        return listeCourses.size();
    }
}
