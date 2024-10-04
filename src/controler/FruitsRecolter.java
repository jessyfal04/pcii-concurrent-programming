package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Banque;
import model.Fruits;
import model.Unite;

public class FruitsRecolter implements ActionListener{
	private ArrayList<Fruits> fruits;
	private ReactionClic reactionClic;
	private Banque banque;

	public FruitsRecolter(ArrayList<Fruits> fruits, ReactionClic reactionClic, Banque banque) {
		this.fruits = fruits;
		this.reactionClic = reactionClic;
		this.banque = banque;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// On fait une copie de la liste pour éviter les erreurs de concurrence
		// Pour chaque fruit, on vérifie si il est recoltable
		for (Fruits fruit : new ArrayList<Fruits>(fruits)) {
			Unite uniteClique = reactionClic.getUniteClique();

			// On vérifie si l'unité qui clique est à côté du fruit
			if (uniteClique.getPosition().distance(fruit.getPosition()) <= Unite.RAYONACTION) {
				// Si oui, on vérifie si le fruit est recoltable
				if (fruit.estRecoltable()) {
					// Si oui, on l'enlève de la liste et on affiche le gain
					banque.modifierArgent(fruit.getGain());
					fruits.remove(fruit);
					// System.out.println("Fruit recolté, gain de : " + fruit.getGain());
				}
				if (fruit.estPourri()){
					banque.modifierArgent(FruitPlanter.PRIXPLANTATION/2);
					fruits.remove(fruit);
				} 
					
			}
		}
	}
	
}
