public class EventSurLieu extends Evenement{

    public EventSurLieu(String Name, int niveau) {
        super(Name, niveau, "N");
    }


    @Override
    public void applyEvent(Game game) {
        Lieu event = new Lieu(this.Name, true, this.getType());
        event.setEOP(this.EOP);
        event.setEOP(this.EOM);
        event.setEOP(this.EOT);
        event.setEOP(this.EOS);
        event.setEOP(this.EOA);
        event.setEOP(this.EOC);
        event.setEOP(this.EOPV);
        game.getListLieux().add(event);
    }
}
