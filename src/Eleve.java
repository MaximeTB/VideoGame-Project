import java.util.ArrayList;
import java.util.Random;

/**
 *
 */

public class Eleve implements Comparable {
    /**
     * Nom de l'Eleve. Attribut fix.
     */
    private final String name;
    /**
     * Niveau d'Etude de l'elève. Evolue necessairement au court de la partie.
     */
    private Integer studies;
    /**
     * Niveau de fatigue de l'eleve. Evolue necessairement au court de la partie.
     */
    private Integer tired;
    /**
     * Cout de recrutement de l'eleve. A priori fix.
     */
    private Integer cost;
    /**
     * Liste des Skills (attribut/capacites speciales) de l'Eleve. L'Eleve peut etre amene a gagner certain skills au court de la partie.
     */
    private ArrayList<Skills> skillsList=new ArrayList<>(3);
    /**
     * Lieu dans lequel se trouve l'Eleve le jour. Change à chaque tour/journee.
     */
    private Lieu locationJ;
    /**
     * Lieu dans lequel se trouve l'Eleve la nuit. Change à chaque tour/journee.
     */
    private Lieu locationN;
    /**
     * Pole auquel appartient l'Eleve.
     */
    private Pole pole;
    /**
     * Fixe a True quand l'Eleve est indisponible pendant un tour (exemple : niveau de fatigue max atteint).
     */
    private Boolean skipTurn=false;
    /**
     * Liste des noms dans laquelle on pioche aleatoirement a chaque creation d'Eleve. Elle est generee dans la classe Eleve.
     */
    private static PoolOfName Names=new PoolOfName("data/prenom.csv");
    /**
     * Liste des Skills disponibles dans laquelle on pioche à chaque fois qu'on ajoute un Skill.
     * Elle est generee a l'exterieur de la classe Eleve, c'est pourquoi Eleve possede deux constructeurs, un utilisee pour le premier Eleve cree prend en argument ce qui sera cette liste
     * puis un deuxieme constructeur qui sera utilise pour tous les autre Eleves qui ne touche pas a la liste.
     */
    private static PoolsOfSkills Skills;

    private static Random r = new Random();
//Constructeurs

    /**
     * Constructeur du premier Eleve cree.
     * Lors de la creation, le nom de l'Eleve est pioche aleatoirement dans Names.
     * Le niveau d'Etude est genere aleatoirement (compris entre 0 et 10),
     * ensuite le cout est genere aleatoirement (entre 1 et 3).
     * Puis en fonction du cout de l'Eleve on lui ajoute un ou plusieurs Skills. Pour un cout de 1 , un skills de depart, pour un cou de 2, 2 skills etc...
     * Enfin on ajuste le cout en fonction du niveau d'Etude. Si le niveau d'Etude est superieur a 5 on augmente le cout de 1.
     * @param Skills Liste des Skills qui initialise l'attribut static Skills et dans laquelle on piochera les skills
     */
    public Eleve(PoolsOfSkills Skills) {
        name = Names.RandomName();
        this.Skills = Skills;
        studies = (int) (r.nextInt(10));   //on genère le niveau d'etude et le cout aleatoirement
        tired = 0;
        cost = r.nextInt(3);
        this.AddRandomSkill(Skills);                          //on donne plus de skills au eleves les plus cher
        if (cost > 1) {
            this.AddRandomSkill(Skills);
        }
        if (cost > 2) {
            this.AddRandomSkill(Skills);
        }
        if (studies > 5) {    //on ajuste le cout selon le niveau d'etude
            cost += 1;
        }
    }



