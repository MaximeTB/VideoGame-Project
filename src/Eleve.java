import java.util.ArrayList;
import java.util.Random;

public class Eleve {
    private String name;
    private Integer studies;
    private Integer tired;
    private Integer loyalty;
    private Skills[] skillsList = new Skills[3];
    //private ArrayList<motcle>;


    public Integer getStudies() {
        return studies;
    }

    public Integer getTired() {
        return tired;
    }

    public Integer getLoyalty() {
        return loyalty;
    }

    public String getName() {
        return name;
    }

    public Skills[] getSkillsList(){
        return skillsList;
    }

    public void initSKills(){
        for (int i = 0; i<3; i++){
            this.skillsList[i] = new Skills(0, 0);
        }
    }

    public Eleve(){
        name = "fdp";
        Random r1 = new Random();
        Random r2 = new Random();
        studies =  (int) (r1.nextGaussian()*1.2 + 6);
        tired = 0;
        loyalty = r1.nextInt(4);
        this.initSKills();
        this.skillsList[0] = new Skills(1 + r1.nextInt(5), 1 + r2.nextInt(4));

    }

    public  Eleve(String name){
        this.name=name;
        studies=0;
        tired=0;
        loyalty=0;
    }

    public void silliker(Liste liste){
        liste.setArgent(liste.getArgent()+20);
    }

    public void studying(){
        studies+=10;
        studies=studies>100 ? 100 : studies;
    }

    public void soiree(Liste liste){
        tired+=10;
        liste.setPopularite(liste.getPopularite()+10);
    }

}
