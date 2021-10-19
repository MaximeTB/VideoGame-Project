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

    public void silliker(Liste liste){
        liste.setArgent(liste.getArgent()+20);
    }

    public void etudie(){
        etude+=10;
        etude=etude>100 ? 100 : etude;
    }

    public void soiree(Liste liste){
        fatigue+=10;
        liste.setPopularite(liste.getPopularite()+10);
    }

}
