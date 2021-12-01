public class Game {
    private int NbTour;
    private String Phase;

    public Game() {
        this.NbTour=0;
        this.Phase = "Jour";
    }

    public void NewTour(){
        if (this.Phase=="Jour"){
            this.Phase="Nuit";}
        else if (this.Phase=="Nuit"){
            this.NbTour+=1;
            this.Phase="JOur";
        }
    }



}
