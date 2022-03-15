import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static ArrayList<Eleve> Test= new ArrayList();
    public static void main(String[] args) {
        Scanner clavier =new Scanner(System.in);
        Game game = new Game();
        int k;
        for(k=1;k<=10;k++){
            game.Tour(clavier);

        }
        System.out.println("Fin de la partie");
    }


}
