package com.example.projetdev_2k19;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projetdev_2k19.R;

import java.util.List;

public class FournisseurViewHolder extends RecyclerView.ViewHolder
{
    // TextView intitulé course :
    public TextView textViewNomfournisseur;
    public TextView textViewdescriptionfournisseur;
    public LinearLayout parentLayout;
    public ConstraintLayout fourniViewWrapper;

    // Constructeur
    public FournisseurViewHolder(final View itemView, final List<fournisseur> listeCourses)
    {
        super(itemView);
        textViewNomfournisseur = itemView.findViewById(R.id.nom_Fournisseur);
        textViewdescriptionfournisseur= itemView.findViewById(R.id.description_Fournisseur);
        parentLayout= itemView.findViewById(R.id.constraintview);
        fourniViewWrapper = itemView.findViewById(R.id.fourniViewWrapper);
        fourniViewWrapper.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(itemView.getContext(), desc_fourni.class);
                intent.putExtra("fourninom", listeCourses.get(getAdapterPosition()).nom);
                intent.putExtra("fournidescription", listeCourses.get(getAdapterPosition()).description);
                intent.putExtra("fourniadresse", listeCourses.get(getAdapterPosition()).adresse);
                intent.putExtra("fournitelephone", listeCourses.get(getAdapterPosition()).telephone);
                intent.putExtra("fournimail", listeCourses.get(getAdapterPosition()).mail);
                intent.putExtra("fourniid", listeCourses.get(getAdapterPosition()).id);
                itemView.getContext().startActivity(intent);
            }
        });
    }
}