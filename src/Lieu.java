import java.util.ArrayList;

public class Lieu {
    private String Nom;
    private ArrayList<Eleve> ElevePresents;
    private int CapMax;




    //Getters


    public String getNom() {
        return Nom;
    }

    public ArrayList<String> getElevePresents() {
        ArrayList<String> EleveDansLieu = new ArrayList<String>();
        for (Eleve i : ElevePresents){
             EleveDansLieu.add(i.getName());
        }
        return EleveDansLieu;
    }

    public int getCapMax() {
        return CapMax;
    }


    //Methodes

}

