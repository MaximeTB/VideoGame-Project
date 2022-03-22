import java.sql.Array;
import java.util.ArrayList;

public abstract class Evenement{
    protected String Name; //Nom de l'événèment
    protected int niveau;
    protected ArrayList<Effect> Effects ; //Liste des effets sur les stats de cet évènement
    protected String Type; //vaut J ou N si c'est un lieu (nuit ou jour) et vaut G si c'est un effet global
    protected int EOP=0; //effect on pop
    protected int EOM=0; //effect on money
    protected int EOT=0; //effect on tired
    protected int EOS=0; //effect on studies
    protected int EOA=0; //effect on admin
    protected int EOC=0; //effect on cohesion
    protected int EOPV=0; //effect on PV


//constructeur
    public Evenement(String Name,int niveau,String Type){
        this.Name=Name;
        this.niveau=niveau;
        this.Type=Type;
        this.Effects=new ArrayList<Effect>();
    }

    public void addEffect(Effect effet){
        this.Effects.add(effet);
    }

//getter
    public String getName() {
        return Name;
    }
    public int getNiveau() {
        return niveau;
    }
    public ArrayList<Effect> getEffects() {
        return Effects;
    }
    public String getType() {
        return Type;
    }

//abstract methode
    public abstract void  applyEvent(Game game);



    @Override
    public String toString() {
        return  "\n {Name='" + Name + '\'' +
                ", niveau=" + niveau +
                ", Effects=" + Effects +
                ", Type='" + Type + '\'' +
                "}";
    }
}
