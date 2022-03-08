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

        //initialisation temporaire des lieux
        //jour
        Lieu Assoce = new Lieu("activité associative", true, "D",3);
        Lieu Rue = new Lieu("La Rue", true, "D");
        Rue.setEOP(2);
        Lieu Amphi = new Lieu("Amphi", true, "D");
        Amphi.setEOS(2);Amphi.setIsAMPH(1);
        Lieu TP = new Lieu("Salle de TP", false, "D");
        TP.setEOS(1);
        Lieu Admin = new Lieu("Bureau de l'administration",true,"D",1);
        Admin.setEOA(1);
        Lieu GrassMat = new Lieu("Grasse matiné", true, "D");
        GrassMat.setEOT(-1);
        //nuit
        Lieu Soire = new Lieu("Soirée", true, "N");
        Soire.setEOT(1);Soire.setEOP(5);
        Lieu Argent1 = new Lieu("petit boulot", true, "N");
        Argent1.setEOM(10);Argent1.setEOT(1);
        Lieu Argent2 = new Lieu("petit boulot moins légal", false, "N");
        Argent2.setEOM(50);Argent2.setEOT(1);Argent1.setEOA(-1);
        Lieu Argent3 = new Lieu("vente de cookies", false, "N");
        Argent3.setEOM(25);Argent3.setEOT(1);
        Lieu Revision = new Lieu("Centre doc", true, "N");
        Revision.setEOS(1);Revision.setEOT(-1);
        Lieu Repos = new Lieu("Repos", true, "N");
        Repos.setEOT(-1);



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

