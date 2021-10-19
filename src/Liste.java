import java.util.ArrayList;

public class Liste {
    private String name;
    private ArrayList<Eleve> liste=null;
    private Integer argent;
    private Integer popularite;
    private  Integer admin;


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

    public Liste(){}

    public Liste(String nameList, String namePrez){
        name=nameList;
        liste=new ArrayList<Eleve>();
        liste.add(new Eleve(namePrez));
        argent=0;
        admin=50;
        popularite=0;
    }

    public static void main(String[] args){
        Liste BDTerre=new Liste(args[0],args[1]);
        System.out.println(BDTerre.getAdmin());
        System.out.println(BDTerre.getListe().get(0).getName());
        System.out.println(BDTerre.getArgent());
        System.out.println(BDTerre.getName());
        System.out.println(BDTerre.getPopularite());

        Eleve e=new Eleve("poi");
        for(int i=0;i<12;i++){
            e.etudie();
            System.out.println(e.getEtude());
        }

        e.silliker(BDTerre);
        System.out.println(BDTerre.getArgent());
    }

}
