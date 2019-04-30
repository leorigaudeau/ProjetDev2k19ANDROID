package com.example.projetdev_2k19;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projetdev_2k19.R;

public class FournisseurViewHolder extends RecyclerView.ViewHolder
{
    // TextView intitulé course :
    public TextView textViewNomfournisseur;
    public TextView textViewdescriptionfournisseur;
    public LinearLayout parentLayout;

    // Constructeur
    public FournisseurViewHolder(View itemView)
    {
        super(itemView);
        textViewNomfournisseur = itemView.findViewById(R.id.nom_Fournisseur);
        textViewdescriptionfournisseur= itemView.findViewById(R.id.description_Fournisseur);
        parentLayout= itemView.findViewById(R.id.constraintview);
    }
}