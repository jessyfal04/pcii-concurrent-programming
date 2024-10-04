package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Unite;

public class UniteDeplacer implements ActionListener{
	private ReactionClic reactionClic;
	public UniteDeplacer(ReactionClic reactionClic) {
		this.reactionClic = reactionClic;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Unite uniteClique = reactionClic.getUniteClique();
		uniteClique.setdeplacer(true);

	}
}
