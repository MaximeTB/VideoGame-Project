import java.awt.Event;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Cette classe permet grace a son constructeur de creer chacun des Lieux et de les stocker dans une liste a partir d'un fichier csv.
 */
public class PoolOfEvent {
    /**
     * Liste des Evenements.
     */
    private ArrayList<Evenement> eventList;

    private int NbEvent=0; //Nb d'évènements dans la pool

    /**
     * Dans un fichier csv ont ete repertories les differents Evenements et leurs caracteristiques.<br/>
     * Chacun de ces fichiers est lu ligne par ligne (1 ligne = 1 Evenement),
     * pour chaque ligne un String est genere puis convertie en un tableau de String en separant ce qui se trouve entre chaque ";",
     * et enfin en utilisant le contenu de chacun de ces Strings dans l'ordre, l'Evenement est cree.
     * @param fileName Lien du fichier csv repertoriant tous les Evenements.
     */
    public PoolOfEvent(String fileName) {  //le constructeur extrait la liste d'event d'un fichier
        this.eventList= new ArrayList<Evenement>();

        try {
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();
            s= buf.readLine();
            while(s!=null){
                s=s.replaceAll("\"","");
                String fields[]=s.split(";");
                //this.eventList.add(new Evenement(fields[0],Integer.parseInt(fields[1]),fields[2]));

                int nbEffet = Integer.parseInt(fields[3]);
                for (int i=0;i<nbEffet;i++)
                {
                    //this.eventList.get(eventList.size()-1).addEffect(new Effect(fields[4+i*2],Integer.parseInt(fields[5+i*2])));
                }
                this.NbEvent+=1;
                s=buf.readLine();
            }
            buf.close();
        }
        catch(Exception e){
            System.out.println("Maybe the file isn't there ?");
            e.printStackTrace();
        }
    }

    /**
     * Permet d'ajouter un Evenement a la liste (sans passer par this.eventList).
     * @param event Evenement que l'on veut ajouter.
     */
    public void add(Evenement event){
        this.eventList.add(event);
        this.NbEvent+=1;
    }





    @Override
    public String toString() {
        return "PoolOfEvent{" +
                "eventList=" + eventList +
                " , NbEvent="+ NbEvent +
                '}';
    }


    public static void main (String[] args){
        PoolOfEvent pool = new PoolOfEvent("./data/ListeEvent.csv");
        System.out.println(pool.eventList.get(0));
        System.out.println(pool);
    }

    public ArrayList<Evenement> getEventList() {
        return eventList;
    }
}
