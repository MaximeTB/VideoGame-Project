import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private int NbTour;
    private int NbtourList;
    private int NbSemaine;
    private final int MaxTour=45;
    private final int MaxTourList=7;
    private Boolean EndGame = false;
    private PoolOfEvent pool, DayPool, NightPool;
    private Player player;
    private QGconsole QG;
    private Evenement CurrentEvent;
    private ArrayList<Lieu> ListLieux;
    Random crep = new Random();
    private int NBC = 0; //nombre de crêpe

    public Game() {
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


        //Initialisation des Skills
        //PoolsOfSkills poolsOfSkills= new PoolsOfSkills("data/SkillsOnPoles.csv","data/SkillsOnLieu.csv","data/SkillsOnOther.csv","data/SkillsOnRecruit.csv",poolOfLocation,Pole);

        //Initialisation du Jeu
        QG = new QGconsole();
        this.player = new Player("BDMichelle","Michelle",poolOfLocation);
        //test console on fout de l'argent tour 1
        player.setArgent(100);
        System.out.println("Liste : " + player.getName() +"\n"+"Président/Présidente : "+ this.getPlayer().getListeEleve().get(0).getName());
        this.NbTour=1;
    }

    /**
     *
     * @param nb
     */
    public Game(int nb) {
        //Listes des Events
        /*PoolOfEvent pool = new PoolOfEvent("./data/ListeEvent.csv");
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
        }*/

        //initialisation des lieux
        PoolOfLocation poolOfLocation = new PoolOfLocation("data/ListesLieux.csv");
        this.ListLieux=poolOfLocation.getLocationList();


        //Initialisation des Skills
        //PoolsOfSkills poolsOfSkills= new PoolsOfSkills("data/SkillsOnPoles.csv","data/SkillsOnLieu.csv","data/SkillsOnOther.csv","data/SkillsOnRecruit.csv",poolOfLocation,Pole);

        //Initialisation du Jeu
        QG = new QGconsole();
        this.player = new Player("BDMichelle","Michelle",poolOfLocation);
        for(int i=0;i<nb;i++) player.addEleve(new Eleve(player.pool));
        //test console on fout de l'argent tour 1
        player.setArgent(100);
        player.setPopularite(20);
        System.out.println("Liste : " + player.getName() +"\n"+"Président/Présidente : "+ this.getPlayer().getListeEleve().get(0).getName());
        this.NbTour=1;
        this.ListLieux = new ArrayList<Lieu>();
    }

    public void Tour(Scanner clavier){
        boolean FinTour= false,SortieMenu=false;
        //ArrayList<Eleve> DayNonAffectedList = new ArrayList<Eleve>();
        //ArrayList<Eleve> NightNonAffectedList = new ArrayList<Eleve>();
        int Entree;
        System.out.println("Tour " + this.getNbTour() + ":" +"\nArgent :"+this.getPlayer().getArgent().toString()
                + "\nAdmin :"+this.getPlayer().getAdmin().toString()
                + "\nPopularité :"+this.getPlayer().getPopularite().toString()
                +"\nCohésion :"+this.getPlayer().getCohesion().toString()
                +"\nPV :"+this.getPlayer().getPV().toString());
        System.out.println("Début du Tours :");

        //effet de début de tours


        //fin effet de début de tours

        //initialisation des listes d'élèves dont l'action n'a pas été choisie
        ArrayList<Eleve> DayNonAffectedList = new ArrayList<>(this.getPlayer().getListeEleve());
        ArrayList<Eleve> NightNonAffectedList = new ArrayList<>(this.getPlayer().getListeEleve());

        this.NewTour(clavier);

        while(!FinTour){
            System.out.println("Quel Menu veut-tu ouvrir ? \n1.Jour\n2.Nuit\n3.QG\n4.Fin du Tour");
            Entree=clavier.nextInt();
            if(Entree==1){
                this.Menu(DayNonAffectedList,clavier,"J");
            }else if (Entree==2){
                this.Menu(NightNonAffectedList,clavier,"N");
            }else if(Entree==3){
                this.MenuGQ(clavier);
            } else if(Entree==4){
                FinTour=true;
            }
        }
        System.out.println("Fin du Tour \n\n");
    }

    public void NewTour(Scanner clavier){

        //System.out.println("test");
        int Entree;
        this.NbTour+=1;
        for(Lieu L : ListLieux){
            L.ApplyLieuEffect(player);
        }//applique les effets de tous les lieux
        //for(Pole L : player.getPoles()){
        //}
        for(Lieu L : ListLieux){
            L.ReductionDuree();
        }//réduit la duree de tout les lieux, permet de désactiver les lieux temporaire
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
        if(player.getPopularite()>=8*player.getListeEleve().size()) {
            System.out.println("voulez vous recruter ? 1.oui 2.non");
            Entree = clavier.nextInt();
            if (Entree == 1) {
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
            NbSemaine++;

        }
    }

//Gestion des Menu

    public void Menu(ArrayList<Eleve> NonAffectedList,Scanner clavier, String moment){ //moment permet de savoir si c'est le menu jour ou nuit
        boolean SortieMenu=false;
        int Entree;
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
            Entree=clavier.nextInt();

        if(Entree>=NonAffectedList.size()+1){
            SortieMenu = true;
        }

        if (!SortieMenu && NonAffectedList.size()!=0) {
            EleveSelected=NonAffectedList.get(Entree-1);

            System.out.println("Dans quel lieu veut-tu l'envoyer ?\n");
            int i=0;
            ArrayList<Lieu> LieuDisp=new ArrayList<>();
            for(Lieu L : this.getListLieux()) {
                if(L.getType().equals(moment)){
                    System.out.println(i+1 + "." + L.getname());
                    LieuDisp.add(L);
                    i++;
                }
            }
            Entree=clavier.nextInt();
            if(Entree<=LieuDisp.size()){
                int err = LieuDisp.get(Entree-1).placeStudent(EleveSelected);
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
        int Entree;
        while(!SortieMenu){
            System.out.println("Bienvenue au QG chacal , que veux-tu faire ?\n1. Liste\n2. Magasin\n3. Inventaire\n4. gestion des pôles\n5.Revenir au Menu Principal");
            Entree=clavier.nextInt();
            if(Entree==5){
                SortieMenu=true;
            }
            else if(Entree ==4){
                this.MenuPole(clavier);
            }else if(Entree == 3){ // inventaire
                while(Entree!=7) {
                    QG.inventaire();
                    Entree = clavier.nextInt();
                }
            }else if(Entree == 2){ //shop
                while(Entree!=7) {
                    QG.shop();
                    System.out.println("Argent disponible : " + player.getArgent());
                    Entree = clavier.nextInt();
                    QG.shopAction(Entree, player);
                }
            }else if (Entree == 1){ // liste
                player.displayListEleve();
            }
        }
    }//menu pour le QG

    public void MenuPole(Scanner clavier){
        boolean SortieMenu=false;
        int Entree;

        while(!SortieMenu) {
            int i=1;
            System.out.println("1. Voir les poles\n2. Créer un pole\n3. Gérer les poles\n4. Annuler");
            Entree = clavier.nextInt();
            if (Entree==1){
                this.getPlayer().displayPole();
            }//affichage pole
            else if(Entree==2){
                System.out.println("Choisissez le pole à créer :");
                ArrayList<Pole> ToCreat=new ArrayList<>();
                for(Pole P:this.getPlayer().getPoles()) {
                    if (!P.isCreated()) {
                        System.out.println(i+". "+P.getName());
                        ToCreat.add(P);
                        i++;
                    }
                }
                Entree=clavier.nextInt();
                if(Entree<=ToCreat.size()){
                    ToCreat.get(Entree-1).enable();
                }
            }//création de pole
            else if (Entree==3) {
                editPole(clavier);
            }
            else{
                SortieMenu=true;
            }
        }
    }

    public void editPole(Scanner clavier){
        int Entree;
        int i =1;
        ArrayList<Eleve> EleveSansPole=new ArrayList<>();
        Eleve ToPlace;
        System.out.println("1. Placer un élève\n2. Changer un élève de pole\n3. Quitter");
        Entree=clavier.nextInt();
        if(Entree==1){
            for(Eleve e : this.getPlayer().getListeEleve()){
                if(e.getPole()==null){
                    System.out.println(i+". "+e);
                    EleveSansPole.add(e);
                }
            }
            Entree=clavier.nextInt();
            ToPlace=EleveSansPole.get(Entree-1);
            System.out.println("Ou voulez vous le placer ?");
            //this.getPlayer().displayPoleName();
            i=1;
            for(Pole P : this.getPlayer().getPolesDisp()){
                System.out.println(i+". "+P.getName());
                i++;
            }
            Entree=clavier.nextInt();
            this.getPlayer().getPolesDisp().get(Entree-1).addMember(ToPlace);
            System.out.println(ToPlace.getName()+" a été placé dans "+this.getPlayer().getPolesDisp().get(Entree-1).getName());
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
        for(Lieu L : ListLieux){
            if(L.getEOM()!=0 || L.getEOS()!=0)
                L.setAvailable(false);

            else if(L.getname().equals("Crepes J")||L.getname().equals("Crepes N"))
                L.setAvailable(true);

            else if(L.getClass()==Animation.class)
                L.setAvailable(true);
        }


        NBC=crep.nextInt(20)+20; //premère commande de crep

    }//lance la semaine de liste

    public void NewTourSemaineListe(){
        if(NbtourList>MaxTourList) EndGame();

        //gestions des crepes
        int diff = player.getNbCrepe()-NBC;
        if(diff>=0) player.gainPV(20+2*diff);
        else player.gainPV(20*diff-30);
        NBC=crep.nextInt(20)+15;

        //afficher le nombre de crêpes à donner !!
    }

public void EndGame(){
    System.out.println("fin");
    if(player.getPV()>3000){
        System.out.println("c'est gagné !");
    }
    else System.out.println("perdu D:");
    EndGame=true;
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
    public Evenement getCurrentEvent(){return CurrentEvent;}
//

    public boolean FinTour(String Entree){
        if(Entree.equals("fin")){
            return true;
        }else{
            return false;
        }
    }

}

