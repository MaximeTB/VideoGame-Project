import java.util.Random;

public class BureauAssoce extends Lieu{

    public BureauAssoce(String name, Boolean available, String type) {
        super(name, available, type);
        this.capMax=3;
        this.EOP=1;
    }

    public void GiveSkill(Eleve E, Player list){
        Random rand = new Random();
        if(rand.nextInt(100)>90){//environ 10% de chance de nouveau skill
            Skills S = E.AddRandomSkill();
            if(S.getClass()==SkillOnRecruit.class){
                ((SkillOnRecruit) S).OnRecruit(E,list);
            }
        }
    }//fonction à améliorer pour gagner des skills de couleur spécifique
    //a faire quand la fonction randomskill sera complété


    @Override
    public void ApplyLieuEffect(Player list){
        super.ApplyLieuEffect(list);
        for(Eleve E : this.getElevePresents()){
            this.GiveSkill(E,list);
        }
    }
}
