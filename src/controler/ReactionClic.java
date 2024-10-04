package controler;

import model.Unite;
import view.Affichage;
import view.GrilleBoutons;
import model.Alien;
import model.Fruits;
import model.Position;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ReactionClic implements MouseListener {
    private int uniteId = -1;
    private int uniteIdDeplacer = -1;
    private GrilleBoutons g; // j'ai laissé au cas où
    //je veux un booléen pour savoir si j'ai mis à jour l'id
    private boolean clicSurUnite = false;
    private boolean clicSurFruit = false;
    private boolean clicSurAlien = false;


    private ArrayList<Unite> unitesList;
    private ArrayList<Fruits> fruitsList;
    private ArrayList<Alien> aliensList;

    public Fruits fruitClique;
    public Alien alienClique;


    public ReactionClic(ArrayList<Unite> unitesList, ArrayList<Fruits> fruitsList, ArrayList<Alien> aliensList){
        this.unitesList = unitesList;
        this.fruitsList = fruitsList;
        this.aliensList = aliensList;
    }

    public void setGrille(GrilleBoutons g){
        this.g = g;
    }

    public void mouseClicked(MouseEvent e) {
        //Si la position sur laquelle je clique correspond à la position d'une des unités de la liste d'unités de l'affichage
        //dans un rayon de TAILLEUNITE, alors je mets à jour l'id

        for(Unite u : unitesList){
            if(u.getPosition().distance(new Position(e.getX(), e.getY())) < Affichage.TAILLEUNITE/2){
                uniteId = u.getId();
                uniteIdDeplacer = u.getId();
                // uniteClique = u;
                clicSurUnite = true;
                g.activerBoutons();
            }
        }

        for (Fruits fruit : new ArrayList<Fruits>(fruitsList)) {
            if (fruit.getPosition().distance(new Position(e.getX(), e.getY())) < Affichage.TAILLEUNITE/2) {
                fruitClique = fruit;
                clicSurFruit = true;
            }
        }

        for (Alien alien : new ArrayList<Alien>(aliensList)) {
            if (alien.getPosition().distance(new Position(e.getX(), e.getY())) < Affichage.TAILLEUNITE/2) {
                alienClique = alien;
                clicSurAlien = true;
            }
        }

        //"Sinon", je mets à jour la position de la dernière unité sur laquelle j'ai cliqué, celle dont l'id est égal à id
        Unite uniteClique = getUniteClique();
        boolean deplacementEffectue = false;

        if(!clicSurUnite && uniteClique != null) {
            if (uniteClique.getpeutdeplacer()) {
                deplacementEffectue = true;
                Position p = new Position(e.getX(), e.getY());

                uniteClique.initDeplacer(p);

                //g.desactiverBoutons();
            }
            //Besoin de l'idée donc ne pas remettre uniteId à -1
            //uniteId = -1;
        }

        if (!clicSurFruit) {
            fruitClique = null;
        }

        if (!clicSurAlien) {
            alienClique = null;
        }

        if (!clicSurUnite && !deplacementEffectue) {
            //Besoin de l'idée donc ne pas remettre uniteId à -1
            uniteId = -1;
            g.desactiverBoutons(); 
        }

        //Je remets idUpdated à false pour le prochain clic
        clicSurUnite = false;
        clicSurFruit = false;
        clicSurAlien = false;
        
    }

    public Unite getUniteClique(){
        Unite uniteClique = null;
        for (Unite unite : unitesList) {
            if (unite.getId() == uniteId) {
                uniteClique = unite;
            }
        }

        return uniteClique;
    }

    public Fruits getFruitClique() {
        return fruitClique;
    }

    public Alien getAlienClique() {
        return alienClique;
    }

    public Unite getUniteDeplacer(){
        Unite uniteClique = null;
        for (Unite unite : unitesList) {
            if (unite.getId() == uniteIdDeplacer) {
                uniteClique = unite;
            }
        }

        return uniteClique;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
