import java.util.ArrayList;

public class Pole {
    String name;
    ArrayList<Eleve> Member=null;
    private int XP=0;
    private int level=1;

    public Pole(String name) {
        this.name=name;
    }


    public void addMember(Eleve e){
        this.Member.add(e);
    }
    public void levelUp(){this.level++;}
}
