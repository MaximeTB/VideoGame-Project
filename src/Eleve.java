import java.util.ArrayList;
import java.util.Random;

public class Eleve implements Comparable {
    private String name;
    private Integer studies;
    private Integer tired;
    private Integer cost;
    private ArrayList<Skills> skillsList=new ArrayList<>(3);
    private Lieu locationJ,locationN;
    private Pole pole;
    private Boolean skipTurn=false;
    private static PoolOfName Names=new PoolOfName("data/prenom.csv");

    private ArrayList<Skills> TEMPORAIREskills=new ArrayList<>();

    private static PoolsOfSkills pool;


//constructeur

    public Eleve(PoolsOfSkills pool){
        name = Names.RandomName();
        this.pool=pool;
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
        this.AddRandomSkill(pool);                          //on donne plus de skills au eleves les plus cher
        if (cost >1){
            this.AddRandomSkill(pool);
        }
        if (cost >2){
            this.AddRandomSkill(pool);
        }
    }

    /*  Eleve(String name){
        this();
        this.name=name;
    }*/


    //getter
    public PoolsOfSkills getPool(){
        return pool;
    }
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
    public Lieu getLocation(String type){
        if(type.equals("J")) return locationJ;
        else if(type.equals("N"))return locationN;
        else return null;
    }
    public Pole getPole() {
        return pole;
    }
    public boolean getSkipTurn(){return  skipTurn;}
    public ArrayList<SkillsOnLieu> getSkillsOnLieuList(){
        ArrayList<SkillsOnLieu> retour = new ArrayList<>();
        for(Skills S : skillsList){
            if(S.getClass()==SkillsOnLieu.class){
                retour.add((SkillsOnLieu) S);
            }
        }
        return retour;
    }
    public ArrayList<SkillOnRecruit> getSkillsOnRecruitList(){
        ArrayList<SkillOnRecruit> retour = new ArrayList<>();
        for(Skills S : skillsList){
            if(S.getClass()==SkillOnRecruit.class){
                retour.add((SkillOnRecruit) S);
            }
        }
        return retour;
    }

//fin getter

    //setter
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
    public void setLocation(Lieu location, String moment) {
        if(moment.equals("J")){
            this.locationJ = location;
        }else{this.locationN=location;}
    }
    public void setPole(Pole P){this.pole=P;}//attention, utiliser pole.addMembre pour placer des élèves, pas cette méthode.
    public void setSkipTurn(boolean skip){this.skipTurn=skip;}

    public void Etude(int i){
        studies+=i;
        if(studies>20){
            studies=20;
        }
        else if(studies<0){
            studies=0;
        }
    }
    public void Fatigue(int i){
        tired+=i;
        if (tired<0){
            tired=0;
        }
    }
    public void Cout(int i){
        cost+=i;
    }


//fin setter

    public Skills AddRandomSkill(PoolsOfSkills pool){
        Skills skill;
        skill= pool.RandomSkill();
        this.getSkillsList().add(skill);
        return  skill;
    }//a changer asbolument !!!!  dans tout les cas, la méthode doit renvoyer le skill qui a été donné

    public String TestBureau(){
        Pole P=this.getPole();
        if(P.getClass()==Bureau.class){
            return ((Bureau) P).findRole(this);
        }
        else{
            return null;
        }
    }//si l'élève est dans le bureau renvois son role, sinon, renvois null


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

    public static void main(String[] arg){
        int i;
        //PoolsOfSkills skills = new PoolsOfSkills()
        for(i=0;i<15;i++){
            //System.out.println(new Eleve());
        }
    }
}
