import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Array;
import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.Random;


/**
 * Cette classe regroupe plusieurs attribut de type ArrayList qui repertorient et trient les Skills.
 */
public class PoolsOfSkills {
    private Random rand;

    /**
     * Regroupe tout les Skills sans distinction.
     */
    private ArrayList<Skills> AllSkills;

    /**
     * Regroupe les Skills de type SkillOnRecruit.
     */
    private ArrayList<Skills> SkillOnRecruit;

    /**
     * Regroupe les Skills de type SkillOnlieu.
     */
    private ArrayList<Skills> SkillOnLieu;

    /**
     * Regroupe les Skills de type SkillBDA.
     */
    private ArrayList<Skills> SkillBDA;

    /**
     * Regroupe les SKills de type SkillOnShop.
     */
    private ArrayList<Skills> SkillOnShop;

    /**
     * Regroupe les Skills de type SkillOnOther.
     */
    private ArrayList<Skills> SkillOnOther;

    /**
     * Regroupe les Skills de type SkillOnPole, excepte les Gris qui ne peuvent s'acquerir après la creation d'un Eleve.
     */
    private ArrayList<Skills> SkillOnPole;
    /**
     * Regroupe les Skills "Blue", impactant le Pole Animation.
     */
    private ArrayList<Skills> BlueList; //Pole Animation
    /**
     * Regroupe les Skills "Yellow", qui impactent le Pole Bdls.
     */
    private ArrayList<Skills> YellowList; //
    /**
     * Regroupe les Skills "Red", qui impactent le Pole BDS.
     */
    private ArrayList<Skills> RedList; //
    /**
     * Regroupe les Skills "Grey", qui s'obtiennent a la creation d'un Eleve mais après.
     */
    private ArrayList<Skills> GreyList; // Ne peux s'obtenir qu'a la creation de l'elève
    /**
     * Regroupe les Skills "Green", qui impactent le Pole BDTech.
     */
    private ArrayList<Skills> GreenList; //
    /**
     * Regroupe les Skills "Black", qui impactent le Pole Cave.
     */
    private ArrayList<Skills> BlackList; //

