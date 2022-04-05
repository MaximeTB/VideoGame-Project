import java.sql.Array;
import java.util.ArrayList;

public abstract class Evenement{
    protected String Name; //Nom de l'événèment
    protected int niveau;
    protected String Type; //vaut J ou N si c'est un lieu (nuit ou jour) et vaut G si c'est un effet global
    protected String Tier; //1,2,3 ou liste -> stade d'apparition minimal de l'event
    protected int duree;



//constructeur
    public Evenement(String Name,int niveau,String Type,int duree){
        this.Name=Name;
        this.niveau=niveau;
        this.Type=Type;
        this.duree=duree;
    }


//getter
    public String getName() {
        return Name;
    }
    public int getNiveau() {
        return niveau;
    }
    public String getType() {
        return Type;
    }

//abstract methode
    public abstract void applyEvent(Game game);



    @Override
    public String toString() {
        return  "\n {Name='" + Name + '\'' +
                ", niveau=" + niveau +
                ", Type='" + Type + '\'' +
                "}";
    }
}
