import java.util.ArrayList;

public class SoireSpecial extends EventSurLieu{
    private ArrayList<String> motCle; //mot cle qui applique un modificateur à ce lieu
    private ArrayList<String> modificateur;//donne la valeur du bonus/malus confere par le mot cle correspondant


    public SoireSpecial(String Name, int niveau) {
        super(Name, niveau);
    }

    public void applyEvent(Game game) {
        super.applyEvent(game);


    }
}
