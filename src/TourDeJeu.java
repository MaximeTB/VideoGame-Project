import java.util.ArrayList;
import java.util.Scanner;

public class TourDeJeu {
    private static int tour;
    private ArrayList<Eleve> dispo;

    public TourDeJeu(Liste liste){
        tour++;
        dispo= liste.getListe();
        Scanner sc=new Scanner(System.in);
        for (Eleve e : dispo){
            System.out.println("Que doit faire "+e.getName()+"(silliker ou etudie");
            String choix=sc.next();// choix de l'action
            if(choix.equals("silliker")){
                e.silliker(liste);//e.action();   //action choisie
            }
            if(choix.equals("etudie")){
                e.etudie();
            }
        }
    }

    public static void main(String[] args){
        Liste bde=new Liste("bde","machin");
        System.out.println(bde.getArgent());
        for(int i=0;i<3;i++){
            TourDeJeu tour=new TourDeJeu(bde);
            System.out.println(bde.getArgent());
        }
    }
}