    /**
     * Dans differents fichiers csv (un par Classe de Skill) ont ete repertories les differents Skills et leurs caracteristiques.<br/>
     * Chacun de ces fichiers est lu ligne par ligne (1 ligne = 1 Skill),
     * pour chaque ligne un String est genere puis convertie en un tableau de String en separant ce qui se trouve entre chaque ";", et enfin en utilisant le contenu de chacun de ces String dans l'ordre, le Skill est cree.
     * @param SkillsOnPole Lien du fichier csv repertoriant les SkillsOnPole. Permet de remplir la liste attribut SkillOnPole.
     * @param SkillsOnLieu Lien du fichier csv repertoriant les SkillsOnLieu. Permet de remplir la liste attribut SkillsOnPole.
     * @param SkillsOnOthers Lien du fichier csv repertoriant les SkillsOnRecruit. Permet de remplir la liste attribut SkillOnOthers.
     * @param SkillsOnRecruit Lien du fichier csv repertoriant les SkillsOnRecruit. Permet de remplir la liste attribut SkillsOnRecruit.
     * @param Lieux Pour la creation des SkillsOnLieu et SkillsOnOthers, il est necessaire de disposer des Lieux crees au debut de partie (pour les LieuCible).<br/>
     *              On entre donc la liste de ces Lieux. Cela impose donc que les Lieux soient generes avant les Skills.
     * @param Poles Pour la creation des SkillsOnPole, il est necessaire de disposer des Poles crees au debut de partie.<br/>
     *              On entre donc la liste de ces Poles. Cela impose donc que les Poles soient generes avant les Skills.
     */
    public PoolsOfSkills(String SkillsOnPole, String SkillsOnLieu, String SkillsOnOthers, String SkillsOnRecruit, ArrayList<Lieu> Lieux,ArrayList<Pole>  Poles){
        //Initialisation de toutes les listes
        AllSkills = new ArrayList<Skills>();

        SkillOnRecruit = new ArrayList<Skills>();

        SkillOnLieu = new ArrayList<Skills>();

        SkillBDA = new ArrayList<Skills>();

        SkillOnShop = new ArrayList<Skills>();

        SkillOnOther = new ArrayList<Skills>();

        SkillOnPole = new ArrayList<Skills>();
        BlueList = new ArrayList<Skills>();
        BlackList = new ArrayList<Skills>();
        GreenList = new ArrayList<Skills>();
        YellowList = new ArrayList<Skills>();
        RedList = new ArrayList<Skills>();
        GreyList = new ArrayList<Skills>();
        //


        Skills skill;

        //Pour SkillsOnLieu et SkillsOnOther
        ArrayList<Lieu> ListeLieu= new ArrayList<Lieu>();
        ArrayList<String> StatCible= new ArrayList<String>();
        ArrayList<Integer> ValueCible= new ArrayList<Integer>();
        String Stat;
        int ValueEffect;

        //Pour SkillsOnPole
        ArrayList<Pole> ListePole = new ArrayList<Pole>();

        //Pour les SkillsOnRecruit
        int EOM,EOC,EOA;

        String name;

        //Creation des SkillOnLieu
        try{
            BufferedReader buf = new BufferedReader(new FileReader(SkillsOnLieu));
            buf.readLine();
            String s = buf.readLine();
            while(s!=null){
                ListeLieu.clear();
                StatCible.clear();
                ValueCible.clear();
                s.replaceAll("\"", "");
                String fields[] = s.split(";");


                for(int i=0 ;i<(fields.length - 2)/3;i++){
                    name = fields[3*i+2];
                    for(Lieu l : Lieux){
                        if(l.getname().equals(name)){
                            ListeLieu.add(l);
                            break;
                        }
                    }
                    StatCible.add(fields[3*i+3]);
                    ValueCible.add(Integer.parseInt(fields[3*i+4]));


                }
                skill=new SkillsOnLieu(fields[1],fields[0],ListeLieu,StatCible,ValueCible);
                SkillOnLieu.add(skill);
                AllSkills.add(skill);

                String c = skill.getColor();

                switch(c){
                    case "Blue" : BlueList.add(skill);break;
                    case "Yellow" : YellowList.add(skill);break;
                    case "Green" : GreenList.add(skill);break;
                    case "Grey" : GreyList.add(skill);break;
                    case "Red" : RedList.add(skill);break;
                    case "Black" : BlackList.add(skill);break;
                    default : break;
                }

                s = buf.readLine();
            }
            buf.close();
        }
        catch(Exception e){
            System.out.println("Maybe the file isn't there ?");
            e.printStackTrace();
        }


        //Creation des SkillOnOthers
        try{
            BufferedReader buf = new BufferedReader(new FileReader(SkillsOnOthers));
            buf.readLine();
            String s = buf.readLine();
            while(s!=null){
                ListeLieu.clear();
                StatCible.clear();
                ValueCible.clear();
                s.replaceAll("\"", "");
                String fields[] = s.split(";");


                for(int i=0 ;i<(fields.length - 2)/3;i++){
                    name = fields[i+2];
                    for(Lieu l : Lieux){
                        if(l.getname().equals(name)){
                            ListeLieu.add(l);
                        }
                    }
                    StatCible.add(fields[i+3]);
                    ValueCible.add(Integer.parseInt(fields[i+4]));
                }
                skill=new SkillOnOthers(fields[1],fields[0],ListeLieu,StatCible,ValueCible);
                SkillOnOther.add(skill);
                AllSkills.add(skill);

                switch(skill.getColor()){
                    case "Blue" : BlueList.add(skill);
                    case "Yellow" : YellowList.add(skill);
                    case "Green" : GreenList.add(skill);
                    case "Grey" : GreyList.add(skill);
                    case "Red" : RedList.add(skill);
                    case "Black" : BlackList.add(skill);
                    default : break;
                }
                s = buf.readLine();
            }
            buf.close();
        }
        catch(Exception e){
            System.out.println("Maybe the file isn't there ?");
            e.printStackTrace();
        }


        //Creation SkillOnPole
        try{
            BufferedReader buf = new BufferedReader(new FileReader(SkillsOnPole));
            buf.readLine();
            String s = buf.readLine();
            while(s!=null) {
                ListePole.clear();
                s.replaceAll("\"", "");
                String fields[] = s.split(";");

                name=fields[4];
                for(Pole p : Poles){
                    if(p.getName().equals(name)){
                        ListePole.add(p);
                    }
                }
                ValueEffect= Integer.parseInt(fields[3]);

                skill= new SkillOnPole(fields[1],fields[0],ListePole,fields[2],ValueEffect);
                AllSkills.add(skill);
                SkillOnPole.add(skill);
                switch(skill.getColor()){
                    case "Blue" : BlueList.add(skill);
                    case "Yellow" : YellowList.add(skill);
                    case "Green" : GreenList.add(skill);
                    case "Grey" : GreyList.add(skill);
                    case "Red" : RedList.add(skill);
                    case "Black" : BlackList.add(skill);
                    default : break;
                }

                s = buf.readLine();
            }
            buf.close();
        }
        catch(Exception e) {
            System.out.println("Maybe the file isn't there ?");
            e.printStackTrace();
        }

        //Creation de SkillOnRecruit
        try{
            BufferedReader buf = new BufferedReader(new FileReader(SkillsOnRecruit));
            buf.readLine();
            String s = buf.readLine();
            while(s!=null){
                s.replaceAll("\"", "");
                String fields[] = s.split(";");
                EOM= Integer.parseInt(fields[2]);
                EOC=Integer.parseInt(fields[3]);
                EOA=Integer.parseInt(fields[4]);

                skill=new SkillOnRecruit(fields[1],fields[0],EOM,EOC,EOA);
                AllSkills.add(skill);
                SkillOnRecruit.add(skill);
                switch(skill.getColor()){
                    case "Blue" : BlueList.add(skill);
                    case "Yellow" : YellowList.add(skill);
                    case "Green" : GreenList.add(skill);
                    case "Grey" : GreyList.add(skill);
                    case "Red" : RedList.add(skill);
                    case "Black" : BlackList.add(skill);
                    default : break;
                }
                s = buf.readLine();
            }
            buf.close();
        }
        catch(Exception e){
            System.out.println("Maybe the file isn't there ?");
            e.printStackTrace();
        }

        Skills Metro=new SkillOnShop("Grey","Carte Metro");
        AllSkills.add(Metro);
        GreyList.add(Metro);

    }

