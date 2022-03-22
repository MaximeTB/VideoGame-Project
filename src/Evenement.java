import java.sql.Array;
import java.util.ArrayList;

public abstract class Evenement{
    protected String Name; //Nom de l'événèment
    protected int niveau;
    protected ArrayList<Effect> Effects ; //Liste des effets sur les stats de cet évènement
    protected String Type; //vaut J ou N si c'est un lieu (nuit ou jour) et vaut G si c'est un effet global
    protected int EOP=0; //effect on pop
    protected int EOM=0; //effect on money
    protected int EOT=0; //effect on tired
    protected int EOS=0; //effect on studies
    protected int EOA=0; //effect on admin
    protected int EOC=0; //effect on cohesion
    protected int EOPV=0; //effect on PV



    public Evenement(String Name,int niveau,String Type){
        this.Name=Name;
        this.niveau=niveau;
        this.Type=Type;
        this.Effects=new ArrayList<Effect>();
    }

    public void addEffect(Effect effet){
        this.Effects.add(effet);
    }

//getter
    public String getName() {
        return Name;
    }
    public int getNiveau() {
        return niveau;
    }
    public ArrayList<Effect> getEffects() {
        return Effects;
    }
    public String getType() {
        return Type;
    }

   /* public void ApplyEvent(Game game){
        if(this.getType().equals("G")){
            //ajouter tout les EO stats de la liste ainsi que les lieux
            //modifier ou arreter l'effet après X tours
            //ajouter la gestion des condition ( a faire en dernier. au pires ces event sont pas nombreux donc on les enlèvera juste)
            //
        }
        else{
            Lieu event = new Lieu(this.Name, true, this.getType());
            event.setEOP(this.EOP);
            event.setEOP(this.EOM);
            event.setEOP(this.EOT);
            event.setEOP(this.EOS);
            event.setEOP(this.EOA);
            event.setEOP(this.EOC);
            event.setEOP(this.EOPV);

            //modifier les EO en fonction des mot-clé des élèves présents
            //désactiver le lieu après X tours
            game.getListLieux().add(event);
        }
    }*/
    public abstract void  applyEvent(Game game);



    @Override
    public String toString() {
        return  "\n {Name='" + Name + '\'' +
                ", niveau=" + niveau +
                ", Effects=" + Effects +
                ", Type='" + Type + '\'' +
                "}";
    }
}
