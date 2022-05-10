/**
 * Ce type de Skill a un effet sur l'achat des item du Shop (prix plus bas etc...)
 */
public class SkillOnShop extends Skills{

    public SkillOnShop( String color, String name) {
        super( color, name);
    }

    @Override
    void ApplySkillEffect(Eleve E, Player list) {
    }

    @Override
    void OnRecruit(Eleve E, Player list) {

    }


}
