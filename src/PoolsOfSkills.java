import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Array;
import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.Random;

public class PoolsOfSkills {
    private Random rand;


    private ArrayList<Skills> AllSkills;

    private ArrayList<Skills> SkillOnRecruit;

    private ArrayList<Skills> SkillOnLieu;
    private ArrayList<Skills> SkillBDA;


    private ArrayList<Skills> SkillOnShop;

    private ArrayList<Skills> SkillOnOther;

    private ArrayList<Skills> SkillOnPole; //Toutes les couleurs suaf le gris
    private ArrayList<Skills> BlueList; //Pole Animation
    private ArrayList<Skills> YellowList; //
    private ArrayList<Skills> RedList; //
    private ArrayList<Skills> GreyList; // Ne peux s'obtenir qu'à la création de l'élève
    private ArrayList<Skills> GreenList; //
    private ArrayList<Skills> BlackList; //

    private SkillColor Colors = new SkillColor();

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


    public Skills RandomSkill(){
        Skills skill;
        rand = new Random();
        skill=this.getAllSkills().get(rand.nextInt(this.getAllSkills().size()));
        return skill;
    }
    public Skills RandomSkillOnLieu(){
        Skills skill;
        skill=this.getSkillOnLieu().get(rand.nextInt(this.getSkillOnLieu().size()));
        return skill;
    }

    public Skills RandomSkillBDA(){
        Skills skill;
        skill=this.getSkillBDA().get(rand.nextInt(this.getSkillBDA().size()));
        return skill;
    }

    public Skills RandomSkillOnShop(){
        Skills skill;
        skill=this.getSkillOnShop().get(rand.nextInt(this.getSkillOnShop().size()));
        return skill;
    }
    public Skills RandomSkillOnOthers(){
        Skills skill;
        skill=this.getSkillOnOther().get(rand.nextInt(this.getSkillOnOther().size()));
        return skill;
    }
    public Skills RandomSkillOnPole(){
        Skills skill;
        skill=this.getSkillOnPole().get(rand.nextInt(this.getSkillOnPole().size()));
        return skill;
    }

    public Skills RandomSkillBleu(){
        Skills skill;
        skill=this.getBlueList().get(rand.nextInt(this.getBlueList().size()));
        return skill;
    }
    public Skills RandomSkillYellow(){
        Skills skill;
        skill=this.getYellowList().get(rand.nextInt(this.getYellowList().size()));
        return skill;
    }

    public Skills RandomSkillRed(){
        Skills skill;
        skill=this.getRedList().get(rand.nextInt(this.getRedList().size()));
        return skill;
    }
    public Skills RandomSkillGrey(){
        Skills skill;
        skill=this.getGreyList().get(rand.nextInt(this.getGreyList().size()));
        return skill;
    }

    public Skills RandomSkillGreen(){
        Skills skill;
        skill=this.getGreenList().get(rand.nextInt(this.getGreenList().size()));
        return skill;
    }

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
    public SkillColor getColors() {
        return Colors;
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
    public void setColors(SkillColor colors) {
        Colors = colors;
    }
//
}
