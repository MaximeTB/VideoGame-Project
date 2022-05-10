import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Durant la partie le Joueur aura la possibilite de placer chaque Eleve dans un Lieu la nuit et dans un Lieu le jour.
 * Passer le jour/la nuit dans ce Lieu aura un impact sur l'Eleve et sur les statistiques de la Liste (le Joueur).
 * Par Exemple : Lors du jour 3 le Joueur choisis de placer Jean-Kevin dans l'Amphi ,dans laquelle il gagnera 1 point d'Etude,
 * et choisis de le placer dans le Lieu Soiree la nuit, ce qui fera gagner 5 Points de Popularite et 5 Points de Victoire a la Liste, et 1 Point de Fatigue a Jean-Kevin.
 */

public class Lieu {
    /**
     *Nom du Lieu
     */
    protected String name;
    /**
     * Type du Lieu : "J" pour Lieu de Jour, "N" pour lieu de Nuit
     */
    protected String type;
    /**
     * Liste des Eleves presents dans ce lieu
     */
    protected ArrayList<Eleve> ElevePresents = new ArrayList<Eleve>();
    /**
     * Nombre maximum d'Eleve que peut contenir ce Lieu. Fixe par defaut a 99.
     */
    protected int capMax;     //capacite max en elève. les lieux sans limite sont place de base a 100.
    /**
     * Pour les lieux temporaires lies a des Event, defini le temps  qu'il reste avant que l'Event soit fini et donc que le lieu ne soit plus disponible.
     */
    protected int Duree;
    /**
     * Defini si le lieu est disponible ou non
     */
    protected Boolean available=true;
    /**
     * Effet sur les Points de Popularite de la liste
     */
    protected int EOP=0;
    /**
     * Effet sur la Monnaie de la liste
     */
    protected int EOM=0;
    /**
     * Effet sur la Fatigue des Eleves presents dans le Lieu
     */
    protected int EOT=0;
    /**
     * Effet sur le niveau d'Etude des Eleves presents dans le Lieu
     */
    protected int EOS=0;
    /**
     * Effet sur les points d'Administration de la Liste
     */
    protected int EOA=0;
    /**
     * Effet sur les points de cohesion de la Liste
     */
    protected int EOC=0;
    /**
     * Effet sur les Points de Victoire de la Liste
     */
    protected int EOPV=0; //effect on PV
    /**
     * Indique si le Lieu est l'Amphi ou non (facilite l'utilisation d'une methode d'une autre classe)
     */
    protected int isAMPH=0;
    /**
     * Indique si le Lieu est une reunion de Pole ou non (facilite l'utilisation d'une methode d'une autre classe)
     */
    private boolean isMeeting=false;

    private static int nbLieu =0;

    /**
     * Constructeur pour un Lieu sans capacite maximum (fixee alors par defaut a 99)
     * @param name Nom du lieu
     * @param available Disponibilite du Lieu
     * @param type Type du Lieu : "J" Jour, "N" Nuit
     */
    public Lieu(String name, Boolean available, String type){
        this.name = name;
        this.available = available;
        this.type = type;
        this.capMax = 100;
        this.Duree = 100;
        nbLieu++;
    }

    /**
     * Constructeur pour un Lieu avec une capacite maximale definie
     * @param name Nom du lieu
     * @param available Disponibilite du Lieu
     * @param type Type du Lieu : "J" Jour, "N" Nuit
     * @param capMax Capacite maximum du lieu
     */
    public Lieu(String name, Boolean available, String type, int capMax){
        this(name, available, type);
        this.capMax=capMax;
    }

    //Getters


    /**
     * @return Nom du Lieu
     */
    public String getname() {
        return name;
    }
    /**
     * @return Type du lieu : "J" Jour, "N" Nuit
     */
    public String getType() {
        return type;
    }

    /**
     * @return Liste des noms des Eleves presents dans le Lieu
     */
    public ArrayList<String> getNameElevePresents() {
        ArrayList<String> EleveDansLieu = new ArrayList<String>();
        for (Eleve i : ElevePresents){
             EleveDansLieu.add(i.getName());
        }
        return EleveDansLieu;
    }

    /**
     * @return Liste des Eleves present dans le Lieu
     */
    public ArrayList<Eleve> getElevePresents() {
        return ElevePresents;
    }


    public int getCapMax() {
        return capMax;
    }
    public int getEOM() {
        return EOM;
    }
    public int getEOS() {
        return EOS;
    }
    public int getEOP() {
        return EOP;
    }
    public int getEOT() {
        return EOT;
    }
    public int getEOA() {
        return EOA;
    }
    public int getEOC() {
        return EOC;
    }
    public int getEOPV() {
        return EOPV;
    }
    public int getNbLieu(){return nbLieu;}


    //Setters


    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
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


//Methodes

    /**
     * Reduit la Duree d'un lieu (pour les lieu lies a un Event) de 1 et desactive le lieu si cette derniere arrive a 0  (methode utilisee a chaque fin de tour)
     */
    public void ReductionDuree(){
        this.Duree--;
        if(Duree<=0){
            this.setAvailable(false);
            Duree=0;
        }
    }

    /**
     * Change la disponibilite du Lieu (Si disponible -> indisponible , Si indisponible -> disponible)
     */
    public void ChangeState(){this.available= !available;}

    /**
     *Verifie si il est possible de placer un Eleve dans le Lieu et le fait si c'est le cas.
     * @param e Eleve que l'on souhaite placer dans le lieu.
     * @return 0 si l'Eleve a bien ete place, sinon -1 si le Lieu est plein, -2 si le Lieu est indisponible.
     */
    public int placeStudent(Eleve e){
        if(available){
            if(this.ElevePresents.size()<capMax){
                this.ElevePresents.add(e);
                e.setLocation(this, this.type);
                System.out.println(e.getName() + " place dans " + this.name + " ("+ElevePresents.size()+"/" + capMax + ")");
            }
            else{
                System.out.println("Cet endroit est deja plein !");
                return -1;
            }
        }else{
            System.out.println("Lieu indisponible pour le moment");
            return -2;
        }
        return 0;
    }//renvois un int qui indique si l'elève a pu être place ou pas


    /**
     * En fin de tour applique a chaque Eleve present dans le Lieu et a la Liste (Player) le ou les effets du Lieu
     * @param list L'entite Player (permet de modifier ses attribut Argent, Popularite etc...)
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
                E.Etude(this.EOS - Math.min(E.getTired()*isAMPH,2)); //les bonus d'amphi sont reduit par la fatigue, cape a 2
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
                +"Capacite Maximum :" + this.capMax+"\n"
                +"Effet sur la population: "+this.EOP+"\n"
                +"Effet sur l'argent: "+this.EOM+"\n"
                +"Effet sur la fatigue: " +this.EOT+"\n"
                +"Effet sur les Etudes: "+ this.EOS+"\n"
                +"Effet sur l'Administration: "+ this.EOA+"\n"
                +"Effet sur la Cohesion: "+ this.EOC+"\n"
                +"Effet sur les Points de Victoire: "+this.EOPV+"\n";
    }
}

