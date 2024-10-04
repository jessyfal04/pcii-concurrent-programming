package model;

import java.awt.geom.Point2D;

import view.Affichage;

public class AlienThread extends Thread {
    private Alien alien;
    public static final int DELAY = 150;

    public AlienThread(Alien a){ alien = a; }

    @Override
    public void run(){
        while(true) {
            Fruits fruitProche = alien.fruitsDansRayon();

            // Si fruit très proche, on l'emposonne
            if (fruitProche != null && fruitProche.getPosition().distance(alien.getPosition()) < Affichage.TAILLEUNITE/2 ) {
                fruitProche.augmenterPoison();
            }

            // S'il est arrête, il cherche un fruit sinon il se balade
            if(!alien.getEnDeplacement()) {
                if (fruitProche != null) {
                    alien.initDeplacer(fruitProche.getPosition());
                    alien.setAlienObjectif(Alien.AlienObjectif.FRUIT);
                }
                else {
                    alien.initDeplacer(new Position());
                    alien.setAlienObjectif(Alien.AlienObjectif.BALADE);
                }
            }
            // S'il se balade mais qu'un fruit est proche, il fonce sur le fruit
            else if (alien.getAlienObjectif() == Alien.AlienObjectif.BALADE) {
                if (fruitProche != null) {
                    alien.stop_deplacement();
                    alien.initDeplacer(fruitProche.getPosition());
                    alien.setAlienObjectif(Alien.AlienObjectif.FRUIT);
                }
            }

            Unite uniteProche = alien.uniteProche();
            if (uniteProche == null || alien.getAlienObjectif() == Alien.AlienObjectif.FUITE){
                alien.deplacer();
            } else {
                Point2D vecteur = new Point2D.Double(uniteProche.getPosition().getX() - alien.getPosition().getX(), uniteProche.getPosition().getY() - alien.getPosition().getY());

                // On normalise et on multiplier par -1.25*RAYONACTION
                double norme = Math.sqrt(vecteur.getX()*vecteur.getX() + vecteur.getY()*vecteur.getY());
                double k = (-1.25 * Alien.RAYONACTION);
                vecteur.setLocation((vecteur.getX()/norme * k), (vecteur.getY()/norme * k));
                
                Position positionFuite = new Position((int)(alien.getPosition().getX() + vecteur.getX()), (int)(alien.getPosition().getY() + vecteur.getY()));

                alien.initDeplacer(positionFuite);
                alien.setAlienObjectif(Alien.AlienObjectif.FUITE);
            }
            
            try {
                if (alien.getAlienObjectif() == Alien.AlienObjectif.FUITE)
                    Thread.sleep(DELAY/2);
                else
                    Thread.sleep(DELAY);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}




