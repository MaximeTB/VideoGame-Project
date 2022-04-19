import java.lang.reflect.Array;
import java.util.ArrayList;

public class SkillsOnLieu extends Skills {
    private ArrayList<Lieu> LieuCible = new ArrayList<>(); //liste des lieux (event inclus)
    private ArrayList<String> StatCible = new ArrayList<>();
    private ArrayList<Integer> ValueEffet = new ArrayList<>(); //valeur du buff


    public SkillsOnLieu(String color, String name,ArrayList<Lieu> LieuCible,ArrayList<String> StatCible,ArrayList<Integer> ValueEffet) {
        super(color,name);
        this.LieuCible=LieuCible;
        this.StatCible=StatCible;
        this.ValueEffet=ValueEffet;
    }

    public void ApplySkillEffectOnLieu(Eleve E, Player list, String type) {
        //Type pour Jour/Nuit
        int i;

        for(i=0; i<this.getLieuCible().size(); i++){

            if(this.getLieuCible().get(i).equals(E.getLocation(type))){

                String stat = this.getStatCible().get(i);//Stat affectée par l'effect

                if(stat.equals("Argent")){
                    list.gainArgent(ValueEffet.get(i)-E.getLocation(type).getEOM());
                }
                if(stat.equals("Popularite")){
                    list.gainPopularite(ValueEffet.get(i)-E.getLocation(type).getEOP());
                }
                if(stat.equals("Admin")){
                    list.gainAdmin(ValueEffet.get(i)-E.getLocation(type).getEOA());
                }
                if(stat.equals("Cohesion")){
                    list.gainCohesion(ValueEffet.get(i)-E.getLocation(type).getEOC());
                }
                if(stat.equals("PV")){
                    list.gainPV(ValueEffet.get(i)-E.getLocation(type).getEOPV());
                }
                if(stat.equals("Etude")){
                    E.Etude(ValueEffet.get(i)-E.getLocation(type).getEOS());
                }
                if(stat.equals("Fatigue")){
                    E.Fatigue(ValueEffet.get(i)-E.getLocation(type).getEOT());
                }
                if(stat.equals("Cout")){
                    E.Cout(ValueEffet.get(i)-E.getLocation(type).getEOC());
                }
            }
        }
    }


    @Override
    public void ApplySkillEffect(Eleve E, Player list) {
        System.out.println("mauvaise fonction");
    }

    public void OnRecruit(Eleve E, Player list){}

//Getter
    public ArrayList<Lieu> getLieuCible() {
        return LieuCible;
    }
    public ArrayList<String> getStatCible() {
        return StatCible;
    }
    public ArrayList<Integer> getValueEffet() {
        return ValueEffet;
    }
//
//Setter
    public void setLieuCible(ArrayList<Lieu> lieuCible) {
        LieuCible = lieuCible;
    }
    public void setStatCible(ArrayList<String> statCible) {
        StatCible = statCible;
    }
    public void setValueEffet(ArrayList<Integer> valueEffet) {
        ValueEffet = valueEffet;
    }
//
}
