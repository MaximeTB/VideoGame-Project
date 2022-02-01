import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Eleve> Player=null;
    private Integer argent;
    private Integer popularite;
    private Integer admin;
    private Integer PV;   //points de victoires, utiles en fin de partie


    public Player(String nameList, String namePrez){
        name=nameList;
        Player=new ArrayList<Eleve>();
        Player.add(new Eleve(namePrez));
        argent=0;
        admin=50;
        popularite=0;
    }



    //Les Getters

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

    public ArrayList<Eleve> getPlayer() {
        return Player;
    }

    public Integer getPV() {
        return PV;
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

    public Player(Integer PV) {
        this.PV = PV;
    }

    //Fin Setters


    public void addEleve(Eleve e){
        Player.add(e);
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



    public static void main(String[] args){
        Player BDTerre=new Player(args[0],args[1]);
        System.out.println(BDTerre.getAdmin());
        System.out.println(BDTerre.getPlayer().get(0).getName());
        System.out.println(BDTerre.getArgent());
        System.out.println(BDTerre.getName());
        System.out.println(BDTerre.getPopularite());

        Eleve e=new Eleve("poi");
        for(int i=0;i<12;i++){
            e.studying();
            System.out.println(e.getStudies());
        }

        e.silliker(BDTerre);
        System.out.println(BDTerre.getArgent());
    }

}