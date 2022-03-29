import java.lang.reflect.Array;
import java.util.ArrayList;

public class SkillsSurLieu extends Skills{
    ArrayList<Lieu> LieuCible = new ArrayList<>(); //liste des lieux (event inclus)
    ArrayList<String> StatCible = new ArrayList<>();
    ArrayList<Integer> ValueEffet = new ArrayList<>(); //valeur du buff


    public SkillsSurLieu(int indexC, int indexN) {
        super(indexC, indexN);
    }

    @Override
    void ApplySkillEffect(Eleve E, Player list) {
        int i;
        for(i=0; i<LieuCible.size(); i++){
            if(LieuCible.get(i).equals(E.getLocationJ())){
                if(StatCible.get(i).equals("argent")){
                    list.setArgent(list.getArgent()+ValueEffet.get(i)-E.getLocationJ().getEOM());
                }
                if(StatCible.get(i).equals("popularite")){
                    list.setPopularite(list.getPopularite()+ValueEffet.get(i)-E.getLocationJ().getEOP());
                }
                if(StatCible.get(i).equals("admin")){
                    list.setAdmin(list.getAdmin()+ValueEffet.get(i)-E.getLocationJ().getEOA());
                }
                if(StatCible.get(i).equals("cohesion")){
                    list.setCohesion(list.getCohesion()+ValueEffet.get(i)-E.getLocationJ().getEOC());
                }
                if(StatCible.get(i).equals("PV")){
                    list.setPV(list.getPV()+ValueEffet.get(i)-E.getLocationJ().getEOPV());
                }
                if(StatCible.get(i).equals("etude")){
                    E.setStudies(E.getStudies()+ValueEffet.get(i)-E.getLocationJ().getEOS());
                }
                if(StatCible.get(i).equals("fatigue")){
                    E.setTired(E.getTired()+ValueEffet.get(i)-E.getLocationJ().getEOT());
                }
                if(StatCible.get(i).equals("cout")){
                    E.setCost(E.getCost()+ValueEffet.get(i)-E.getLocationJ().getEOC());
                }
            }
        }
    }
}
