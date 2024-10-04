package view;

public class MetriquesRedessinne extends Thread {
	private final int DELAY = 100;
	private Metriques metriques;

	public MetriquesRedessinne(Metriques metriques) {
		this.metriques = metriques;
	}

	@Override
	public void run() {
		while (true) {
			// On redessine le panneau
			metriques.repaint();
			try { Thread.sleep(DELAY); }
			catch (Exception e) { e.printStackTrace(); }
		}
	}
}