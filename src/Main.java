import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    static ArrayList<Eleve> Test= new ArrayList();
    public static void main(String[] args) {
        for (int i = 0 ; i < 20; i++) {
            Eleve test = new Eleve();
            Test.add(test);
            //System.out.println("Studies = " + test.getStudies()); //ok
            //System.out.println("Loyalty = " + test.getLoyalty()); //ok
            }
        Collections.sort(Test);
        for(Eleve T : Test){
            System.out.println(T);
        }

    }

}
