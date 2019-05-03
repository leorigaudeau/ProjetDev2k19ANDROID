package com.example.projetdev_2k19;

public class fournisseur {

    // Classe fournisseur qui à les mêmes caractéristiques que les objets dans l'API
    public String id;
    public String nom;
    public String description;
    public String adresse;
    public String telephone;
    public String mail;

    public fournisseur(String id ,String nom, String description, String adresse, String telephone, String mail) {
        this.id=id;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.telephone = telephone;
        this.mail = mail;
    }
}
