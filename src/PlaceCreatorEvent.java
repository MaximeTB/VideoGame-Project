public class PlaceCreatorEvent extends Evenement {
    protected int EOP=0; //effect on pop
    protected int EOM=0; //effect on money
    protected int EOT=0; //effect on tired
    protected int EOS=0; //effect on studies
    protected int EOA=0; //effect on admin
    protected int EOC=0; //effect on cohesion
    protected int EOPV=0; //effect on PV
    protected String time; //Event Jour ou Event Nuit
    protected int capMax=0 ; //Capacité max du lieu à créer, si elle est à 0 contructeur de lieu sans capmax (par défaut à 99)
    public PlaceCreatorEvent(String name,int niveau,String type,String time ,int EOP,int EOM,int EOT,int EOS,int EOA,int EOC,int EOPV){
        super(name,niveau,type);
        this.time=time;
        this.EOP=EOP;this.EOM=EOM;this.EOT=EOT;this.EOS=EOS;this.EOA=EOA;this.EOC=EOC;this.EOPV=EOPV;
    }
    public PlaceCreatorEvent(String name,int niveau,String type,String time ,int capMax,int EOP,int EOM,int EOT,int EOS,int EOA,int EOC,int EOPV){
        this(name,niveau,type,time,EOP,EOM,EOT,EOS,EOA,EOC,EOPV);
        this.capMax=capMax;
    }

    @Override
    public void applyEvent(Game game){
        Lieu lieu;
        if (this.capMax==0){
            lieu=new Lieu(this.getName(),true,this.getType());
        }else{
            lieu = new Lieu(this.getName(),true,this.getType(),this.capMax);
        }
        lieu.setEOP(this.EOP);lieu.setEOM(this.EOM);lieu.setEOT(this.EOT);lieu.setEOS(this.EOS);
        lieu.setEOA(this.EOA);lieu.setEOC(this.EOC);lieu.setEOPV(this.EOPV);
        game.getListLieux().add(lieu);
    }
}
