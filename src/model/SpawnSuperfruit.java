package model;

import java.util.ArrayList;

public class SpawnSuperfruit extends Thread {
	private ArrayList <Fruits> fruitsList;
	public static final int DELAY = 35000; // 35 secondes

	public SpawnSuperfruit(ArrayList<Fruits> fruitsList) {
		this.fruitsList = fruitsList;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			fruitsList.add(new Fruits(new Position(), Fruits.Variete.SUPERFRUIT));
		}
	}
}
