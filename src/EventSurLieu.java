/**
 * Ce type d'Evenement cree un Lieu pour un duree limitee.Par exemple l'Evenement Soiree cree un Lieu de Nuit Soiree.
 */
public class EventSurLieu extends Evenement{
    private int Duree;

    //type d'evenement qui modifie, créer ou désactive un lieu
    public EventSurLieu(String Name, int Tier,String Type, int duree) {
        super(Name, Tier, Type,duree);
    }


    @Override
    /**
     * L'application de cet Event a pour Effet de creer un Lieu avec les stats correspondantes aux stats de l'Event.
     */
    public void applyEvent(Game game) {
    }

}
