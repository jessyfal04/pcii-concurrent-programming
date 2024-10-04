package model;

public class UniteDeplacement extends Thread {

    private Unite unite;
    public static final int DELAY = 75;

    public UniteDeplacement(Unite u){ unite = u; }

    @Override
    public void run(){
        while(true){
            //Si l'unite est en deplacement, on la deplace, en lui attribuant successivement les positions intermediaires
            if(unite.getEnDeplacement()){
                unite.deplacer();
            }
            try {
                Thread.sleep(DELAY);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}


