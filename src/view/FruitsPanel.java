package view;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import controler.ReactionClic;
import main.Main;
import model.Fruits;
import model.Unite;

public class FruitsPanel extends JPanel {
	private final int X = (int) (700 * Main.GLOBAL_RATIO_WIDTH);
	private final int Y = (int) (200 * Main.GLOBAL_RATIO_HEIGHT);
	private ArrayList<Fruits> fruits;
	private ReactionClic reactionClic;

	private Image imageRadar = new ImageIcon("res/radar.png").getImage().getScaledInstance(Y, Y,  java.awt.Image.SCALE_SMOOTH);


	public FruitsPanel(ArrayList<Fruits> fruits, ReactionClic reactionClic) {
		// On fixe la taille du panneau
		setPreferredSize(new Dimension(X, Y));

		// // On démarre le thread de redessin
		// new FruitsRedessine(this).start();

		this.fruits = fruits;

		this.reactionClic = reactionClic;


	}

	@Override
    public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(imageRadar, X/2 - Y/2, 0, this);


		// Si aucune unité n'est cliquée, on ne fait rien
		Unite uniteClique = reactionClic.getUniteClique();
		if (uniteClique == null)
			return;
			
		// On fait une copie de la liste pour éviter les problèmes de concurrence
		ArrayList<Fruits> fruitsProximiteUnite = new ArrayList<Fruits>();

		for (Fruits fruit : new ArrayList<Fruits>(fruits)) {
			if (fruit.getPosition().distance(uniteClique.getPosition()) < Unite.RAYONACTION) {
				fruitsProximiteUnite.add(fruit);
			}
		}
		
		int nbdiv = Math.max(3, fruitsProximiteUnite.size());
		// Pour chaque fruit
		for (int i = 0; i < fruitsProximiteUnite.size(); i++) {

			Fruits fruit = fruitsProximiteUnite.get(i);

			// Barre entière
			if (fruit.estPourri())
				g.setColor(Color.RED);
			else
				g.setColor(Color.BLACK);
			g.fillRect(0, Y/nbdiv * i, X/2, Y/nbdiv);

			// Barre de vie
			if (fruit.estRecoltable() && !fruit.estPourri())
				g.setColor(Color.GREEN);
			else if (!fruit.estPourri())
				g.setColor(Color.ORANGE);
			g.fillRect(0, Y/nbdiv * i, X/2 * fruit.getVie() / fruit.getVieInitiale(), Y/nbdiv);

			// Texte informatif
			g.setFont(new Font("Arial", Font.BOLD, 20));

			if (fruit.estPourri())
				g.drawString("Pourri - Gain: " + fruit.getGain(), X/2, (int)(Y/nbdiv * (i+0.67)));
			else if (fruit.estRecoltable())
				g.drawString("Recoltable - Gain: " + fruit.getGain(), X/2, (int)(Y/nbdiv * (i+0.67)));
			else
				g.drawString("En pousse - Gain: " + fruit.getGain(), X/2, (int)(Y/nbdiv * (i+0.67)));
		}
	}
}