    /**
     * Pioche un Skill aleatoirement dans tous les Skills disponibles.
     * @return Skill obtenu lors du tirage.
     */
    public Skills RandomSkill(){
        Skills skill;
        rand = new Random();
        skill=this.getAllSkills().get(rand.nextInt(this.getAllSkills().size()));
        return skill;
    }

    /**
     * Pioche un Skill aleatoirement dans tous les SkillsOnLieu disponibles.
     * @return Skill obtenu lors du tirage.
     */
    public Skills RandomSkillOnLieu(){
        Skills skill;
        skill=this.getSkillOnLieu().get(rand.nextInt(this.getSkillOnLieu().size()));
        return skill;
    }

    /**
     * Pioche un Skill aleatoirement dans tous les SkillsBDA disponibles.
     * @return Skill obtenu lors du tirage.
     */
    public Skills RandomSkillBDA(){
        Skills skill;
        skill=this.getSkillBDA().get(rand.nextInt(this.getSkillBDA().size()));
        return skill;
    }

    /**
     * Pioche un Skill aleatoirement dans tous les SkillsOnShop disponibles.
     * @return Skill obtenu lors du tirage.
     */
    public Skills RandomSkillOnShop(){
        Skills skill;
        skill=this.getSkillOnShop().get(rand.nextInt(this.getSkillOnShop().size()));
        return skill;
    }

    /**
     * Pioche un Skill aleatoirement dans tous les SkillsOnOthers disponibles.
     * @return Skill obtenu lors du tirage.
     */
    public Skills RandomSkillOnOthers(){
        Skills skill;
        skill=this.getSkillOnOther().get(rand.nextInt(this.getSkillOnOther().size()));
        return skill;
    }

    /**
     * Pioche un Skill aleatoirement dans tous les SkillsOnPole disponibles.
     * @return Skill obtenu lors du tirage.
     */
    public Skills RandomSkillOnPole(){
        Skills skill;
        skill=this.getSkillOnPole().get(rand.nextInt(this.getSkillOnPole().size()));
        return skill;
    }

