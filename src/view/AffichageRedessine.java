package view;

public class AffichageRedessine extends Thread{
    private Affichage monAffichage;
    public static final int DELAY = 50;

    public AffichageRedessine(Affichage a){ monAffichage = a; }

    @Override
    public void run(){
        while(true){
            monAffichage.revalidate();
            monAffichage.repaint();
            try {
                Thread.sleep(DELAY);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
