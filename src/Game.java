import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int NbTour;
    private PoolOfEvent pool, DayPool, NightPool;
    private Player player;

    public Game() {
        //Initialisation du Jeu
        this.player = new Player("BDMichelle","Michelle");
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
        Lieu Amphi = new Lieu("Amphi", true, "D");
        Lieu Admin = new Lieu("Bureau de l'administration",false,"D",1);
        Lieu Soire = new Lieu("Soirée", true, "N");
        Lieu Repos = new Lieu("Repos", true, "N");
        Lieu Assoce = new Lieu("activité associative", true, "D",3);

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
                    System.out.println("Bienvenue au QG chacal , que veux-tu faire ?\n1.Liste 2.Magasin  3.Revenir au Menu Principal");
                    Entrée=clavier.nextInt();
                    if(Entrée==3){
                        SortieMenu=true;
                    }else if(Entrée == 2){

                    }else if (Entrée == 1){
                        Player liste = new Player();
                        liste.generateList(5);

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

