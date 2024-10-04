package main;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.*;

import view.*;
import controler.*;
import model.*;

public class Main {
    public static final double GLOBAL_RATIO_WIDTH = (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1920);
    public static final double GLOBAL_RATIO_HEIGHT = (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1080);

    public static void main(String [] args) {

        // fenetre //
        JFrame frame = new JFrame("Projet PCII");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Banque, Temps, Metriques //
        Banque banque = new Banque();
        Temps temps = new Temps();
        Metriques metriques = new Metriques(banque, temps);


        // Detection de fin de partie
        DetectionFinPartie detection = new DetectionFinPartie(frame, banque, temps);
        detection.start();

        // Fruits //
        ArrayList<Fruits> fruitsList = new ArrayList<Fruits>();
        fruitsList.add(new Fruits(new Position()));
        fruitsList.add(new Fruits(new Position()));

        // SuperFruits //
        SpawnSuperfruit spawnSuperfruit = new SpawnSuperfruit(fruitsList);
        spawnSuperfruit.start();

        // Unités //
        ArrayList<Unite> unitesList = new ArrayList<>();
        unitesList.add(new Unite(new Position()));

        // Aliens //
        ArrayList<Alien> aliensList = new ArrayList<>();
        aliensList.add(new Alien(new Position(), fruitsList, unitesList));

        // Spawn Aliens //
        AlienSpawn alienSpawn = new AlienSpawn(aliensList, fruitsList, unitesList);
        alienSpawn.start();

        // Grille & Instructions //
        ReactionClic cUnites = new ReactionClic(unitesList, fruitsList, aliensList);
        GrilleBoutons grille = new GrilleBoutons(fruitsList, cUnites, banque, unitesList);
        cUnites.setGrille(grille);


        // Affichage //
        FruitsPanel fruitsPanel = new FruitsPanel(fruitsList, cUnites);
        Affichage aUnites = new Affichage(unitesList, fruitsList, aliensList, cUnites);
        aUnites.addMouseListener(cUnites);
        
        AffichageRedessine redessineAUnites = new AffichageRedessine(aUnites);
        redessineAUnites.start();
        FruitsRedessine redessineFruits = new FruitsRedessine(fruitsPanel);
        redessineFruits.start();
        GrilleMAJ gr = new GrilleMAJ(fruitsList, cUnites, grille, banque);
        gr.start();
        MetriquesRedessinne redessineMetriques = new MetriquesRedessinne(metriques);
        redessineMetriques.start();
        Decompte decompte = new Decompte(banque);
        decompte.start();
        
        // test layout //
        InfoClic infoClic = new InfoClic(cUnites);
        BasLayout basLayout = new BasLayout();
        basLayout.add(fruitsPanel, BorderLayout.CENTER);
        basLayout.add(infoClic, BorderLayout.EAST);
        


        // Layout //
        frame.add(aUnites, BorderLayout.CENTER);
        frame.add(grille, BorderLayout.EAST);
        frame.add(basLayout, BorderLayout.SOUTH);
        frame.add(metriques, BorderLayout.NORTH);

        // On affiche la fenêtre
        frame.pack();
        frame.setVisible(true);        
    }
}
