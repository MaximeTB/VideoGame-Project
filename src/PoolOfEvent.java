import java.awt.Event;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class PoolOfEvent {
    private ArrayList<Evenement> eventList;
    private static int NbTot=0;
    private int NbPool;

    public PoolOfEvent(String fileName) {  //le constructeur extrait la liste d'event d'un fichier
        NbTot += 1;
        this.eventList= new ArrayList<Evenement>();

        try {
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();
            s= buf.readLine();
            while(s!=null){
                s=s.replaceAll("\"","");
                String fields[]=s.split(";");
                this.eventList.add(new Evenement(fields[0],Integer.parseInt(fields[1]),fields[2]));

                int nbEffet = Integer.parseInt(fields[3]);
                for (int i=0;i<nbEffet;i++)
                {
                    this.eventList.get(eventList.size()-1).addEffect(new Effect(fields[4+i*2],Integer.parseInt(fields[5+i*2])));
                }
                s=buf.readLine();
            }
        }
        catch(Exception e){
            System.out.println("Maybe the file isn't there ?");
            e.printStackTrace();
        }
    }

    public static void main (String[] args){
        PoolOfEvent pool = new PoolOfEvent("./data/ListeEvent.csv");



    }
}
