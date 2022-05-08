import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;


//Source de la liste de nom : https://www.data.gouv.fr/fr/datasets/liste-de-prenoms-et-patronymes/

/**
 * Classe utilisee pour generer aleatoirement des noms pour les eleves. Nous avons utilisee une liste sous format csv disponible sur internet
 * recensant plus de 14000 nom de toute origine.
 */
public class PoolOfName {
    /**
     * Liste des noms
     */
    private ArrayList<String> list;
    /**
     * Classe permettant de generer aleatoirement des nombres.
     */
    private Random rand=new Random();


    /**
     * Rempli l'attribut list avec tous les noms du csv
     * @param filename Lien du csv
     */
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


    /**
     * Pioche un indice aleatoire, pioche dans la liste le nom correspondant puis supprime le nom de la liste.
     * @return Un nom pioché aleatoirement dans la liste
     */
    public String RandomName(){
        String name=this.getList().get(rand.nextInt(this.getList().size()));
        this.getList().remove(name);
        return name;
    }

    public ArrayList<String> getList() {
        return list;
    }
/*
    public static void main (String[] args){
        String name;
        int i;
        PoolOfName names = new PoolOfName("data/prenom.csv");
        for (i=0;i<100;i++){
            name = names.RandomName();
            System.out.println(name);
        }
    }*/
}


