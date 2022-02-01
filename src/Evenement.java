import java.util.ArrayList;

public class Evenement {
    int niveau;
    int type;
    ArrayList<Integer> effectlist;

    public Evenement(int niveau, int type, ArrayList<Integer> effectlist) {
        this.niveau = niveau;
        this.type = type;
        this.effectlist = effectlist;
    }
}
