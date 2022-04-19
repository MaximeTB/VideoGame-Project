import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;


//Source de la liste de nom : https://www.data.gouv.fr/fr/datasets/liste-de-prenoms-et-patronymes/

public class PoolOfName {
    private ArrayList<String> list;
    private Random rand=new Random();
    private String name;

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
            buf.close();
        }
        catch(Exception e){
            System.out.println("Maybe the file isn't there ?");
            e.printStackTrace();
        }
    }

    public String RandomName(){
        name=this.getList().get(rand.nextInt(this.getList().size()));
        this.getList().remove(name);
        return name;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public static void main (String[] args){
        String name;
        int i;
        PoolOfName names = new PoolOfName("data/prenom.csv");
        for (i=0;i<100;i++){
            name = names.RandomName();
            System.out.println(name);
        }
    }
}


