package view;

import java.awt.*;

import javax.swing.JPanel;

import main.Main;

public class PanelFinPartie extends JPanel {
	private final int X = (int) (1200 * Main.GLOBAL_RATIO_WIDTH);
	private final int Y = (int) (800 * Main.GLOBAL_RATIO_HEIGHT);

	private String message;
	private Color couleur;
	
	public PanelFinPartie(String message, Color couleur) {
		this.message = message;
		this.couleur = couleur;
		setPreferredSize(new Dimension(X, Y));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		// Fond coloré
		g.setColor(couleur);
		g.fillRect(0, 0, X, Y);

		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.BLACK);

		// Message centré
		FontMetrics fm = g.getFontMetrics();
		int x = (X - fm.stringWidth(message)) / 2;
		int y = (fm.getAscent() + (Y - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(message, x, y);
	}
}
