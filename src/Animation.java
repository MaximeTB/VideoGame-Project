public class Animation extends Lieu{
    public Animation(String name, Boolean available, String type, int capMax, int EOPV) {
        super(name, available, type, capMax);
        this.Duree=7;
        this.EOPV=EOPV;
        this.available=false;
    }


}
