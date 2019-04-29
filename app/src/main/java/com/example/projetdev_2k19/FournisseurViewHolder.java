package com.example.projetdev_2k19;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.projetdev_2k19.R;

public class FournisseurViewHolder extends RecyclerView.ViewHolder
{
    // TextView intitulé course :
    public TextView textViewNomfournisseur;
    public TextView textViewdescriptionfournisseur;
    // Constructeur :
    public FournisseurViewHolder(View itemView)
    {
        super(itemView);
        textViewNomfournisseur = itemView.findViewById(R.id.nom_Fournisseur);
        textViewdescriptionfournisseur= itemView.findViewById(R.id.description_Fournisseur);
    }
}