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

    public Liste(){}


}
