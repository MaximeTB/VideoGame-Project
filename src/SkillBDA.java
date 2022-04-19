import java.util.ArrayList;

public class SkillBDA extends SkillsOnLieu{
    public ArrayList<Integer> index;//list des index des lieux ciblé dans le super effectOnLieu
    private int ValueBonus; //modificateur par autres eleves
    public SkillBDA(String type, String color, String name, ArrayList<Lieu> LieuCible, ArrayList<String> StatCible, ArrayList<Integer> ValueEffet) {
        super(color, name,LieuCible,StatCible,ValueEffet);
    }


    public int CheckOther(Player list){
        int Other=0;
        for(Eleve E:list.getListeEleve())
            for (Skills S : E.getSkillsList()){
                if(S.equals(this)){
                    Other++;
                }
            }
        return Other;
    }

    public void OnRecruit(Eleve E, Player list){
    }

    @Override
    public void ApplySkillEffectOnLieu(Eleve E, Player list, String type) {
        int nbOther = CheckOther(list);
        for (int i : index) {
            super.getValueEffet().set(i, ValueBonus * nbOther);
        }
        super.ApplySkillEffectOnLieu(E, list, type);
    }

}
