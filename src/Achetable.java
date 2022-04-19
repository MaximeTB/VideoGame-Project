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

//getter
    public Integer getAttribute(){
        return this.number;
    }
    public String getName() {
        return name;
    }
    public Integer getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }
    public String getJN() {
        return JN;
    }
    public Integer getEOPV() {
        return EOPV;
    }
    public Integer getCapMax() {
        return capMax;
    }

//setter

    public void setNumber(Integer number) {
        this.number = number;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setJN(String JN) {
        this.JN = JN;
    }
    public void setCapMax(Integer capMax) {
        this.capMax = capMax;
    }
    public void setEOPV(Integer EOPV) {
        this.EOPV = EOPV;
    }



    public void Achat(Game G){
        if(type.equals("animation")){
            G.getListLieux().add(new Animation(this.name, false, JN, capMax, EOPV));
        }
    }
}
