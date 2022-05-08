import java.sql.Array;
import java.util.ArrayList;

public abstract class Evenement{
    /**
     * Nom de l'Evenement
     */
    protected String Name; //Nom de l'événèment
    /**
     * Tier de l'Evenement , 1 2 ou 3. Plus le Tier de l'Evenement est eleve
     * plus son impact sur le Liste , les Eleves et leurs stats est important et il apparait tard dans la partie.
     */
    protected int Tier;
    /**
     * Jour "J" ou Nuit "N".
     */
    protected String Type;//vaut J ou N si c'est un lieu (nuit ou jour) et vaut G si c'est un effet global
    /**
     * Nombre de tours que dure l'Evenement.
     */
    protected int duree;



//constructeur
    public Evenement(String Name,int Tier,String Type,int duree){
        this.Name=Name;
        this.Tier=Tier;
        this.Type=Type;
        this.duree=duree;
    }


//getter
    public String getName() {
        return Name;
    }
    public int getTier() {
        return Tier;
    }
    public String getType() {
        return Type;
    }

//abstract methode

    /**
     * Cette methode s'appliquera de plusieurs manières en fonction du type d'evenement.
     * @param game
     */
    public abstract void applyEvent(Game game);



    @Override
    public String toString() {
        return  "\n {Name='" + Name + '\'' +
                ", niveau=" + Tier +
                ", Type='" + Type + '\'' +
                "}";
    }
}
