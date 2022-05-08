import java.util.ArrayList;

/**
 * Ces skills ont un effet sur les autre eleves present dans le meme lieu que celui possedant le skills
 * Exemple un eleve pedagogue permet aux autre eleve present dans l'Amphi de gagner plus de points d'Etude.
 */
public class SkillOnOthers extends SkillsOnLieu{

    /**
     * 
     * @param color Categorie du Skill
     * @param name Nom du Skill
     * @param LieuCible Liste des Lieux dans lequel s'applique le Skill
     * @param StatCible Liste des Stats concernees par le Skill
     * @param ValueEffet Valeurs numeriques (positive ou negative) des modifications de gain apportees par le Skill
     * Les trois listes sont "synchrones", le premier element de StatCible est la Stat affecte pour le premier element de LieuCible, et le premier element de ValueCible correspond au premier Lieu et la premier Stat etc...
     */
    public SkillOnOthers(String color, String name, ArrayList<Lieu> LieuCible, ArrayList<String> StatCible, ArrayList<Integer> ValueEffet) {
        super(color, name,LieuCible,StatCible,ValueEffet);
    }

    /**
     *Verifie si l'Eleve E est present dans le Lieu ou un des Lieux ciblees par le Skill, puis applique l'Effet du Skill sur tous les Eleves presents dans le Lieu
     * @param E Eleve possedant le Skill
     * @param list Liste du Joueur
     * @param type Moment de la journee pour lequel on cherche a appliquer le Skill (permet de ne pas appliquer un Skill deux fois sur une meme journee)
     */
    @Override
    public void ApplySkillEffectOnLieu(Eleve E,Player list,String type){
        Lieu LieuDeEleve= E.getLocation(type) ;
        Boolean BonLieu=false;
        String statCible;
        int valueEffect;
        for (int i=0;i< this.getLieuCible().size();i++){
            if(LieuDeEleve.equals(this.getLieuCible().get(i))){  //On verifie que l'eleve est dans un des lieux/le lieu affecte par le skills
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
