import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int NbTour;
    private PoolOfEvent pool, DayPool, NightPool;
    private Player player;
    private QGconsole QG;
    private ArrayList<Lieu> ListLieux;

    public Game() {
        //Initialisation du Jeu
        QG = new QGconsole();
        this.player = new Player("BDMichelle","Michelle");
        //test console on fout de l'argent tour 1
        player.setArgent(100);
        System.out.println("Liste : " + player.getName() +"\n"+"Président/Présidente : "+ this.getPlayer().getListeEleve().get(0).getName());
        this.NbTour=1;
        this.ListLieux = new ArrayList<Lieu>();

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
        Lieu Assoce = new Lieu("activité associative", true, "J",3);
        ListLieux.add(Assoce);
        Lieu Rue = new Lieu("La Rue", true, "J");
        ListLieux.add(Rue);Rue.setEOP(2);
        Lieu Amphi = new Lieu("Amphi", true, "J");
        ListLieux.add(Amphi);Amphi.setEOS(2);Amphi.setIsAMPH(1);
        Lieu TP = new Lieu("Salle de TP", false, "J");
        ListLieux.add(TP);TP.setEOS(1);
        Lieu Admin = new Lieu("Bureau de l'administration",true,"J",1);
        ListLieux.add(Admin);Admin.setEOA(1);
        Lieu GrassMat = new Lieu("Grasse matiné", true, "J");
        ListLieux.add(GrassMat);GrassMat.setEOT(-1);
        //nuit
        Lieu Soire = new Lieu("Soirée", true, "N");
        ListLieux.add(Soire);Soire.setEOT(1);Soire.setEOP(5);
        Lieu Argent1 = new Lieu("petit boulot", true, "N");
        ListLieux.add(Argent1);Argent1.setEOM(10);Argent1.setEOT(1);
        Lieu Argent2 = new Lieu("petit boulot moins légal", false, "N");
        ListLieux.add(Argent2);Argent2.setEOM(50);Argent2.setEOT(1);Argent1.setEOA(-1);
        Lieu Argent3 = new Lieu("vente de cookies", false, "N");
        ListLieux.add(Argent3);Argent3.setEOM(25);Argent3.setEOT(1);
        Lieu Revision = new Lieu("Centre doc", true, "N");
        ListLieux.add(Revision);Revision.setEOS(1);Revision.setEOT(-1);
        Lieu Repos = new Lieu("Repos", true, "N");
        ListLieux.add(Repos);Repos.setEOT(-1);
    }

    public void Tour(Scanner clavier){
        boolean FinTour= false,SortieMenu=false;
        int Entrée;
        System.out.println("Tour " + this.getNbTour() + ":" +"\nArgent :"+this.getPlayer().getArgent().toString()
                + "\nAdmin :"+this.getPlayer().getAdmin().toString()
                + "\nPopularité :"+this.getPlayer().getPopularite().toString()
                +"\nCohésion :"+this.getPlayer().getCohesion().toString()
                +"\nPV :"+this.getPlayer().getPV().toString());
        System.out.println("Début du Tours :");

        if(this.getNbTour()%2==0){
            for(Eleve e : player.getListeEleve()){
                e.setStudies(e.getStudies()<=0 ? 0:e.getStudies()-1);
            }
        }//perte de niveau d'étude tout les 2 tours

        if(this.getNbTour()%3==0) {
            System.out.println("event");
        }//gestion des events

        if(player.getPopularite()>=8*player.getListeEleve().size() && player.getListeEleve().size()<9) {
            System.out.println("voulez vous recruter ? 1.oui 2.non");
            Entrée = clavier.nextInt();
            if (Entrée == 1) {
                this.getPlayer().recrute(3);
            }
        }//recrutement

        if(this.getNbTour()%7==6){
            System.out.println("C'est le week-end, l'ENSEA est fermé");
            ListLieux.get(0).ChangeState();
            ListLieux.get(1).ChangeState();
            ListLieux.get(2).ChangeState();
            ListLieux.get(3).ChangeState();
            ListLieux.get(4).ChangeState();

        } //gestion du week-end : lieu de l'ensea désactivé
        if(this.getNbTour()%7==0 && this.getNbTour()!=0){
            System.out.println("Week-end terminé, retour en cour !");
            ListLieux.get(0).ChangeState();
            ListLieux.get(1).ChangeState();
            ListLieux.get(2).ChangeState();
            ListLieux.get(3).ChangeState();
            ListLieux.get(4).ChangeState();

        }

        ArrayList<Eleve> DayNonAffectedList = new ArrayList<>(this.getPlayer().getListeEleve());
        ArrayList<Eleve> NightNonAffectedList = new ArrayList<>(this.getPlayer().getListeEleve());

        while(!FinTour){
            System.out.println("Quel Menu veut-tu ouvrir ? \n1.Jour 2.Nuit 3.QG 4.Fin du Tour");
            Entrée=clavier.nextInt();
            if(Entrée==1){
                this.MenuJour(DayNonAffectedList,clavier,"J");
            }else if (Entrée==2){
                this.MenuJour(NightNonAffectedList,clavier,"N");
            }else if(Entrée==3){
                while(!SortieMenu){
                    System.out.println("Bienvenue au QG chacal , que veux-tu faire ?\n1.Liste 2.Magasin  3. Inventaire 4. gestion des pôles 5.Revenir au Menu Principal");
                    Entrée=clavier.nextInt();
                    if(Entrée==5){
                        SortieMenu=true;
                    }
                    else if(Entrée ==4){
                        System.out.println("gérer les poles WIP");
                    }else if(Entrée == 3){ // inventaire
                        while(Entrée!=7) {
                            QG.inventaire();
                            Entrée = clavier.nextInt();
                        }
                    }else if(Entrée == 2){ //shop
                        while(Entrée!=7) {
                            QG.shop();
                            System.out.println("Argent disponible : " + player.getArgent());
                            Entrée = clavier.nextInt();
                            QG.shopAction(Entrée, player);
                        }
                    }else if (Entrée == 1){ // liste
                        player.displayListEleve();
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
        for(Lieu L : ListLieux){
            L.ApplyLieuEffect(player);
        }
    }

//Gestion des Menu

    public void MenuJour(ArrayList<Eleve> NonAffectedList,Scanner clavier, String moment){ //moment permet de savoir si c'est le menu jour ou nuit
        boolean SortieMenu=false;
        int Entrée;
        Eleve EleveSelected;
        int k;
        while(!SortieMenu){
            System.out.println("Une journée sans gueule de bois est une journée à rentabiliser , choisis un Elève :\n");//\n1.Option 1  2.Option 2  3.Revenir au Menu Principal");
            for(k=0;k<NonAffectedList.size();k++){
                System.out.println((k+1)+"."+NonAffectedList.get(k).toString());
            }
            System.out.println(NonAffectedList.size()+1 + ".Annuler");
            Entrée=clavier.nextInt();

        if(Entrée>=NonAffectedList.size()+1){
            SortieMenu = true;
        }

        if (!SortieMenu && NonAffectedList.size()!=0) {
            EleveSelected=NonAffectedList.get(Entrée-1);

            System.out.println("Dans quel lieu veut-tu l'envoyer ?\n");
            int i=1;
            int diff=0;
            for(k = 0; k < this.ListLieux.size(); ++k) {
                if(ListLieux.get(k).getType().equals(moment)){
                    System.out.println(i + "." + ListLieux.get(k));
                    diff=i-k;
                    i++;
                }
            }
            Entrée=clavier.nextInt();
            if(Entrée<=i-1){
                int err = ListLieux.get(Entrée-diff).placeStudent(EleveSelected);
                if(err==0){
                    NonAffectedList.remove(EleveSelected);
                }
            } else {
                SortieMenu = true;
            }
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

    public ArrayList<Lieu> getListLieux() {
        return ListLieux;
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

