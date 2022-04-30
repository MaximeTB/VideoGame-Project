import javax.xml.stream.Location;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/**
 * Classe permettant la gestion des objets Lieu (code incomplet)
 * Elle possede un attribut "principale" LocationList qui est une ArrayList
 * regroupant tous les Lieux, puis des attributs sous-listes pour chaque sous-categories
 * de Lieu (exemple : NightLocation pour tous les lieux de type nocturne)
 * (nous n'avons pas eu le temps d'implementer les sous-listes)
 */
public class PoolOfLocation {
    /**
     * Liste de tout les lieux
     */
    private final ArrayList<Lieu> LocationList;
    /**
     * Nombre total de lieu
     */
    private int Nb=0 ;

    /**
     * Genere a partir d'un fichier csv tout les lieux du jeu et remplie l'attribut LocationList avec.
     * @param filename Lien du fichier csv
     */
    public PoolOfLocation(String filename){
        this.LocationList= new ArrayList<Lieu>();


        try{
            Lieu lieu;
            BufferedReader buf = new BufferedReader(new FileReader(filename));
            buf.readLine();
            String s = buf.readLine();
            while(s!=null) {
                s.replaceAll("\"", "");
                String[] fields = s.split(";");
                if (fields.length == 11) {
                    if (fields[1].equals("true")) {
                        this.LocationList.add(new Lieu(fields[0], true, fields[2]));
                    } else if (fields[1].equals("false")) {
                        this.LocationList.add(new Lieu(fields[0], false, fields[2]));
                    }
                } else if (fields.length == 12) {
                    if (fields[1].equals("true")) {
                        this.LocationList.add(new Lieu(fields[0], true, fields[2], Integer.parseInt(fields[11])));
                    } else if (fields[1].equals("false")) {
                        this.LocationList.add(new Lieu(fields[0], false, fields[2], Integer.parseInt(fields[11])));
                    }
                }
                lieu= this.LocationList.get(this.LocationList.size()-1);
                if (Integer.parseInt(fields[3]) == 1){
                      lieu.setIsAMPH(1);
                }
                lieu.setEOP(Integer.parseInt(fields[4]));
                lieu.setEOM(Integer.parseInt(fields[5]));
                lieu.setEOT(Integer.parseInt(fields[6]));
                lieu.setEOS(Integer.parseInt(fields[7]));
                lieu.setEOA(Integer.parseInt(fields[8]));
                lieu.setEOC(Integer.parseInt(fields[9]));
                lieu.setEOPV(Integer.parseInt(fields[10]));
                this.Nb+=1;
                s=buf.readLine();
            }
            buf.close();
        }
        catch(Exception e){
            System.out.println("Maybe the file isn't there ?");
            e.printStackTrace();
        }
    }

    public ArrayList<Lieu> getLocationList() {
        return LocationList;
    }

    @Override
    public String toString() {
        return "PoolOfEvent{" +
                "Liste de Lieu=" + LocationList +
                " , NbLieu="+ Nb +
                '}';
    }

/*
    public static void main (String[] args){
        PoolOfLocation pool = new PoolOfLocation("data/ListesLieux.csv");
        for(Lieu l : pool.LocationList) {
            System.out.println(l);
        }
        System.out.println(pool.getClass());
    }
*/
}
