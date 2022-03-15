import java.util.ArrayList;
import java.util.Scanner;

public class TourDeJeu {
    private static int tour;
    private ArrayList<Eleve> dispo;

    public TourDeJeu(Player Player){
        tour++;
        dispo= Player.getListeEleve();
        Scanner sc=new Scanner(System.in);
        for (Eleve e : dispo){
            System.out.println("Que doit faire "+e.getName()+"(silliker ou etudie");
            String choix=sc.next();// choix de l'action
            if(choix.equals("silliker")){
                e.silliker(Player);//e.action();   //action choisie
            }
            if(choix.equals("etudie")){
                e.studying();
            }
        }
    }

    public static void main(String[] args){
        Player bde=new Player("bde","machin");
        System.out.println(bde.getArgent());
        for(int i=0;i<3;i++){
            TourDeJeu tour=new TourDeJeu(bde);
            System.out.println(bde.getArgent());
        }
    }
}
