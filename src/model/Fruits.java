package model;

import java.util.Random;

public class Fruits {
	private int vieInitiale;
	private int vie;
	private int gain;
	private int seuilRecoltable;

	private int poison;
	public static int seuilPoison = 50;


	private Position position;
	public static enum Variete {ORANGE, POMME, SUPERFRUIT};
	private Variete variete;

	public Fruits(Position position) {

		Random rand = new Random();

		// On initialise la variété du fruit aléatoirement ORANGE ou POMME
		variete = rand.nextBoolean() ? Variete.ORANGE : Variete.POMME;

		// On initialise les valeurs gain et vie initiale aléatoirement
		if (variete == Variete.ORANGE){
			gain = rand.nextInt(5) + 1;
		}else if (variete == Variete.POMME){
			gain = rand.nextInt(10) + 1;
		}
		
		
		vieInitiale = rand.nextInt(11) + 5;
		poison = 0;

		// On initialise la vie du fruit, et le seuil à partir duquel il est récoltable (la moitié de sa vie initiale)
		vie = vieInitiale;
		seuilRecoltable = vieInitiale / 2;

		// On initialise la position du fruit
		this.position = position;

		

		// On démarre le thread de vie	
		new FruitsVivre(this).start();
	}

	public Fruits(Position position, Variete variete) {
		this(position);
		this.variete = variete;

		// Superfruit a un gain de 33 et un seuil de vie maximum (récoltable dès le spawn)
		if (variete == Variete.SUPERFRUIT){
			gain = 20;
			this.seuilRecoltable = vieInitiale;
		}
	}

	public Variete getVariete() {
		return variete;
	}

	public Position getPosition() {
		return position;
	}

	public int getVieInitiale() {
		return vieInitiale;
	}

	public int getVie() {
		return vie;
	}

	public void augmenterPoison() {
		poison++;
		poison = Math.min(poison, seuilPoison);
	}

	public int getPoison() {
		return poison;
	}

	// Un fruit est pourri si sa vie est à 0
	public boolean estPourri() {
		return vie == 0 || poison >= seuilPoison;
	}

	// Un fruit est récoltable si sa vie est inférieure à un certain seuil
	public boolean estRecoltable() {
		return vie <= seuilRecoltable;
	}

	// On réduit la vie du fruit
	public void reduireVie() {
		vie = Math.max(0, vie - 1);
	}

	// On obtient le gain du fruit, 0 si le fruit est pourri
	public int getGain() {
		return estPourri() ? 0 : gain;
	}


	@Override
	public String toString() {
		return "Fruit de variété " + variete + " à la position " + position + " avec " + vie + " points de vie";
	}
}
