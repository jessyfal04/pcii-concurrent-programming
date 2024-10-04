package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Banque;
import model.Fruits;
import model.Position;
import model.Unite;
import view.Affichage;

public class FruitPlanter implements ActionListener{
	private ArrayList<Fruits> fruits;
	private ReactionClic reactionClic;
	private Banque banque;

	public static int PRIXPLANTATION = -2;

	public FruitPlanter(ArrayList<Fruits> fruits, ReactionClic reactionClic, Banque banque) {
		this.fruits = fruits;
		this.reactionClic = reactionClic;
		this.banque = banque;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// On plante un fruit à une position aléatoire +-64 de la position de l'unité ReactionClic.uniteClique.getPosition()
		Unite uniteClique = reactionClic.getUniteClique();

		boolean fruitPourriProche = false;
		int nbFruitsProche = 0;

		for (Fruits fruit : fruits) {
			if (fruit.getPosition().distance(uniteClique.getPosition()) <= Unite.RAYONACTION) {
				nbFruitsProche++;
				if (fruit.estPourri()) {
					fruitPourriProche = true;
				}
			}
		}

		if (!fruitPourriProche && nbFruitsProche < 3 && banque.disponible(PRIXPLANTATION)) {
			int x = (int) (uniteClique.getPosition().getX() + (Math.random() * Unite.RAYONACTION - Unite.RAYONACTION/2));
			int y = (int) (uniteClique.getPosition().getY() + (Math.random() * Unite.RAYONACTION - Unite.RAYONACTION/2));
			// On vérifie que la position est dans l'écran avec min max et les constantes Affichage.LARGEURFENETRE et Affichage.HAUTEURFENETRE
			x = Math.min(x, Affichage.LARGEURFENETRE);
			x = Math.max(x, 0);
			y = Math.min(y, Affichage.HAUTEURFENETRE);
			y = Math.max(y, 0);

			// On crée la position
			Position p = new Position(x, y);

			// On plante un fruit en ajoutant un fruit à la liste
			Fruits fruit = new Fruits(p);

			banque.modifierArgent(PRIXPLANTATION);
			fruits.add(fruit);
		}
	}
}
