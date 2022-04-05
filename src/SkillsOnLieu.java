import java.lang.reflect.Array;
import java.util.ArrayList;

public class SkillsOnLieu extends Skills {
    ArrayList<Lieu> LieuCible = new ArrayList<>(); //liste des lieux (event inclus)
    ArrayList<String> StatCible = new ArrayList<>();
    ArrayList<Integer> ValueEffet = new ArrayList<>(); //valeur du buff


    public SkillsOnLieu(String type,String color, String name) {
        super(type,color,name);

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
}
