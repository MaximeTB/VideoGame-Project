public class EventSurLieu extends Evenement{
    private int Duree;

    //type d'evenement qui modifie, créer ou désactive un lieu
    public EventSurLieu(String Name, int niveau, int duree) {
        super(Name, niveau, "N",duree);
    }


    @Override
    public void applyEvent(Game game) {

    }

}
