import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class PoolOfLocation {
    private ArrayList<Lieu> LocationList;
    private int Nb=0 ; //Nb de lieu dedans

    public PoolOfLocation(String filename){
        this.LocationList= new ArrayList<Lieu>();


        try{
            BufferedReader buf = new BufferedReader(new FileReader(filename));
            String s = buf.readLine();
            while(s!=null) {
                s.replaceAll("\"", "");
                String fields[] = s.split(";");
                if (fields.length == 3) {
                    if (fields[1].equals("true")) {
                        this.LocationList.add(new Lieu(fields[0],true,fields[2]));
                    }else if (fields[1].equals("false")){
                        this.LocationList.add(new Lieu(fields[0],false,fields[2]));
                    }
                }else if(fields.length==4) {
                    if (fields[1].equals("true")) {
                        this.LocationList.add(new Lieu(fields[0], true, fields[2], Integer.parseInt(fields[3])));
                    } else if (fields[1].equals("false")) {
                        this.LocationList.add(new Lieu(fields[0], false, fields[2], Integer.parseInt(fields[3])));
                    }
                }
                this.Nb+=1;
                s=buf.readLine();
            }
        }
        catch(Exception e){
            System.out.println("Maybe the file isn't there ?");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "PoolOfEvent{" +
                "Liste de Lieu=" + LocationList +
                " , NbLieu="+ Nb +
                '}';
    }


    public static void main (String[] args){
        PoolOfLocation pool = new PoolOfLocation("data/ListesLieux.csv");
        //System.out.println(pool.LocationList.get(0));
        System.out.println(pool);
    }

}
