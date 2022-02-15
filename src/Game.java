public class Game {
    private int NbTour;
    private PoolOfEvent pool, DayPool, NightPool;
    private String Phase;
    private Player player;

    public Game() {
        //Initialisation du Jeu
        this.player = new Player("BDMichelle","Michelle");
        System.out.println("Liste : " + player.getName() +"\n"+"Président/Présidente : "+ this.getPlayer().getListeEleve().get(0).getName());
        this.Phase="J";
        this.NbTour=0;


        //Listes des Events
        PoolOfEvent pool = new PoolOfEvent("./data/ListeEvent.csv");

        PoolOfEvent DayEvent= new PoolOfEvent();
        for (Evenement e : pool.getEventList()){
            if (e.getPeriode().equals("J")) {
                DayEvent.add(e);
            }
        }

        PoolOfEvent NightEvent= new PoolOfEvent();
        for (Evenement e : pool.getEventList()){
            if (e.getPeriode().equals("N")) {
                NightEvent.add(e);
            }
        }


    }

    public void Tour(){
        System.out.println("Début du Tours : \nArgent :"+this.getPlayer().getArgent().toString()
                + "\nAdmin :"+this.getPlayer().getAdmin().toString()
                + "\nPopularité :"+this.getPlayer().getPopularite().toString()
                +"\nCohésion :"+this.getPlayer().getCohesion().toString()
                +"\nPV :"+this.getPlayer().getPV().toString());
    }



    public void NewTour(){
        if (this.Phase=="J"){
            this.Phase="N";}
        else if (this.Phase=="N"){
            this.NbTour+=1;
            this.Phase="J";
        }
    }

    public int getNbTour() {
        return NbTour;
    }

    public PoolOfEvent getPool() {
        return pool;
    }

    public PoolOfEvent getDayPool() {
        return DayPool;
    }

    public PoolOfEvent getNightPool() {
        return NightPool;
    }

    public String getPhase() {
        return Phase;
    }

    public Player getPlayer() {
        return player;
    }
}

