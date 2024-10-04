package model;

public class Unite extends Entite{

    public static final int PRIXRECRUTEMENT = 8;//discuter
    private static int idCounter = 0;
    private int id;

    public Unite(Position p){
        super(p);

        id = idCounter;
        idCounter++;
        
        UniteDeplacement d = new UniteDeplacement(this);
        d.start();
    }


    /* getters */
    public int getId(){
        return id;
    }
}
