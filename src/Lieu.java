import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 */
public class Lieu {
    /**
     * @serialField
     */
    protected String name;
    protected String type;        //J pour les lieux du jour et N ceux de la nuit
    protected ArrayList<Eleve> ElevePresents = new ArrayList<Eleve>();
    protected int capMax;     //capacité max en élève. les lieux sans limite sont placé de base a 100.
    protected int Duree;
    protected Boolean available=true;

    protected int EOP=0; //effect on pop
    protected int EOM=0; //effect on money
    protected int EOT=0; //effect on tired
    protected int EOS=0; //effect on studies
    protected int EOA=0; //effect on admin
    protected int EOC=0; //effect on cohesion
    protected int EOPV=0; //effect on PV
    protected int isAMPH=0; //effet specifique a l'amphi

    private boolean isMeeting=false; //effet specifique aux reunions de poles

    private static int nbLieu =0;

    /**
     * @param name Nom du lieu
     * @param available Indique si le lieu est disponible ou non
     * @param type Indique si c'est un lieu de Journee "J" ou de Nuit "N"
     */
    public Lieu(String name, Boolean available, String type){
        this.name = name;
        this.available = available;
        this.type = type;
        this.capMax = 100;
        this.Duree = 100;
        nbLieu++;
    }

    public Lieu(String name, Boolean available, String type, int capMax){
        this(name, available, type);
        this.capMax=capMax;
    }

    //Getters

    /**
     *
     * @return
     */
    public String getname() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getNameElevePresents() {
        ArrayList<String> EleveDansLieu = new ArrayList<String>();
        for (Eleve i : ElevePresents){
             EleveDansLieu.add(i.getName());
        }
        return EleveDansLieu;
    }

    /**
     *
     * @return
     */
    public ArrayList<Eleve> getElevePresents() {
        return ElevePresents;
    }

    /**
     *
     * @return
     */
    public int getCapMax() {
        return capMax;
    }

    /**
     *
     * @return
     */
    public int getEOM() {
        return EOM;
    }

    /**
     *
     * @return
     */
    public int getEOS() {
        return EOS;
    }

    /**
     *
     * @return
     */
    public int getEOP() {
        return EOP;
    }

    /**
     *
     * @return
     */
    public int getEOT() {
        return EOT;
    }

    /**
     *
     * @return
     */
    public int getEOA() {
        return EOA;
    }

    /**
     *
     * @return
     */
    public int getEOC() {
        return EOC;
    }

    /**
     *
     * @return
     */
    public int getEOPV() {
        return EOPV;
    }

    /**
     *
     * @return
     */
    public int getNbLieu(){return nbLieu;}
    //Setters

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param capMax
     */
    public void setCapMax(int capMax) {
        this.capMax = capMax;
    }
    /**
     * @param EOP
     */
    public void setEOP(int EOP) {
        this.EOP = EOP;
    }

    /**
     * @param EOM
     */
    public void setEOM(int EOM) {
        this.EOM = EOM;
    }

    /**
     * @param EOT
     */
    public void setEOT(int EOT) {
        this.EOT = EOT;
    }

    /**
     * @param EOS
     */
    public void setEOS(int EOS) {
        this.EOS = EOS;
    }

    /**
     * @param EOA
     */
    public void setEOA(int EOA) {
        this.EOA = EOA;
    }

    /**
     * @param EOC
     */
    public void setEOC(int EOC) {
        this.EOC = EOC;
    }

    /**
     * @param EOPV
     */
    public void setEOPV(int EOPV) {
        this.EOPV = EOPV;
    }

    /**
     * @param isAMPH
     */
    public void setIsAMPH(int isAMPH){this.isAMPH = isAMPH;}

    /**
     * @param available
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /**
     *
     */
//Methodes
    public void ReductionDuree(){
        this.Duree--;
        if(Duree<=0){
            this.setAvailable(false);
            Duree=0;
        }
    }
    /**
     *
     */
    public void ChangeState(){this.available= !available;}

    /**
     *
     * @param e
     * @return
     */
    public int placeStudent(Eleve e){
        if(available){
            if(this.ElevePresents.size()<capMax){
                this.ElevePresents.add(e);
                e.setLocation(this, this.type);
                System.out.println(e.getName() + " placé dans " + this.name + " ("+ElevePresents.size()+"/" + capMax + ")");
            }
            else{
                System.out.println("Cet endroit est déja plein !");
                return -1;
            }
        }else{
            System.out.println("Lieu indisponible pour le moment");
            return -2;
        }
        return 0;
    }//renvois un int qui indique si l'élève a pu être placé ou pas

    /**
     *
     * @param list
     */
    public void ApplyLieuEffect(Player list){
        int Nb=ElevePresents.size();
        if (Nb!=0){         //bonus des lieux sur l'ensemble de la liste
            for(Eleve E:ElevePresents) {    //bonus des lieux sur les eleves presents
                /*for (int i = 0; i < E.getSkillsList().size(); i++){
                    if(E.getSkillsList().get(i).getName().equals(motCle)){
                        NbSpe++;
                    }
                }*/
                E.Etude(this.EOS - Math.min(E.getTired()*isAMPH,2)); //les bonus d'amphi sont réduit par la fatigue, capé a 2
                E.setTired(E.getTired()+this.EOT);
                list.setArgent(list.getArgent()+this.EOM);
                list.setPopularite(list.getPopularite()+this.EOP);
                list.setAdmin(list.getAdmin()+this.EOA);
                list.setPV(list.getPV()+this.EOPV);
                list.setCohesion(list.getCohesion()+this.EOC);
                for(SkillsOnLieu S : E.getSkillsOnLieuList()){
                    S.ApplySkillEffectOnLieu(E, list, this.getType());
                }
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "\nNom: " +name+"\n"
                + "Type: " + this.type +"\n"
                +"Disponible: "+this.available+"\n"
                +"Amphi: " + this.isAMPH+"\n"
                +"Capacité Maximum :" + this.capMax+"\n"
                +"Effet sur la population: "+this.EOP+"\n"
                +"Effet sur l'argent: "+this.EOM+"\n"
                +"Effet sur la fatigue: " +this.EOT+"\n"
                +"Effet sur les Etudes: "+ this.EOS+"\n"
                +"Effet sur l'Administration: "+ this.EOA+"\n"
                +"Effet sur la Cohésion: "+ this.EOC+"\n"
                +"Effet sur les Points de Victoire: "+this.EOPV+"\n";
    }
}

