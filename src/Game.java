import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int NbTour;
    private final int MaxTour=45;
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
        player.setPopularite(30);
        System.out.println("Liste : " + player.getName() +"\n"+"Président/Présidente : "+ this.getPlayer().getListeEleve().get(0).getName());
        this.NbTour=1;
        this.ListLieux = new ArrayList<Lieu>();

        //Listes des Events
        PoolOfEvent pool = new PoolOfEvent("./data/ListeEvent.csv");

        PoolOfEvent DayEvent= new PoolOfEvent();
        for (Evenement e : pool.getEventList()){
            if (e.getType().equals("J")) {
                DayEvent.add(e);
            }
        }

        PoolOfEvent NightEvent= new PoolOfEvent();
        for (Evenement e : pool.getEventList()){
            if (e.getType().equals("N")) {
                NightEvent.add(e);
            }
        }

        //initialisation des lieux
        PoolOfLocation poolOfLocation = new PoolOfLocation("data/ListesLieux.csv");
        this.ListLieux=poolOfLocation.getLocationList();

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

        //effet de début de tours
        if(getNbTour()>MaxTour){
           DebutSemaineListe();
        }//début de semaine de liste
        if(getNbTour()%2==0 || getNbTour()<MaxTour){
            for(Eleve e : player.getListeEleve()){
                e.setStudies(e.getStudies()<=0 ? 0:e.getStudies()-1);
            }
        }//perte de niveau d'étude tout les 2 tours
        if(getNbTour()%3==0) {
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
        //fin effet de début de tours

        //initialisation des listes d'élèves dont l'action n'a pas été choisie
        ArrayList<Eleve> DayNonAffectedList = new ArrayList<>(this.getPlayer().getListeEleve());
        ArrayList<Eleve> NightNonAffectedList = new ArrayList<>(this.getPlayer().getListeEleve());

        while(!FinTour){
            System.out.println("Quel Menu veut-tu ouvrir ? \n1.Jour\n2.Nuit\n3.QG\n4.Fin du Tour");
            Entrée=clavier.nextInt();
            if(Entrée==1){
                this.Menu(DayNonAffectedList,clavier,"J");
            }else if (Entrée==2){
                this.Menu(NightNonAffectedList,clavier,"N");
            }else if(Entrée==3){
                this.MenuGQ(clavier);
            } else if(Entrée==4){
                FinTour=true;
            }
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

    public void Menu(ArrayList<Eleve> NonAffectedList,Scanner clavier, String moment){ //moment permet de savoir si c'est le menu jour ou nuit
        boolean SortieMenu=false;
        int Entrée;
        Eleve EleveSelected;
        int k;
        while(!SortieMenu){
            if (moment.equals("J")) {
                System.out.println("Une journée sans gueule de bois est une journée à rentabiliser , choisis un Elève :");//\n1.Option 1  2.Option 2  3.Revenir au Menu Principal");
            }else if(moment.equals("N")){
                System.out.println("C'est la nuit lol");
            }
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
            int i=0;
            ArrayList<Lieu> LieuDisp=new ArrayList<>();
            for(Lieu L : ListLieux) {
                if(L.getType().equals(moment)){
                    System.out.println(i+1 + "." + L.getname());
                    LieuDisp.add(L);
                    i++;
                }
            }
            Entrée=clavier.nextInt();
            if(Entrée<=LieuDisp.size()){
                int err = LieuDisp.get(Entrée-1).placeStudent(EleveSelected);
                if(err==0){
                    NonAffectedList.remove(EleveSelected);
                    SortieMenu = true;
                }
            } else {
                SortieMenu = true;
            }
        }
    }

}//menu pour le jour et la nuit

    public void MenuGQ(Scanner clavier){
        boolean SortieMenu=false;
        int Entrée;
        while(!SortieMenu){
            System.out.println("Bienvenue au QG chacal , que veux-tu faire ?\n1. Liste\n2. Magasin\n3. Inventaire\n4. gestion des pôles\n5.Revenir au Menu Principal");
            Entrée=clavier.nextInt();
            if(Entrée==5){
                SortieMenu=true;
            }
            else if(Entrée ==4){
                this.MenuPole(clavier);
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
    }//menu pour le QG

    public void MenuPole(Scanner clavier){
        boolean SortieMenu=false;
        int Entrée;

        while(!SortieMenu) {
            int i=1;
            System.out.println("1. Voir les poles\n2. Créer un pole\n3. Gérer les poles\n4. Annuler");
            Entrée = clavier.nextInt();
            if (Entrée==1){
                this.getPlayer().displayPole();
            }//affichage pole
            else if(Entrée==2){
                System.out.println("Choisissez le pole à créer :");
                ArrayList<Pole> ToCreat=new ArrayList<>();
                for(Pole P:this.getPlayer().getPoles()) {
                    if (!P.isCreated()) {
                        System.out.println(i+". "+P.getName());
                        ToCreat.add(P);
                        i++;
                    }
                }
                Entrée=clavier.nextInt();
                if(Entrée<=ToCreat.size()){
                    ToCreat.get(Entrée-1).enable();
                }
            }//création de pole
            else if (Entrée==3) {
                editPole(clavier);
            }
            else{
                SortieMenu=true;
            }
        }
    }

    public void editPole(Scanner clavier){
        int Entrée;
        int i =1;
        ArrayList<Eleve> EleveSansPole=new ArrayList<>();
        Eleve ToPlace;
        System.out.println("1. Placer un élève\n2. Changer un élève de pole\n3. Quitter");
        Entrée=clavier.nextInt();
        if(Entrée==1){
            for(Eleve e : this.getPlayer().getListeEleve()){
                if(e.getPole()==null){
                    System.out.println(i+". "+e);
                    EleveSansPole.add(e);
                }
            }
            Entrée=clavier.nextInt();
            ToPlace=EleveSansPole.get(Entrée-1);
            System.out.println("Ou voulez vous le placer ?");
            //this.getPlayer().displayPoleName();
            i=1;
            for(Pole P : this.getPlayer().getPolesDisp()){
                System.out.println(i+". "+P.getName());
                i++;
            }
            Entrée=clavier.nextInt();
            this.getPlayer().getPolesDisp().get(Entrée-1).addMember(ToPlace);
            System.out.println(ToPlace.getName()+" a été placé dans "+this.getPlayer().getPolesDisp().get(Entrée-1).getName());
        }

    }

    //Partie semaine de liste

    public void DebutSemaineListe(){
        //1 : le Cs
        int NbRatrapage=0;
        int NbBonEleves=0;
        for (Eleve e : getPlayer().getListeEleve()){
            if(e.getStudies()<10){
                NbRatrapage++;
            }
            else if(e.getStudies()>15){
                NbBonEleves++;
            }
        }
        this.getPlayer().setAdmin(getPlayer().getAdmin()-NbRatrapage%2+NbBonEleves%2);
        if(getPlayer().getAdmin()<0){
            System.out.println("Dans la mesure ou trop d'élève n'ont pas leur année, l'administration a jugé que vous ne devriez peut être pas devenir BDE...");
            //défaite
        }
        //2 : fin des études, désactivation des lieux et créer les nouveau
        for(Lieu L : getListLieux()){
            if(L.getEOM()!=0 || L.getEOS()!=0){
                L.setAvailable(false);
            }
        }
        getListLieux().add(new Lieu("Faire des crêpes",true,"J", 5));

    }//lance la semaine de liste



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

