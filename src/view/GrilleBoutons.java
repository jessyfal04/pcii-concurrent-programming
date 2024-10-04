package view;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import controler.*;
import main.Main;
import model.Banque;
import model.Fruits;
import model.Unite;


public class GrilleBoutons extends JPanel {
    // Constantes //
    public static final int WIDTH = (int) (500 * Main.GLOBAL_RATIO_WIDTH);
    public static final int HEIGHT = (int) (500 * Main.GLOBAL_RATIO_HEIGHT);


    // Les boutons //
    
    Icon icon = new ImageIcon("res/run.png");
    Icon icon1 = new ImageIcon("res/plant.png");
    Icon icon2 = new ImageIcon("res/recupe.png");
    Icon icon3 = new ImageIcon("res/stop.png");
    Icon icon4 = new ImageIcon("res/hire.png");
    public JButton deplacer = new JButton("se déplacer", icon);
    public JButton planter = new JButton("planter une graine", icon1);
    public JButton recolter = new JButton("récolter une plante", icon2);
    public JButton rester = new JButton("rester sur place", icon3);
    public JButton recruter = new JButton("recruter une unité", icon4);

    


    // Constructeur //
    public GrilleBoutons(ArrayList<Fruits> fruitsList, ReactionClic reactionClic, Banque banque, ArrayList<Unite> unitesList) {
        this.setPreferredSize((new DimensionUIResource(HEIGHT, WIDTH)));
        this.setBackground(Color.LIGHT_GRAY);

        
        deplacer.setHorizontalTextPosition(SwingConstants.CENTER);
        planter.setHorizontalTextPosition(SwingConstants.CENTER);
        recolter.setHorizontalTextPosition(SwingConstants.CENTER);
        rester.setHorizontalTextPosition(SwingConstants.CENTER);
        recruter.setHorizontalTextPosition(SwingConstants.CENTER);
        this.add(deplacer);
        this.add(planter);
        this.add(recolter);
        this.add(rester);
        this.add(recruter);

        this.setLayout(new GridLayout(3,2));

        deplacer.addActionListener(new UniteDeplacer(reactionClic));
        planter.addActionListener(new FruitPlanter(fruitsList, reactionClic, banque));
        recolter.addActionListener(new FruitsRecolter(fruitsList, reactionClic, banque));
        rester.addActionListener(new UniteRester(reactionClic));
        recruter.addActionListener(new UniteRecrutement(banque, unitesList));


        deplacer.setEnabled(false);
        planter.setEnabled(false);
        recolter.setEnabled(false);
        rester.setEnabled(false);
        recruter.setEnabled(false);
    }

    public void activerBoutons(){
        deplacer.setEnabled(true);
        planter.setEnabled(true);
        recruter.setEnabled(true);
    }

    public void activerRecolter(){
        recolter.setEnabled(true);
        rester.setEnabled(true);
    }

    public void desactiverRecolter(){
        recolter.setEnabled(false); 
    }

    public void activerPlanter(){
        planter.setEnabled(true);
    }

    public void desactiverPlanter(){
        planter.setEnabled(false); 
    }

    public void activerRester(){
        rester.setEnabled(true);
    }

    public void desactiverBoutonRester(){
        rester.setEnabled(false);
    }

    public void activerRecruter(){
        recruter.setEnabled(true);
    }

    public void desactiverRecruter(){
        recruter.setEnabled(false);
    }

    public void desactiverBoutons(){
        deplacer.setEnabled(false);
        planter.setEnabled(false);
        recolter.setEnabled(false);
    }

}
