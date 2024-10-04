package model;

import java.util.ArrayList;

public class Alien extends Entite {

    private ArrayList<Fruits> fruitsList;
    private ArrayList<Unite> unitesList;

    public static enum AlienObjectif {
        BALADE,
        FRUIT,
        FUITE
    }
    private AlienObjectif alienObjectif = AlienObjectif.BALADE;

    /* Constructeur */

    public Alien(Position p, ArrayList<Fruits> fruitsList, ArrayList<Unite> unitesList){
        super(p);
        this.fruitsList = fruitsList;
        this.unitesList = unitesList;
        
        AlienThread at = new AlienThread(this);
        at.start();
    }

    /* AJOUT    */
    //Fonction pour savoir si un fruit est dans le rayon d'action de l'alien et si oui lequel
    public Fruits fruitsDansRayon(){
        // Liste of (fruit * distance), for sort after
        ArrayList<Fruits> fruitsDansRayon = new ArrayList<>();
        for(Fruits f : fruitsList){
            if (position.distance(f.getPosition()) < RAYONACTION && ! f.estPourri()){
                fruitsDansRayon.add(f);
            }
        }
        if (fruitsDansRayon.size() == 0){
            return null;
        }
        fruitsDansRayon.sort((f1, f2) -> {
            return (int) (position.distance(f1.getPosition()) - position.distance(f2.getPosition()));
        });
        return fruitsDansRayon.get(0);
    }

    //Fonction pour savoir si une unite est dans le rayon d'action de l'alien
    public Unite uniteProche(){
        for(Unite u : unitesList){
            if (position.distance(u.getPosition()) < RAYONACTION){
                return u;
            }
        }
        return null;
    }

    public void setAlienObjectif (AlienObjectif alienObjectif) {
        this.alienObjectif = alienObjectif;
    }

    public AlienObjectif getAlienObjectif() {
        return alienObjectif;
    }
}
