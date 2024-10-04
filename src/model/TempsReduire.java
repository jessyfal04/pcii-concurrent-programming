package model;

public class TempsReduire extends Thread{
	
	private Temps temps;
	public static final int DELAY = 1000;
	
	public TempsReduire(Temps temps) {
		this.temps = temps;
	}
	
	@Override
	public void run() {
		while(true) {
			temps.decrementer();
			try {
				Thread.sleep(DELAY);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

