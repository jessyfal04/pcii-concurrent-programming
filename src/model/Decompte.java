package model;

public class Decompte extends Thread {

    private Banque banque;
    public static final int DELAY = 200;

    public Decompte(Banque b){ banque = b; }

    @Override
    public void run(){
        while(true){
            //Si l'unite est en deplacement, on la deplace, en lui attribuant successivement les positions intermediaires
            if(banque.getDecompte()){
                banque.modifier();
            }
            try {
                Thread.sleep(DELAY);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
