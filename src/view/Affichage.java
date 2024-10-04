package view;

import model.Unite;
import model.Alien;
import model.Fruits;

import javax.swing.*;

import controler.ReactionClic;
import main.Main;

import java.awt.*;
import java.util.ArrayList;

public class Affichage extends JPanel {

    public static final int TAILLEUNITE = 32;
    public static final int LARGEURFENETRE = (int) (700 * Main.GLOBAL_RATIO_WIDTH);
    public static final int HAUTEURFENETRE = (int) (500 * Main.GLOBAL_RATIO_HEIGHT);

    //Liste d'objets à afficher
    private ArrayList<Unite> unitesList;
    private ArrayList<Fruits> fruitsList;
    private ArrayList<Alien> aliensList;

    private ReactionClic reactionClic;

    //Images
    private Image imageScientifiqe = new ImageIcon("res/scientist.png").getImage().getScaledInstance(TAILLEUNITE, TAILLEUNITE,  java.awt.Image.SCALE_SMOOTH);
    private Image imageFruitOrange = new ImageIcon("res/orange.png").getImage().getScaledInstance(TAILLEUNITE, TAILLEUNITE,  java.awt.Image.SCALE_SMOOTH);
    private Image imageFruitPomme = new ImageIcon("res/apple.png").getImage().getScaledInstance(TAILLEUNITE, TAILLEUNITE,  java.awt.Image.SCALE_SMOOTH);
    private Image imageSuperFruit = new ImageIcon("res/superfruit.png").getImage().getScaledInstance(TAILLEUNITE, TAILLEUNITE,  java.awt.Image.SCALE_SMOOTH);
    private Image imageFruitEnPousse = new ImageIcon("res/seed.png").getImage().getScaledInstance(TAILLEUNITE, TAILLEUNITE,  java.awt.Image.SCALE_SMOOTH);
    private Image imageAlienBalade = new ImageIcon("res/alien.png").getImage().getScaledInstance(TAILLEUNITE, TAILLEUNITE,  java.awt.Image.SCALE_SMOOTH);
    private Image imageAlienFuite = new ImageIcon("res/alienFuite.png").getImage().getScaledInstance(TAILLEUNITE, TAILLEUNITE,  java.awt.Image.SCALE_SMOOTH);
    private Image imageAlienFonce = new ImageIcon("res/alienFonce.png").getImage().getScaledInstance(TAILLEUNITE, TAILLEUNITE,  java.awt.Image.SCALE_SMOOTH);
    private Image imageFruitOrangePourri = new ImageIcon("res/orangeRotten.png").getImage().getScaledInstance(TAILLEUNITE, TAILLEUNITE, java.awt.Image.SCALE_SMOOTH);
    private Image imageFruitPommePourri = new ImageIcon("res/appleRotten.png").getImage().getScaledInstance(TAILLEUNITE, TAILLEUNITE, java.awt.Image.SCALE_SMOOTH);
    private Image imageSuperFruitPourri = new ImageIcon("res/superfruitRotten.png").getImage().getScaledInstance(TAILLEUNITE, TAILLEUNITE, java.awt.Image.SCALE_SMOOTH);
    private Image imageLune = new ImageIcon("res/lune.png").getImage().getScaledInstance(LARGEURFENETRE, HAUTEURFENETRE, java.awt.Image.SCALE_SMOOTH);



    public Affichage(ArrayList<Unite> unitesList, ArrayList<Fruits> fruitsList, ArrayList<Alien> aliensList, ReactionClic reactionClic) {
        this.setPreferredSize(new Dimension(LARGEURFENETRE, HAUTEURFENETRE));
        this.unitesList = unitesList;
        this.fruitsList = fruitsList;
        this.aliensList = aliensList;
        this.reactionClic = reactionClic;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imageLune, 0, 0, this);
        g.setColor(Color.RED);
        for (Unite u : unitesList) {
            g.drawImage(imageScientifiqe, u.getPosition().getX() - TAILLEUNITE/2, u.getPosition().getY() - TAILLEUNITE/2, this);
            
            // Si c'est l'unité cliquée on affiche son rayon d'action
            Unite uniteClique = reactionClic.getUniteClique();
            if (u == uniteClique) {
                g.drawOval(u.getPosition().getX() - Unite.RAYONACTION, u.getPosition().getY() - Unite.RAYONACTION, 2*Unite.RAYONACTION, 2*Unite.RAYONACTION);
            }
        }

        for (Fruits fruit : new ArrayList<Fruits>(fruitsList)) {
            Image imageFruit = null;
            if (fruit.estPourri()) {
                switch (fruit.getVariete()) {
                    case ORANGE:
                        imageFruit = imageFruitOrangePourri;
                        break;
                    case POMME:
                        imageFruit = imageFruitPommePourri;
                        break;
                    case SUPERFRUIT:
                        imageFruit = imageSuperFruitPourri;
                        break;
                }
            }
            else if (fruit.estRecoltable()) {
                switch (fruit.getVariete()) {
                    case ORANGE:
                        imageFruit = imageFruitOrange;
                        break;
                    case POMME:
                        imageFruit = imageFruitPomme;
                        break;
                    case SUPERFRUIT:
                        imageFruit = imageSuperFruit;
                        break;
                }
            }
            else
                imageFruit = imageFruitEnPousse;

            g.drawImage(imageFruit, fruit.getPosition().getX() - TAILLEUNITE/2, fruit.getPosition().getY() - TAILLEUNITE/2, this);
        }

        for (Alien alien : aliensList) {
            Image imageAlien = null;
            if (alien.getAlienObjectif() == Alien.AlienObjectif.FRUIT) {
                imageAlien = imageAlienFonce;
            } else if (alien.getAlienObjectif() == Alien.AlienObjectif.BALADE){
                imageAlien = imageAlienBalade;
            } else if (alien.getAlienObjectif() == Alien.AlienObjectif.FUITE) {
                imageAlien = imageAlienFuite;
            }
            
            g.drawImage(imageAlien, alien.getPosition().getX() - TAILLEUNITE/2, alien.getPosition().getY() - TAILLEUNITE/2, this);
        }
    }
}
