import java.lang.reflect.Array;
import java.util.ArrayList;

public class Lieu {
    private String name;
    private String type;        //J pour les lieux du jour et N ceux de la nuit
    private ArrayList<Eleve> ElevePresents = new ArrayList<Eleve>();
    private int capMax;     //capacité max en élève. les lieux sans limite sont placé de base a 100.
    private Boolean available=true;

    private String motCle="rien"; //mot cle qui applique un modificateur à ce lieu
    private ArrayList<String> modifType; //donne la grandeure affecte (pop, argent, etude...)
    private ArrayList<Integer> modifValue; //donne la valeur du bonus/malus confere par le mot cle correspondant

    private int EOP=0; //effect on pop
    private int EOM=0; //effect on money
    private int EOT=0; //effect on tired
    private int EOS=0; //effect on studies
    private int EOA=0; //effect on admin
    private int EOC=0; //effect on cohesion
    private int EOPV=0; //effect on PV
    private int isAMPH=0; //effet specifique a l'amphi

    public Lieu(String name, Boolean available, String type){
        this.name = name;
        this.available = available;
        this.type = type;
        this.capMax = 100;
    }

    public Lieu(String name, Boolean available, String type, int capMax){
        this(name, available, type);
        this.capMax=capMax;
    }

    //Getters


    public String getname() {
        return name;
    }
    public String getType() {
        return type;
    }
    public ArrayList<String> getNameElevePresents() {
        ArrayList<String> EleveDansLieu = new ArrayList<String>();
        for (Eleve i : ElevePresents){
             EleveDansLieu.add(i.getName());
        }
        return EleveDansLieu;
    }
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
    //Setters

    public void setName(String name) {
        this.name = name;
    }
    public void setCapMax(int capMax) {
        this.capMax = capMax;
    }
    public void setEOP(int EOP) {
        this.EOP = EOP;
    }
    public void setEOM(int EOM) {
        this.EOM = EOM;
    }
    public void setEOT(int EOT) {
        this.EOT = EOT;
    }
    public void setEOS(int EOS) {
        this.EOS = EOS;
    }
    public void setEOA(int EOA) {
        this.EOA = EOA;
    }
    public void setEOC(int EOC) {
        this.EOC = EOC;
    }
    public void setEOPV(int EOPV) {
        this.EOPV = EOPV;
    }
    public void setIsAMPH(int isAMPH){this.isAMPH = isAMPH;}
    public void setAvailable(Boolean available) {
        this.available = available;
    }

//Methodes

    public void ChangeState(){this.available= !available;}

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
    }

    public void ApplyLieuEffect(Player list){
        int Nb=ElevePresents.size();
        if (Nb!=0){         //bonus des lieux sur l'ensemble de la liste

            for(Eleve E:ElevePresents) {    //bonus individuels des lieux sur les élèves
                for (int i = 0; i < E.getSkillsList().size(); i++){
                    if(E.getSkillsList().get(i).getName().equals(motCle)){

                    }
                }
                    E.setStudies(E.getStudies()+(this.EOS - Math.min(E.getTired()*isAMPH,2))); //les bonus d'amphi sont réduit par la fatigue, capé a 2
                    E.setTired(E.getTired()+this.EOT);
            }


            list.setArgent(list.getArgent()+this.EOM*Nb);
            list.setPopularite(list.getPopularite()+this.EOP*Nb);
            list.setAdmin(list.getAdmin()+this.EOA*Nb);
            list.setPV(list.getPV()+this.EOPV*Nb);
            list.setCohesion(list.getCohesion()+this.EOC*Nb);


        }
    }

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

