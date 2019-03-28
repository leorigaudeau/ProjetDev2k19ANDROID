package com.example.projetdev_2k19;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class FournisseurAdapter extends RecyclerView.Adapter<FournisseurViewHolder>
{
    // Liste d'objets métier :
    private List<fournisseur> listeCourses;
    // Constructeur :
    public FournisseurAdapter(List<fournisseur> listeCourses)
    {
        this.listeCourses = listeCourses;
    }
    @Override
    public FournisseurViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View viewCourse = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_fournisseur_item_liste, parent, false);
        return new FournisseurViewHolder(viewCourse);
    }
    @Override
    public void onBindViewHolder(FournisseurViewHolder holder, int position)
    {
        holder.textViewNomfournisseur.setText(listeCourses.get(position).nom);
    }
    @Override
    public int getItemCount()
    {
        return listeCourses.size();
    }
}