    /**
     * Pioche un Skill aleatoirement dans tous les Skills "Blue" disponibles.
     * @return Skill obtenu lors du tirage.
     */
    public Skills RandomSkillBlue(){
        Skills skill;
        skill=this.getBlueList().get(rand.nextInt(this.getBlueList().size()));
        return skill;
    }

    /**
     * Pioche un Skill aleatoirement dans tous les Skills "Yellow" disponibles.
     * @return Skill obtenu lors du tirage.
     */
    public Skills RandomSkillYellow(){
        Skills skill;
        skill=this.getYellowList().get(rand.nextInt(this.getYellowList().size()));
        return skill;
    }

    /**
     * Pioche un Skill aleatoirement dans tous les Skills "Red" disponibles.
     * @return Skill obtenu lors du tirage.
     */
    public Skills RandomSkillRed(){
        Skills skill;
        skill=this.getRedList().get(rand.nextInt(this.getRedList().size()));
        return skill;
    }

    /**
     * Pioche un Skill aleatoirement dans tous les Skills "Grey" disponibles.
     * @return Skill obtenu lors du tirage.
     */
    public Skills RandomSkillGrey(){
        Skills skill;
        skill=this.getGreyList().get(rand.nextInt(this.getGreyList().size()));
        return skill;
    }

    /**
     * Pioche un Skill aleatoirement dans tous les Skills "Green" disponibles.
     * @return Skill obtenu lors du tirage.
     */
    public Skills RandomSkillGreen(){
        Skills skill;
        skill=this.getGreenList().get(rand.nextInt(this.getGreenList().size()));
        return skill;
    }

    /**
     * Pioche un Skill aleatoirement dans tous les Skills "Black" disponibles.
     * @return Skill obtenu lors du tirage.
     */
    public Skills RandomSkillBlack(){
        Skills skill;
        skill=this.getBlackList().get(rand.nextInt(this.getBlackList().size()));
        return skill;
    }

//Getter
    public ArrayList<Skills> getAllSkills() {
        return AllSkills;
    }
    public ArrayList<Skills> getSkillOnLieu() {
        return SkillOnLieu;
    }
    public ArrayList<Skills> getSkillBDA() {
        return SkillBDA;
    }
    public ArrayList<Skills> getSkillOnShop() {
        return SkillOnShop;
    }
    public ArrayList<Skills> getSkillOnOther() {
        return SkillOnOther;
    }
    public ArrayList<Skills> getSkillOnPole() {
        return SkillOnPole;
    }
    public ArrayList<Skills> getBlueList() {
        return BlueList;
    }
    public ArrayList<Skills> getYellowList() {
        return YellowList;
    }
    public ArrayList<Skills> getRedList() {
        return RedList;
    }
    public ArrayList<Skills> getGreyList() {
        return GreyList;
    }
    public ArrayList<Skills> getGreenList() {
        return GreenList;
    }
    public ArrayList<Skills> getBlackList() {
        return BlackList;
    }

//

//Setter
    public void setAllSkills(ArrayList<Skills> allSkills) {
        AllSkills = allSkills;
    }
    public void setSkillOnLieu(ArrayList<Skills> skillOnLieu) {
        SkillOnLieu = skillOnLieu;
    }
    public void setSkillBDA(ArrayList<Skills> skillBDA) {
        SkillBDA = skillBDA;
    }
    public void setSkillOnShop(ArrayList<Skills> skillOnShop) {
        SkillOnShop = skillOnShop;
    }
    public void setSkillOnOther(ArrayList<Skills> skillOnOther) {
        SkillOnOther = skillOnOther;
    }
    public void setSkillOnPole(ArrayList<Skills> skillOnPole) {
        SkillOnPole = skillOnPole;
    }
    public void setBlueList(ArrayList<Skills> blueList) {
        BlueList = blueList;
    }
    public void setYellowList(ArrayList<Skills> yellowList) {
        YellowList = yellowList;
    }
    public void setRedList(ArrayList<Skills> redList) {
        RedList = redList;
    }
    public void setGreyList(ArrayList<Skills> greyList) {
        GreyList = greyList;
    }
    public void setGreenList(ArrayList<Skills> greenList) {
        GreenList = greenList;
    }
    public void setBlackList(ArrayList<Skills> blackList) {
        BlackList = blackList;
    }
//
}
