import java.util.ArrayList;

public class SkillOnOthers extends SkillsOnLieu{
//Ces skills ont un effets sur les autre éléves présent dans le même lieu que celui possédant le skills
//Exemple un élève pédagogue permet aux autre élève présent dans l'Amphi d'apprendre plus



    public SkillOnOthers(String color, String name, ArrayList<Lieu> LieuCible, ArrayList<String> StatCible, ArrayList<Integer> ValueEffet) {
        super(color, name,LieuCible,StatCible,ValueEffet);
    }

    @Override
    public void ApplySkillEffectOnLieu(Eleve E,Player list,String type){
        Lieu LieuDeEleve= E.getLocation(type) ;
        Boolean BonLieu=false;
        String statCible;
        int valueEffect;


        for (int i=0;i< this.getLieuCible().size();i++){
            if(LieuDeEleve.equals(this.getLieuCible().get(i))){  //On vérifie que l'élève est dans un des lieux/le lieu affecté par le skills
                statCible = this.getStatCible().get(i);
                valueEffect= this.getValueEffet().get(i);
                for(Eleve e : LieuDeEleve.getElevePresents()){
                    if(statCible.equals("Etude")){
                        e.setStudies(e.getStudies()+valueEffect);
                    }
                    else if(statCible.equals("Fatigue")){
                        e.setTired(e.getTired()+valueEffect);
                    }
                    else if(statCible.equals("Cout")){
                        e.setCost(e.getCost()+valueEffect);
                    }
                }
            }
        }
    }
}
