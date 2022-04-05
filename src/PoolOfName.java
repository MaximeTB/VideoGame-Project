import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


//Source de la liste de nom : https://www.data.gouv.fr/fr/datasets/liste-de-prenoms-et-patronymes/

public class PoolOfName {
    private ArrayList<String> list;

    public PoolOfName(String filename){
        this.list=new ArrayList<String>();
        try {
            BufferedReader buf = new BufferedReader(new FileReader(filename));
            buf.readLine();
            String s = buf.readLine();
            while(s!=null){
                s.replaceAll("\"", "");
                String fields[] = s.split(",");
                this.list.add(fields[0]);
                s=buf.readLine();
            }
        }
        catch(Exception e){
            System.out.println("Maybe the file isn't there ?");
            e.printStackTrace();
        }
    }


    public static void main (String[] args){
        PoolOfName names = new PoolOfName("data/prenom.csv");
        System.out.println(names.list);
    }
}


