import java.util.List;

/**
 * Ce Type de Skill a un effet lors du recrutement de l'Eleve. Par exemple un Eleve Riche rapportera de l'argent a la Liste lors du recrutement.
 */
public class SkillOnRecruit extends Skills{
    /**
     * Effet sur l'argent.
     */
    protected int EOM; //effet sur l'argent de la liste
    /**
     * Effet sur la cohesion.
     */
    protected int EOC; //effet sur la cohesion de list
    /**
     * Effet sur les points d'Admin.
     */
    protected int EOA; //effet sur les points d'admin

    //pour l'instant ce sont les seuls modificateur que l'on utilise, mais on pourrait en rajouter pour toutes les stats
    //de la liste ou des élèves si besoin.

    public SkillOnRecruit(String color, String name,int EOM,int EOC,int EOA ) {
        super(color, name);
        this.EOM=EOM;
        this.EOC=EOC;
        this.EOA=EOA;
    }




    @Override
    /**
     * Methode a appliquer lors du recrutement, fais gagner a la liste toutes les stats du Skill.
     */
    public void OnRecruit(Eleve E, Player list){
        list.gainArgent(EOM);
        list.gainCohesion(EOC);
        list.gainAdmin(EOA);
    }

    @Override
    /**
     * N'a aucun effet pour ce type de Skill.
     */
    void ApplySkillEffect(Eleve E, Player list) {
    }

}
