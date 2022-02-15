import java.util.Scanner;

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
        boolean FinTour= false,SortieMenu=false;
        Scanner clavier = new Scanner(System.in);
        int Entrée;
        System.out.println("Tour " + this.getNbTour() + ":" +"\nArgent :"+this.getPlayer().getArgent().toString()
                + "\nAdmin :"+this.getPlayer().getAdmin().toString()
                + "\nPopularité :"+this.getPlayer().getPopularite().toString()
                +"\nCohésion :"+this.getPlayer().getCohesion().toString()
                +"\nPV :"+this.getPlayer().getPV().toString());
        System.out.println("Début du Tours :");
        while(!FinTour){
            System.out.println("Quel Menu veut-tu ouvrir ? \n1.Jour 2.Nuit 3.QG 4.Fin du Tour");
            Entrée=clavier.nextInt();
            if(Entrée==1){
                while(!SortieMenu){
                    System.out.println("Une journée sans gueule de bois est une journée à rentabiliser , que veut-tu faire ?\n1.Option 1  2.Option 2  3.Revenir au Menu Principal");
                    Entrée=clavier.nextInt();
                    if(Entrée==3){
                        SortieMenu=true;
                    }
                }
            }else if (Entrée==2){
                while(!SortieMenu){
                    System.out.println("Si t'es vivant c'est qu't'es pas encore mort , que veut-tu faire ?\n1.Option 1  2.Option 2  3.Revenir au Menu Principal");
                    Entrée=clavier.nextInt();
                    if(Entrée==3){
                        SortieMenu=true;
                    }
                }
            }else if(Entrée==3){
                while(!SortieMenu){
                    System.out.println("Bienvenue au QG chacal , que veut-tu faire ?\n1.Option 1  2.Option 2  3.Revenir au Menu Principal");
                    Entrée=clavier.nextInt();
                    if(Entrée==3){
                        SortieMenu=true;
                    }
                }
            } else if(Entrée==4){
                FinTour=true;
            }
            SortieMenu=false;

        }
        clavier.close();
        System.out.println("Fin du tour");
    }



    public void NewTour(){
        if (this.Phase=="J"){
            this.Phase="N";}
        else if (this.Phase=="N"){
            this.NbTour+=1;
            this.Phase="J";
        }
    }
//Getter
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
//

    public boolean FinTour(String entrée){
        if(entrée.equals("fin")){
            return true;
        }else{
            return false;
        }
    }

}

