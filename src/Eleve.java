import java.util.ArrayList;

public class Eleve {
    private String name;
    private Integer etude;
    private Integer fatigue;
    private Integer invest;
    //private ArrayList<motcle>;


    public Integer getEtude() {
        return etude;
    }

    public Integer getFatigue() {
        return fatigue;
    }

    public Integer getInvest() {
        return invest;
    }

    public String getName() {
        return name;
    }

    public Eleve(){

    }

    public  Eleve(String name){
        this.name=name;
        etude=0;
        fatigue=0;
        invest=0;
    }
}
