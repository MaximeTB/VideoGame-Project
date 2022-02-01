import java.util.ArrayList;
import java.util.Random;

public class Eleve implements Comparable {
    private String name;
    private Integer studies;
    private Integer tired;
    private Integer cost;
    private ArrayList<Skills> skillsList=new ArrayList<>(3);



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
    //les 3 méthodes précédentes sont surement à suprimer -> seront a définir en tant que lieu et récupéré avec applyZoneEffect





    public void applyZoneEffect(Lieu L){
        //à défnir quand les lieux seront fait. récupère le modificiateur a appliquer à l'élève dans ce lieu a la fin du tour
    }




    @Override
    public String toString() {
        return "name=" + name +
                ", studies=" + studies +
                ", cost=" + cost +
                ", skillsList=" + skillsList +"\n" ;
    }


    @Override
    public int compareTo(Object o) {
        int compareCost = ((Eleve)o).getCost();
        return this.cost-compareCost;
    }
}
