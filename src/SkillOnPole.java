import java.util.ArrayList;

public class SkillOnPole extends Skills{

    private ArrayList<Pole> PoleCible = new ArrayList<>(); //pole affecté par l'effet
    private ArrayList<String> StatCible = new ArrayList<>(); //stat affecté
    private ArrayList<Integer> ValueEffet = new ArrayList<>(); //valeur

    public SkillOnPole(String type, String color, String name) {
        super(type, color, name);
    }

    public void ApplyEffectOnPole(Pole P, Player list){

    }

    @Override
    void ApplySkillEffect(Eleve E, Player list) {

    }
}
