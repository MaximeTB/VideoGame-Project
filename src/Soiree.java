import java.util.Random;

public class Soiree extends Lieu{

    public Soiree(String name, Boolean available, String type) {
        super(name, available, type);
    }

    public void Hangover(){
        Random R = new Random();
        for(Eleve E : this.getElevePresents()){
            for(Skills S : E.getSkillsList()){
                if(S.getName().equals("Foie d'acier")){
                    return;
                }
            }
            if(R.nextInt(100)<10+10*E.getTired()){ //plus l'eleve est fatigue, plus la gueule de bois est probable
                E.setSkipTurn(true);
            }
        }
    }//les eleves en soiree ont une chance de passer leur prochain tour

    @Override
    public void ApplyLieuEffect(Player list){
        super.ApplyLieuEffect(list);
        for(Eleve E : this.getElevePresents()){
            this.Hangover();
        }
    }

}
