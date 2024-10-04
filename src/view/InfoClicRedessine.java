package view;

public class InfoClicRedessine extends Thread {
	private final int DELAY = 100;
	private InfoClic infoClic;

	public InfoClicRedessine(InfoClic infoClic) {
		this.infoClic = infoClic;
	}

	@Override
	public void run() {
		while (true) {
			// On redessine le panneau
			infoClic.repaint();
			try { Thread.sleep(DELAY); }
			catch (Exception e) { e.printStackTrace(); }
		}
	}
}
