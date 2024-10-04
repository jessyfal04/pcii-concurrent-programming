package model;

import view.Affichage;

import java.util.ArrayList;

public class Entite {
    public static final int RAYONACTION = 128;

    public static final int COEFFPAS = 5; //augmenter le nombre de pas pour un déplacement = ralentir

    //On veut avoir des id uniques pour chaque unité
    
    protected Position position;
    protected Position anciennePosition; //Position de l'unité avant de se déplacer
    protected ArrayList<Position> positionsIntermediaires;
    //booleen pour savoir si l'unité est en train de se déplacer, i.e. si anciennePosition est différent de position
    protected boolean enDeplacement = false;
    //compteur pour savoir où on en est dans le tableau de positions intermédiaires (voir méthode deplacer)
    protected int compteur = 0;
    protected boolean peutdeplacer = false;



    /* Constructeur */

    public Entite(Position p){
        position = p;
        anciennePosition = p;
        positionsIntermediaires = new ArrayList<>();
    }


    /* getters */

    public Position getPosition(){
        return position;
    }

    public boolean getEnDeplacement() {
        return enDeplacement;
    }
    public boolean getpeutdeplacer() {
        return peutdeplacer;
    }


    /* setters */

    public void setAnciennePosition(Position position) {
        anciennePosition = position;
    }

    public void setEnDeplacement(boolean b) {
        enDeplacement = b;
    }

    public void setdeplacer(boolean b){
        peutdeplacer = b;
    }

    public void setPositionsIntermediaires(Position destination){
        //On remet à zéro la liste de positions intermédiaires avant le compteur
        positionsIntermediaires.subList(compteur, positionsIntermediaires.size()).clear();

        //On construit la liste de positions intermédiaires en fonction de la taille d'une unité

        int dx = destination.getX() - anciennePosition.getX();
        int dy = destination.getY() - anciennePosition.getY();
        int distance = (int) Math.sqrt(dx * dx + dy * dy);
        int nombreDePas = (distance * COEFFPAS) / Affichage.TAILLEUNITE;

        for(int i = 1; i <= nombreDePas; i++){
            int xInt = anciennePosition.getX() + (dx * i) / nombreDePas;
            int yInt = anciennePosition.getY() + (dy * i) / nombreDePas;
            positionsIntermediaires.add(new Position(xInt, yInt));
        }

        positionsIntermediaires.add(destination);
    }

    /* Méthodes */

    public void initDeplacer(Position destination){
        setAnciennePosition(position);
        setPositionsIntermediaires(destination);
        setEnDeplacement(true);
        setdeplacer(false);
    }

    //Méthode qui va aller vers une case en plus du tableau de positions intermédiaires à chaque appel
    //pour cela, il nous faut un compteur qui va enregistrer la position actuelle de l'unité dans le tableau et dès que la taille du tableau est atteinte, on remet le compteur à 0
    public void deplacer(){
        if(compteur < positionsIntermediaires.size()){
            position = positionsIntermediaires.get(compteur);
            compteur++;
        }else{
            enDeplacement = false;
            //On remet le compteur à 0 pour pouvoir réutiliser la méthode deplacer
            compteur = 0;
        }
    }

    public void stop_deplacement(){
        enDeplacement = false;
        peutdeplacer = false;
        anciennePosition = position;
        positionsIntermediaires.subList(compteur, positionsIntermediaires.size()).clear();
        compteur = 0;
    }
}
