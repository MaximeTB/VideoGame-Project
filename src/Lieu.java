import java.util.ArrayList;

public class Lieu {
    private String name;
    private String type;        //J pour les lieux du jour et N ceux de la nuit
    private ArrayList<Eleve> ElevePresents;
    private int capMax;     //capacité max en élève. les lieux sans limite sont placé de base a 100.
    private String effect;
    private Boolean available=true;
    private int EOP; //effect on pop
    private int EOM; //effect on money
    private int EOT; //effect on tired
    private int EOS; //effect on studies
    private int EOA; //effect on admin
    private int EOC; //effect on cohesion
    private int EOPV; //effect on PV
    private int isAMPH; //effet specifique a l'amphi

    public Lieu(String name, Boolean available, String type){
        this.name = name;
        this.available = available;
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


    //Methodes

    public void ChangeState(){}
    public void desactivate(){
        this.available=false;
    }
    public void activate(){
        this.available=true;
    }

    public void placeStudent(Eleve e){
        if(available){
            if(this.ElevePresents.size()<capMax){
                this.ElevePresents.add(e);
                e.setLocation(this);
                System.out.println(e.getName() + " placé dans " + this.name + " (0/" + (capMax-ElevePresents.size()) + ")");
            }
            else{
                System.out.println("Cet endroit est déja plein !");
            }
        }else{
            System.out.println("Placement impossible pour le moment");
        }
    }

    public void changeStat(Player list){
        int Nb=ElevePresents.size();
        if (Nb!=0){
            list.setArgent(list.getArgent()+this.EOM*Nb);
            list.setPopularite(list.getPopularite()+this.EOP*Nb);
            list.setAdmin(list.getAdmin()+this.EOA*Nb);
            list.setPV(list.getPV()+this.EOPV*Nb);
            list.setCohesion(list.getCohesion()+this.EOC*Nb);

            for(Eleve E:ElevePresents){
                E.setStudies(E.getStudies()+(this.EOS - Math.min(E.getTired()*isAMPH,2)));
                E.setTired(E.getTired()+this.EOT);
            }
        }
    }



}

