import java.lang.reflect.Array;
import java.util.ArrayList;

public class SkillsOnLieu extends Skills {
    private ArrayList<Lieu> LieuCible = new ArrayList<>(); //liste des lieux (event inclus)
    private ArrayList<String> StatCible = new ArrayList<>();
    private ArrayList<Integer> ValueEffet = new ArrayList<>(); //valeur du buff


    public SkillsOnLieu(String type,String color, String name,ArrayList<Lieu> LieuCible,ArrayList<String> StatCible,ArrayList<Integer> ValueEffet) {
        super(type,color,name);
        this.LieuCible=LieuCible;
        this.StatCible=StatCible;
        this.ValueEffet=ValueEffet;
    }

    public void ApplySkillEffectOnLieu(Eleve E, Player list, String type) {
        int i;
        for(i=0; i<LieuCible.size(); i++){
            if(LieuCible.get(i).equals(E.getLocation(type))){
                if(StatCible.get(i).equals("argent")){
                    list.gainArgent(ValueEffet.get(i)-E.getLocation(type).getEOM());
                }
                if(StatCible.get(i).equals("popularite")){
                    list.gainPopularite(ValueEffet.get(i)-E.getLocation(type).getEOP());
                }
                if(StatCible.get(i).equals("admin")){
                    list.gainAdmin(ValueEffet.get(i)-E.getLocation(type).getEOA());
                }
                if(StatCible.get(i).equals("cohesion")){
                    list.gainCohesion(ValueEffet.get(i)-E.getLocation(type).getEOC());
                }
                if(StatCible.get(i).equals("PV")){
                    list.gainPV(ValueEffet.get(i)-E.getLocation(type).getEOPV());
                }
                if(StatCible.get(i).equals("etude")){
                    E.Etude(ValueEffet.get(i)-E.getLocation(type).getEOS());
                }
                if(StatCible.get(i).equals("fatigue")){
                    E.Fatigue(ValueEffet.get(i)-E.getLocation(type).getEOT());
                }
                if(StatCible.get(i).equals("cout")){
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
