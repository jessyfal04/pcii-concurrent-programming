package model;

public class Temps {
   public static final int TEMPS_TOTAL = 120;
   private int secondes = TEMPS_TOTAL;


   public Temps() {
      // On dÃ©marre le thread
      new TempsReduire(this).start();
   }

   public void decrementer() {
      secondes--;
   }

   public int getSecondes() {
      return secondes;
   }

}
