public class Achetable {
    public String name;
    public Integer number;
    public Integer price;
    public String type; //animation, instant

    //serts pour les anim
    public String JN; //vaut J ou N pour jour/nuit si c'est une animation
    public Integer capMax;
    public Integer EOPV;

    //serts pour les instants


    public Integer getAttribute(){
        return this.number;
    }


    public void Achat(Game G){
        if(type.equals("animation")){
            G.getListLieux().add(new Animation(this.name, false, JN, capMax, EOPV));
        }
    }
}
