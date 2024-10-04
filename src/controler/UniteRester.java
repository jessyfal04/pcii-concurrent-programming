package controler;

import model.Unite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UniteRester implements ActionListener {

    private ReactionClic reactionClic;

    public UniteRester(ReactionClic reactionClic) {
        this.reactionClic = reactionClic;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Unite uniteClique = reactionClic.getUniteDeplacer();
        uniteClique.stop_deplacement();
    }

}
