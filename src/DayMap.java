import java.util.ArrayList;


//class temporaire, a voir si on la garde
public class DayMap {
    private ArrayList<Lieu> zones = new ArrayList<>();


    public DayMap(){
        zones.add(new Lieu("Amphi", true, "D"));
        zones.add(new Lieu("Salle de TP", false, "D"));

    }

}
