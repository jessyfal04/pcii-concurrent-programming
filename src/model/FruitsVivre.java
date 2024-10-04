package model;
public class FruitsVivre extends Thread{
	private final int DELAY = 5000;
	private Fruits fruit;

	public FruitsVivre(Fruits fruit) {
		this.fruit = fruit;
	}
	
	@Override
    public void run() {
        while (true) {
            // On réduit la vie du fruit
            fruit.reduireVie();
            try { Thread.sleep(DELAY); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