    /**
     * Meme constructeur que le premier mais ici la liste de Skills est déjà initialisee.
     */
    public Eleve() {
        name = Names.RandomName();
        studies = (int) (r.nextInt(10));   //on genère le niveau d'etude et le cout aleatoirement
        tired = 0;
        cost = r.nextInt(3);
        this.AddRandomSkill(Skills);                          //on donne plus de skills au eleves les plus cher
        if (cost > 1) {
            this.AddRandomSkill(Skills);
        }
        if (cost > 2) {
            this.AddRandomSkill(Skills);
        }
        if (studies > 5) {    //on ajuste le cout selon le niveau d'etude
            cost += 1;
        }
    }

    //getter
    public PoolsOfSkills getSkills(){
        return Skills;
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

    /**
     *
     * @param type "J" pour le locationJ, "N" pour locationN.
     * @return locationJ ou locationN en fonction de l'entrée.
     */
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
    public void setStudies(Integer studies) {
        this.studies = studies;
    }
    public void setTired(Integer tired) {
        this.tired = tired;
    }
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     *
     * @param location Lieu que le veut passer en attribut locationJ ou locationN.
     * @param moment "J" pour modifier locationJ ,"N" pour modifier locationN.
     */
    public void setLocation(Lieu location, String moment) {
        if(moment.equals("J")){
            this.locationJ = location;
        }else{this.locationN=location;}
    }
    public void setPole(Pole P){this.pole=P;}//attention, utiliser pole.addMembre pour placer des elèves, pas cette methode.
    public void setSkipTurn(boolean skip){this.skipTurn=skip;}

    /**
     * Permet d'incrementer le niveau d'Etude d'un nombre positif ou negatif.
     * Si le niveau d'Etude est incremente d'un nombre qui l'amene au dessus de 20, il sera fixe a 20,
     * idem si il est diminué en dessous de 0.
     * @param i nombre positif ou negatif que l'on ajoute au niveau d'Etude.
     */
    public void Etude(int i){
        studies+=i;
        if(studies>20){
            studies=20;
        }
        else if(studies<0){
            studies=0;
        }
    }

    /**
     * Permet d'incrementer le niveau de Fatigue d'un nombre positif ou negatif.
     * Si la fatigue est diminuee d'un nombre qui l'amene au dessous de 0 , elle sera fixee a 0.
     * Si elle depasse 5, l'attribut Skipturn de l'Eleve est alors passe a true, et la fatigue sera repassee a 0 quand l'Eleve se sera repose
     * @param i nombre positif ou negatif que l'on ajoute au niveau de Fatigue.
     */
    public void Fatigue(int i){
        tired+=i;
        if (tired<0){
            tired=0;
        }
        if (tired>5){
            this.setSkipTurn(true);
        }
    }



//fin setter

    /**
     * Ajoute à l'Eleve un Skill pioche aleatoirement dans une liste donnee en argument.
     * @param pool Liste des Skills dans laquelle on pioche
     * @return le Skill ajoute à l'Eleve.
     */
    public Skills AddRandomSkill(PoolsOfSkills pool){
        Skills skill;
        skill= pool.RandomSkill();
        this.getSkillsList().add(skill);
        return  skill;
    }//a changer asbolument !!!!  dans tout les cas, la methode doit renvoyer le skill qui a ete donne
/*
    public Skills AddTestSkill(){
        Skills skill = new SkillOnRecruit("gris","test", 0,0,0) ;
        this.getSkillsList().add(skill);
        return  skill;
    }*/

    /**
     * Teste si l'Eleve fait parti du Bureau et si c'est le cas quel role il y tien.
     * @return Le role de l'Eleve dans le Bureau si l'Eleve en fait partie, Null sinon.
     */
    public String TestBureau(){
        Pole P=this.getPole();
        if(P.getClass()==Bureau.class){
            return ((Bureau) P).findRole(this);
        }
        else{
            return null;
        }
    }//si l'elève est dans le bureau renvois son role, sinon, renvois null


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
/*
    public static void main(String[] arg){
        int i;
        PoolsOfSkills skills = new PoolsOfSkills();
        for(i=0;i<15;i++){
            System.out.println(new Eleve());
        }
    }*/
}
