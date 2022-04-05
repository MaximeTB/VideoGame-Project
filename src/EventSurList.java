public class EventSurList extends Evenement{
    //types d'evenement dont l'effet affect directement la liste:
    //recruter, perdre des élèves, gagner de l'argent...

    public EventSurList(String Name, int niveau, String Type,int duree) {
        super(Name, niveau, Type,duree);
    }

    @Override
    public void applyEvent(Game game) {

    }
}
