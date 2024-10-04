package view;

import java.awt.*;
import javax.swing.*;

import main.Main;
import model.Banque;
import model.Temps;

public class Metriques extends JPanel {
   private Banque banque;
   private Temps temps;



   // Constantes //
   public static final int WIDTH = (int) (1200 * Main.GLOBAL_RATIO_WIDTH);
   public static final int HEIGHT = (int) (100 * Main.GLOBAL_RATIO_HEIGHT);

   private Image imageMet = new ImageIcon("res/salary.png").getImage().getScaledInstance(HEIGHT/2, HEIGHT/2,  java.awt.Image.SCALE_SMOOTH);


   public Metriques(Banque banque, Temps temps) {
      this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      this.banque = banque;
      this.temps = temps;
   }

   @Override
    public void paint(Graphics g) {
      super.paint(g);
      g.drawImage(imageMet, WIDTH/2 - HEIGHT/4, 0, this);

      g.setColor(Color.BLACK);
      // font
      g.setFont(new Font("Arial", Font.PLAIN, 20));
      //g.drawString("Monnaie: " + banque.getArgent(), 0, 20);

      //FontMetrics fm = g.getFontMetrics();
		//int x = (WIDTH - fm.stringWidth( banque.getArgent())) / 2;
		//int y = (fm.getAscent() + (HEIGHT - (fm.getAscent() + fm.getDescent())) / 2);
		//g.drawString(banque.getArgent() + "$", x, 30);

      String text = banque.getArgent() + "$";
      FontMetrics fm = g.getFontMetrics();
      int textWidth = fm.stringWidth(text);

      int x = (getWidth() - textWidth) / 2;

      g.drawString(text, x, 30);

      //g.drawString("Temps: " + temps.getSecondes(), 0, 40);

      // Barre entière
      g.setColor(Color.BLACK);

      g.fillRect(0, 50, WIDTH, HEIGHT/2);

      //barre de progression
      //Je veux que la couleur passe du vers au rouge en fonction du temps
      int couleurTemps = Math.min(255, Math.max(0,temps.getSecondes() * 255 / Temps.TEMPS_TOTAL));
      g.setColor(new Color(255 - couleurTemps, couleurTemps, 0));

      //je veux que la largeur du rectangle correspondent à getSecondes() % 60
      g.fillRect(0, 50, WIDTH * temps.getSecondes() / Temps.TEMPS_TOTAL, HEIGHT/2);

   }
}
