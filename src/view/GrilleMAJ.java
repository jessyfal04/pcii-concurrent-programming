package view;

import java.util.ArrayList;

import controler.FruitPlanter;
import controler.ReactionClic;
import model.Fruits;
import model.Unite;
import model.Banque;

public class GrilleMAJ extends Thread{

    private ArrayList<Fruits> fruits;
	private ReactionClic reactionClic;
    private GrilleBoutons g;
    private Banque b;

    public static final int DELAY = 50;


	public GrilleMAJ(ArrayList<Fruits> fruits, ReactionClic reactionClic, GrilleBoutons grille, Banque bq) {
		this.fruits = fruits;
		this.reactionClic = reactionClic;
        this.g = grille;
        this.b = bq;
	}

    @Override
    public void run(){
        while(true){
            // On fait une copie de la liste pour éviter les erreurs de concurrence
		    // Pour chaque fruit, on vérifie si il est recoltable
            Boolean fruitTrouve = false;
		    for (Fruits fruit : new ArrayList<Fruits>(fruits)) {
			    Unite uniteClique = reactionClic.getUniteClique();

			    // On vérifie si l'unité qui clique est à côté du fruit
                if (uniteClique == null){

                }else{
                    if (uniteClique.getPosition().distance(fruit.getPosition()) <= Unite.RAYONACTION) {
                    // Si oui, on vérifie si le fruit est recoltable
                        if (fruit.estRecoltable()) {
                            // Si oui, on l'enlève de la liste et on affiche le gain
                            g.activerRecolter();
                            fruitTrouve = true;
                            //System.out.println("Fruit recolté, gain de : " + fruit.getGain());
                        } 
                    }
                }
			    
		    }
            if (!fruitTrouve){
                g.desactiverRecolter();
            }

            Unite unitedeplacer = reactionClic.getUniteDeplacer();
            if (unitedeplacer != null){
                if (unitedeplacer.getEnDeplacement() == false){
                    g.desactiverBoutonRester();
                }else{
                    g.activerRester();
                }
            }
            
            Unite uniteClique = reactionClic.getUniteClique();
            boolean fruitPourriProche = false;
            int nbFruitsProche = 0;
            for (Fruits fruit : fruits) {
                if (uniteClique != null && fruit.getPosition().distance(uniteClique.getPosition()) <= Unite.RAYONACTION) {
                    nbFruitsProche++;
                    if (fruit.estPourri()) {
                        fruitPourriProche = true;
                    }
                }
            }
            if (b.disponible(FruitPlanter.PRIXPLANTATION) && uniteClique != null && !fruitPourriProche && nbFruitsProche < 3) {
                g.activerPlanter();
            }
            if (!b.disponible(FruitPlanter.PRIXPLANTATION) || fruitPourriProche || nbFruitsProche >= 3){
                g.desactiverPlanter();
            }
            if (b.disponible(-Unite.PRIXRECRUTEMENT)) {
                g.activerRecruter();
            } else {
                g.desactiverRecruter();
                
            }
            try {
                Thread.sleep(DELAY);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
