package view;

import javax.swing.*;

import controler.ReactionClic;
import main.Main;
import model.Alien;
import model.Fruits;
import model.Unite;

import java.awt.*;

public class InfoClic extends JPanel{

    // Constantes //
    public Color couleur = new Color(113,141,116);
    public static final int WIDTH = (int) (500 * Main.GLOBAL_RATIO_WIDTH);
    public static final int HEIGHT = (int) (200 * Main.GLOBAL_RATIO_HEIGHT);

    private ReactionClic reactionClic;
    private Image imageInfo = new ImageIcon("res/question.png").getImage().getScaledInstance(HEIGHT, HEIGHT,  java.awt.Image.SCALE_SMOOTH);


    // Constructeur //
    public InfoClic(ReactionClic reactionClic) {
        this.setBackground(couleur);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.reactionClic = reactionClic;

        new InfoClicRedessine(this).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawImage(imageInfo, WIDTH/2 - HEIGHT/2, 0, this);
        
        // On récupère l'unité cliquée
        Unite uniteClique = reactionClic.getUniteClique();
        Fruits fruitClique = reactionClic.getFruitClique();
        Alien alienClique = reactionClic.getAlienClique();

        if (fruitClique != null) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics fm = g.getFontMetrics();
		    int x = (WIDTH - fm.stringWidth("Fruit")) / 2;
            g.drawString("Fruit", x, 40);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Variete : " + fruitClique.getVariete(), 10, 60);
            g.drawString("Poison : " + fruitClique.getPoison() + "/" + Fruits.seuilPoison, 10, 80);

            g.drawString("Vie : " + fruitClique.getVie(), 10, 100);
            g.drawString("Vie initiale : " + fruitClique.getVieInitiale(), 10, 120);
            g.drawString("Gain : " + fruitClique.getGain(), 10, 140);
            
            if (fruitClique.estRecoltable()) {
                g.drawString("Recoltable", 10, 160);
            } else {
                g.drawString("Non recoltable", 10, 160);
            }

            if (fruitClique.estPourri()) {
                g.drawString("Pourri", 10, 180);
            } else {
                g.drawString("Non pourri", 10, 180);
            }

        } else if (alienClique != null) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics fm = g.getFontMetrics();
            int x = (WIDTH - fm.stringWidth("Alien")) / 2;
            g.drawString("Alien", x, 40);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            
            // Vers fruit ou pas
            if (alienClique.getAlienObjectif() == Alien.AlienObjectif.FRUIT) {
                g.drawString("Fonce sur un Fruit", 10, 60);
            } else if (alienClique.getAlienObjectif() == Alien.AlienObjectif.BALADE) {
                g.drawString("Se balade", 10, 60);
            } else if (alienClique.getAlienObjectif() == Alien.AlienObjectif.FUITE) {
                g.drawString("Fuit un scientifique", 10, 60);
            }
        } 
        else if (uniteClique != null) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics fm = g.getFontMetrics();
            int x = (WIDTH - fm.stringWidth("Unite")) / 2;
            g.drawString("Unite", x, 40);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Id : " + uniteClique.getId(), 10, 60);
            if (uniteClique.getEnDeplacement()) {
                g.drawString("En deplacement", 10, 80);
            } else {
                g.drawString("Pas en deplacement", 10, 80);
            }

        } 

    }
}
