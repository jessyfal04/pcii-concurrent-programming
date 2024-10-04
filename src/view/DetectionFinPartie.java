package view;

import java.awt.Color;

import javax.swing.JFrame;

import model.Banque;
import model.Temps;

public class DetectionFinPartie extends Thread{
	public static final int DELAY = 50;
	private static final int SEUILRICHESSE = 75;

	private JFrame frame;
	private Banque banque;
	private Temps temps;

	public DetectionFinPartie(JFrame frame, Banque banque, Temps temps) {
		this.frame = frame;
		this.banque = banque;
		this.temps = temps;
	}

	@Override
	public void run() {
		while(true) {
			if(banque.getArgent() < 0) {
				frame.getContentPane().removeAll();
				frame.add(new PanelFinPartie("Banqueroute ! \n Score: " + banque.getArgent(), new Color(250, 177, 160)));
				frame.pack();
				frame.setVisible(true);
			} else if(banque.getArgent() >= DetectionFinPartie.SEUILRICHESSE) {
				frame.getContentPane().removeAll();
				frame.add(new PanelFinPartie("Richesse ! \n Score: " + banque.getArgent(), new Color(85, 239, 196)));
				frame.pack();
				frame.setVisible(true);
			} else if (temps.getSecondes() < 0) {
				frame.getContentPane().removeAll();
				frame.add(new PanelFinPartie("Temps écoulé ! \n Score: " + banque.getArgent(), new Color(116, 185, 255)));
				frame.pack();
				frame.setVisible(true);
			}

			try {
                Thread.sleep(DELAY);
            }catch(Exception e){
                e.printStackTrace();
            }
		}
	}
}