import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int NbTour;
    private PoolOfEvent pool, DayPool, NightPool;
    private Player player;
    private QGconsole QG;

    public Game() {
        //Initialisation du Jeu
        QG = new QGconsole();
        this.player = new Player("BDMichelle","Michelle");
        //test console on fout de l'argent tour 1
        player.setArgent(100);
        System.out.println("Liste : " + player.getName() +"\n"+"Président/Présidente : "+ this.getPlayer().getListeEleve().get(0).getName());
        this.NbTour=1;

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

    public void Tour(Scanner clavier){
        boolean FinTour= false,SortieMenu=false;
        ArrayList<Eleve> DayNonAffectedList = new ArrayList<Eleve>();
        ArrayList<Eleve> NightNonAffectedList = new ArrayList<Eleve>();
        int Entrée;
        System.out.println("Tour " + this.getNbTour() + ":" +"\nArgent :"+this.getPlayer().getArgent().toString()
                + "\nAdmin :"+this.getPlayer().getAdmin().toString()
                + "\nPopularité :"+this.getPlayer().getPopularite().toString()
                +"\nCohésion :"+this.getPlayer().getCohesion().toString()
                +"\nPV :"+this.getPlayer().getPV().toString());
        System.out.println("Début du Tour :");

        DayNonAffectedList=this.getPlayer().getListeEleve();
        while(!FinTour){
            System.out.println("Quel Menu veux-tu ouvrir ? \n1.Jour 2.Nuit 3.QG 4.Fin du Tour");
            Entrée=clavier.nextInt();
            if(Entrée==1){
                this.MenuJour(DayNonAffectedList,clavier);
                //while(!SortieMenu){
                    //System.out.println("Une journée sans gueule de bois est une journée à rentabiliser , choisis un Elève :\n1.Option 1  2.Option 2  3.Revenir au Menu Principal");
                    //Entrée=clavier.nextInt();
                    //if(Entrée==3){
                        //SortieMenu=true;
                    //}
                //}
            }else if (Entrée==2){
                while(!SortieMenu){
                    System.out.println("Si t'es vivant c'est qu't'es pas encore mort , que veux-tu faire ?\n1.Option 1  2.Option 2  3.Revenir au Menu Principal");
                    Entrée=clavier.nextInt();
                    if(Entrée==3){
                        SortieMenu=true;
                    }
                }
            }else if(Entrée==3){
                while(!SortieMenu){
                    System.out.println("Bienvenue au QG chacal , que veux-tu faire ?\n1.Liste 2.Magasin  3. Inventaire 4.Revenir au Menu Principal");
                    Entrée=clavier.nextInt();
                    if(Entrée==4){
                        SortieMenu=true;
                    }else if(Entrée == 3){ // inventaire
                        while(true) {
                            QG.inventaire();
                            Entrée = clavier.nextInt();
                            if (Entrée == 1) break;
                        }
                    }else if(Entrée == 2){ //shop
                        while(true) {
                            QG.shop();
                            System.out.println("Argent disponible : " + player.getArgent());
                            Entrée = clavier.nextInt();
                            QG.shopAction(Entrée, player);
                            if (Entrée == 7) break;
                        }
                    }else if (Entrée == 1){ // liste
                        QG.liste();
                    }
                }
            } else if(Entrée==4){
                FinTour=true;
            }
            SortieMenu=false;

        }

        this.NewTour();
        System.out.println("Fin du Tour \n\n");
    }


    public void NewTour(){
        this.NbTour+=1;
        }

//Gestion des Menu

    public void MenuJour(ArrayList<Eleve> NonAffectedList,Scanner clavier){
        boolean SortieMenu=false;
        int Entrée;
        Eleve EleveSelected;
        int k;
        while(!SortieMenu){
            System.out.println("Une journée sans gueule de bois est une journée à rentabiliser , choisis un Elève :\n");//\n1.Option 1  2.Option 2  3.Revenir au Menu Principal");
            for(k=0;k<NonAffectedList.size();k++){
                System.out.println((k+1)+"."+NonAffectedList.get(k).toString());
            }
            System.out.println(NonAffectedList.size() + "Annuler");
            Entrée=clavier.nextInt();

            EleveSelected=NonAffectedList.get(Entrée);
            NonAffectedList.remove(Entrée);

            System.out.println("Dans quel lieu veux-tu l'envoyer ?\n");

            if(Entrée==3){
                SortieMenu=true;
            }
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

