package model;

import view.Affichage;

public class Position {

    private int x;
    private int y;

    /* Constructeur */
    public Position(){
        this.x = (int)(Math.random()*Affichage.LARGEURFENETRE);
        this.y = (int)(Math.random()*Affichage.HAUTEURFENETRE);
    }

    public Position(int x, int y){
        this.x = Math.min(Affichage.LARGEURFENETRE, Math.max(0, x));
        this.y = Math.min(Affichage.HAUTEURFENETRE, Math.max(0, y));
    }

    /* getters */

    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }


    // Distance
    public double distance(Position p){
        return Math.sqrt((x-p.getX())*(x-p.getX()) + (y-p.getY())*(y-p.getY()));
    }

    public boolean equals(Position p){
        return x == p.getX() && y == p.getY();
    }

}
