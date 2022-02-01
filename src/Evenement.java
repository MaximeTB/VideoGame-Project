import java.sql.Array;
import java.util.ArrayList;

public class Evenement{
    private String Name; //Nom de l'événèment
    private int niveau;
    private ArrayList<Effect> Effects ; //Liste des effets sur les stats de cet évènement
    private String Periode;

    public Evenement(String Name,int niveau,String Periode){
        this.Name=Name;
        this.niveau=niveau;
        this.Periode=Periode;
        this.Effects=new ArrayList<Effect>();
    }

    public void addEffect(Effect effet){
        this.Effects.add(effet);
    }
}
