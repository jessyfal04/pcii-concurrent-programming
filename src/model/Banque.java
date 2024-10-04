package model;

public class Banque {
   private int argent = 10;
   private boolean decompte = false;
   private int aModifier = 0;

   public int getArgent() {
      return argent;
   }

   public boolean getDecompte() {
      return decompte;
   }

   public void modifierArgent(int diff) {
      aModifier = diff;
      decompte = true;
   }

   public void modifier() {
      if(aModifier == 0)
         decompte = false;
      else if (aModifier < 0) {
         aModifier++;
         argent -= 1;
      }else{ //if (aModifier > 0)
         aModifier--;
         argent += 1;
      }
   }

   public boolean disponible(int diff) {
      return argent + diff >= 0;
   }

}