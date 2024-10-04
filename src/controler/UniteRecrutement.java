package controler;

import model.Banque;
import model.Position;
import model.Unite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UniteRecrutement implements ActionListener {
        private Banque banque;
        private ArrayList<Unite> uniteList;

        public UniteRecrutement(Banque banque, ArrayList<Unite> uniteList) {
            this.banque = banque;
            this.uniteList = uniteList;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (banque.getArgent() >= Unite.PRIXRECRUTEMENT) {
                Unite nouvelleUnite = new Unite(new Position());
                uniteList.add(nouvelleUnite);
                banque.modifierArgent(-Unite.PRIXRECRUTEMENT);
            }
        }
}