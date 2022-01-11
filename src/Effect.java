public class Effect { //l'objet "Effet"
    private final String Stat; //Stat affectée par cet effet
    private final int Value; //modificateur (positif ou négatif) appliqué à la stat


    public Effect(String stat, int value) {
        Stat = stat;
        Value = value;
    }

    public String getStat() {
        return Stat;
    }

    public int getValue() {
        return Value;
    }
}
