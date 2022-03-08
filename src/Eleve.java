import java.util.ArrayList;
import java.util.Random;

public class Eleve implements Comparable {
    private String name;
    private Integer studies;
    private Integer tired;
    private Integer cost;
    private ArrayList<Skills> skillsList=new ArrayList<>(3);
    private Lieu location;



    public Integer getStudies() {
        return studies;
    }
    public Integer getTired() {
        return tired;
    }
    public Integer getCost() {
        return cost;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Skills> getSkillsList(){
        return skillsList;
    }
    public Lieu getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setStudies(Integer studies) {
        this.studies = studies;
    }
    public void setTired(Integer tired) {
        this.tired = tired;
    }
    public void setCost(Integer cost) {
        this.cost = cost;
    }
    public void setLocation(Lieu location) {
        this.location = location;
    }

    public Eleve(){
        name = "Pierre";
        Random r1 = new Random();
        studies =  (int) (r1.nextGaussian()*1.2 + 6);   //on génère le niveau d'étude et le cout aléatoirement
        tired = 0;
        cost = r1.nextInt(4);
        if (cost<2 ){                                   //on ajuste le cout selon le niveau d'étude pour les cout 1 et 2
            if(studies>5){
                cost =1;
            }
            else{
                cost=0;
            }
        }
        this.AddRandomSkill();                          //on donne plus de skills au eleves les plus cher
        if (cost >1){
            this.AddRandomSkill();
        }
        if (cost >2){
            this.AddRandomSkill();
        }
    }

    public  Eleve(String name){
        this();
        this.name=name;
    }

    public void AddRandomSkill(){
        this.skillsList.add(new Skills(new Random().nextInt(5), 1+new Random().nextInt(3)));
    }

    @Override
    public String toString() {
        return "name=" + name +
                ", studies=" + studies +
                ", cost=" + cost +
                ", skillsList=" + skillsList +"\n" ;
    }

    /*@Override
    public int compareTo(Eleve comp){
        int compareCost = ((Eleve)comp).getCost();
        return this.cost-compareCost;
    }*/

    @Override
    public int compareTo(Object o) {
        int compareCost = ((Eleve)o).getCost();
        return this.cost-compareCost;
    }
}
