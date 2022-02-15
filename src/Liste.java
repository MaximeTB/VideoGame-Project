import java.security.interfaces.ECKey;
import java.util.ArrayList;
import java.util.Scanner;

public class Liste {
    private String name;
    private ArrayList<Eleve> liste=null;
    private Integer argent;
    private Integer popularite;
    private Integer admin;
    private Integer cohesion;
//    private Integer PV;   //points de victoires, utiles en fin de partie
    private ArrayList<Pole> poles;


    public Integer getAdmin() {
        return admin;
    }

    public Integer getArgent() {
        return argent;
    }

    public Integer getPopularite() {
        return popularite;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Eleve> getListe() {
        return liste;
    }

    public void setArgent(Integer argent) {
        this.argent = argent;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public void setPopularite(Integer popularite) {
        this.popularite = popularite;
    }


    public void addEleve(Eleve e){
        liste.add(e);
    }

    public void recrute(int nb){  //propose nb choix d'élève, vous en choisissez 1 à recruter
        Scanner scan=new Scanner(System.in);
        ArrayList<Eleve> choice=new ArrayList<>();
        for(int i=0;i<nb;i++){
            Eleve e = new Eleve();
            choice.add(e);
            System.out.println(e);
        }
        String answer = scan.nextLine();
        Integer intAnswer = Integer.parseInt(answer);
        if(intAnswer<nb){
            this.addEleve(choice.get(intAnswer));
            System.out.println("vous avez recruté :" + choice.get(intAnswer));
        }

    }


    public Liste(String nameList, String namePrez){
        Eleve President = new Eleve(namePrez);

        name=nameList;
        liste=new ArrayList<Eleve>();
        liste.add(President);
        argent=0;
        admin=50;
        popularite=0;
        cohesion=100;
        poles.add(new Pole("Bureau","gris"));
        poles.add(new Pole("Pole soirée","gris"));
        poles.add(new Pole("Pole animation","gris"));
        poles.add(new Pole("Pole communication","gris"));
        poles.add(new Pole("Pole partenariat","gris"));
        poles.add(new Pole("Pole bouffe","gris"));
        poles.get(0).addMember(President);
    }

    @Override
    public String toString() {
        return  "name=" + name +
                ", membres :" + liste ;
    }

    public static void main(String[] args){
        Liste BDTerre=new Liste(args[0],args[1]);
        /*Eleve e=new Eleve("poi");
        for(int i=0;i<12;i++){
            e.studying();
            //System.out.println(e.getStudies());
        }
        BDTerre.addEleve(e);
        System.out.println(BDTerre);

        System.out.println(BDTerre.getAdmin());
        System.out.println(BDTerre.getArgent());
        System.out.println(BDTerre.getPopularite());




        e.silliker(BDTerre);
        System.out.println(BDTerre.getArgent());*/
        while(BDTerre.getListe().size()<10){
            BDTerre.recrute(3);
        }
        System.out.println(BDTerre);

    }


}
