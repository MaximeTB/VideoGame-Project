import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String name;
    private ArrayList<Eleve> ListeEleve=null;
    private ArrayList<Pole> poles= new ArrayList<>();
    private Integer argent ,popularite,admin,PV,cohesion ;  //PV :points de victoires, utiles en fin de partie

    public Player(){} //test pour QG

    public Player(String nameList, String namePrez){
        Eleve President = new Eleve(namePrez);

        name=nameList;
        ListeEleve=new ArrayList<Eleve>();
        ListeEleve.add(President);
        argent=0;
        admin=10;
        cohesion=10;
        popularite=0;
        PV=0;
        poles.add(new Pole("Bureau","gris"));
        poles.add(new Pole("Pole soirée","noir"));
        poles.add(new Pole("Pole animation","rouge","bleu"));
        poles.add(new Pole("Pole communication","vert"));
        poles.add(new Pole("Pole partenariat","gris"));
        poles.add(new Pole("Pole bouffe","orange"));
        poles.get(0).addMember(President);
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

    //Les Getters
        public Integer getAdmin () {
        return admin;
    }
        public Integer getArgent () {
        return argent;
    }
        public Integer getPopularite () {
        return popularite;
    }
        public String getName () {
        return name;
    }
        public ArrayList<Eleve> getListeEleve () {
        return ListeEleve;
    }
        public Integer getPV () {
        return PV;
    }
        public Integer getCohesion () {
        return cohesion;
    }
    //Fin Getters

    //Les Setters
    public void setArgent(Integer argent) {
        this.argent = argent;
    }
    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
    public void setPopularite(Integer popularite) {
        this.popularite = popularite;
    }
    public void setPV(Integer PV) {
        this.PV = PV;
    }

    public void setCohesion(Integer cohesion){
        this.cohesion = cohesion;
    }
    //Fin Setters

    public void addEleve(Eleve e){
        ListeEleve.add(e);
    }



    public void ApplyEffect(Effect effet){
        String stat= effet.getStat();
        int value = effet.getValue();

        if(stat=="AGT"){
            this.setArgent(this.getArgent()+value);
        }
        else if(stat=="ADM"){
            this.setAdmin(this.getAdmin()+value);
        }
        else if (stat=="PPT"){
            this.setPopularite(this.getPopularite()+value);
        }
        else if (stat=="PVT"){
            this.setArgent(this.getArgent()+value);
        }

    }


    @Override
    public String toString() {
        return "ListeEleve{" +
                "name='" + name + '\'' +
                ", ListeEleve=" + ListeEleve +
                ", argent=" + argent +
                ", popularite=" + popularite +
                ", admin=" + admin +
                ", PV=" + PV +
                '}';
    }

    public void generateList(int upperBound){
        int temp2 = 1;
        for (int i = 0 ; i < upperBound; i++) {
            Eleve test = new Eleve();
            //System.out.println("Studies = " + test.getStudies()); //ok
            //System.out.println("Loyalty = " + test.getLoyalty()); //ok
            int temp = 0;
            System.out.println("Etudiant n°" + temp2 + " : " + test.getName());
            for (Skills s : test.getSkillsList()){
                System.out.println("Skill n°" + temp + " = " + s.getName());
                temp++;
            }
            temp2++;
        }
    }
}
