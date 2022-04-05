import javax.xml.stream.Location;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class PoolOfLocation {
    private ArrayList<Lieu> LocationList;
    private int Nb=0 ; //Nb de lieu dedans

    public PoolOfLocation(String filename){
        this.LocationList= new ArrayList<Lieu>();


        try{
            Lieu lieu;
            BufferedReader buf = new BufferedReader(new FileReader(filename));
            buf.readLine();
            String s = buf.readLine();
            while(s!=null) {
                s.replaceAll("\"", "");
                String fields[] = s.split(";");
                if (fields.length == 11) {
                    if (fields[1].equals("true")) {
                        this.LocationList.add(new Lieu(fields[0], true, fields[2]));
                    } else if (fields[1].equals("false")) {
                        this.LocationList.add(new Lieu(fields[0], false, fields[2]));
                    }
                } else if (fields.length == 12) {
                    if (fields[1].equals("true")) {
                        this.LocationList.add(new Lieu(fields[0], true, fields[2], Integer.parseInt(fields[3])));
                    } else if (fields[1].equals("false")) {
                        this.LocationList.add(new Lieu(fields[0], false, fields[2], Integer.parseInt(fields[3])));
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


    public static void main (String[] args){
        PoolOfLocation pool = new PoolOfLocation("data/ListesLieux.csv");
        //System.out.println(pool.LocationList.get(0));
        System.out.println(pool.getClass());
    }

}
