import java.util.ArrayList;

public class SkillOnPole extends Skills{

    private ArrayList<Pole> PoleCible = new ArrayList<>(); //pole affecté par l'effet
    private String StatCible; //stat affecté
    private Integer ValueEffet; //valeur

    public SkillOnPole(String color, String name) {
        super(color, name);
    }

    public void ApplyEffectOnPole(Pole P, Player list){
        //tester que je suis dans le bon pole
        //vérifier le parametre a changer
        // appliquer le buff
    }
    public float ApplyEffectOnXP(Pole P, Player list){
        float multiplicateur=1;
        if(StatCible.equals("XP"))
        if(PoleCible.contains(P)){
            multiplicateur=ValueEffet;
        }
        return multiplicateur;
    }


        @Override
    void ApplySkillEffect(Eleve E, Player list) {

    }
}
