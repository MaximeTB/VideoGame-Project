import java.util.List;

public class SkillOnRecruit extends Skills{
    protected int EOM; //effet sur l'argent de la liste
    protected int EOC; //effet sur la cohesion de list
    protected int EOA; //effet sur les points d'admin

    //pour l'instant ce sont les seuls modificateur que l'on utilise, mais on pourrait en rajouter pour toutes les stats
    //de la liste ou des élèves si besoin.

    public SkillOnRecruit(String color, String name) {
        super(color, name);
    }


    public void OnRecruit(Eleve E, Player list){//à appeler lorsque l'élève est recruté dans la methode player.recrute()
        list.gainArgent(EOM);
        list.gainCohesion(EOC);
        list.gainAdmin(EOA);
    }

    @Override
    void ApplySkillEffect(Eleve E, Player list) {

    }

}
