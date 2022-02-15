import java.util.ArrayList;

public class Lieu {
    private String name;
    private String type;        //J pour les lieux du jour et N ceux de la nuit
    private ArrayList<Eleve> ElevePresents;
    private int capMax;     //capacité max en élève. les lieux sans limite sont placé de base a 100.
    private String effect;
    private Boolean available=true;


    public Lieu(String name, Boolean available){
        this.name = name;
        this.available = available;
        this.capMax = 100;
    }

    public Lieu(String name, Boolean available, int capMax){
        this(name, available);
        this.capMax=capMax;
    }

    //Getters


    public String getname() {
        return name;
    }

    public ArrayList<String> getNameElevePresents() {
        ArrayList<String> EleveDansLieu = new ArrayList<String>();
        for (Eleve i : ElevePresents){
             EleveDansLieu.add(i.getName());
        }
        return EleveDansLieu;
    }

    public ArrayList<Eleve> getElevePresents() {
        return ElevePresents;
    }

    public int getCapMax() {
        return capMax;
    }


    //Methodes

    public void desactivate(){
        this.available=false;
    }
    public void activate(){
        this.available=true;
    }

    public void placeStudent(Eleve e){
        if(available){
            if(this.ElevePresents.size()<capMax){
                this.ElevePresents.add(e);
            }
            else{
                System.out.println("Cet endroit est déja plein !");
            }

        }else{
            System.out.println("Placement impossible pour le moment");
        }
    }



}

