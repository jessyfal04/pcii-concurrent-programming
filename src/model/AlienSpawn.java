package model;

import java.util.ArrayList;

public class AlienSpawn extends Thread{

	private ArrayList<Alien> aliensList;
	private ArrayList<Fruits> fruitsList;
	private ArrayList<Unite> unitesList;
	public static final int DELAY = 45000; // 45 secondes

	public AlienSpawn(ArrayList<Alien> aliensList, ArrayList<Fruits> fruitsList, ArrayList<Unite> unitesList){
		this.aliensList = aliensList;
		this.fruitsList = fruitsList;
		this.unitesList = unitesList;
	}

	@Override
	public void run(){
		while(true){
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			aliensList.add(new Alien(new Position(), fruitsList, unitesList));

		}
	}

	
}
