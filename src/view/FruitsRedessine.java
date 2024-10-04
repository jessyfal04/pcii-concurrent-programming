package view;

public class FruitsRedessine extends Thread {
	private final int DELAY = 100;
	private FruitsPanel fruitsPanel;

	public FruitsRedessine(FruitsPanel fruitsPanel) {
		this.fruitsPanel = fruitsPanel;
	}

	@Override
	public void run() {
		while (true) {
			// On redessine le panneau
			fruitsPanel.repaint();
			try { Thread.sleep(DELAY); }
			catch (Exception e) { e.printStackTrace(); }
		}
	}
}
