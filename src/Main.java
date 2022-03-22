import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;



//extend application pour le javafx
/*
ATTENTION !!

pour ceux qui font du javafx faite attention quand vous changez le main a ce qu'on puisse toujours
compiler le reste sans problème svp
*/

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("ENSEA");
        Group root = new Group();
        QG scene = new QG(root, 600, 400);


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    static ArrayList<Eleve> Test= new ArrayList();

    public static void main(String[] args) {
        launch(args);

        Scanner clavier =new Scanner(System.in);
        Game game = new Game();
        int k;
        for(k=1;k<=10;k++){
            game.Tour(clavier);

        }
        System.out.println("Fin de la partie");
    }
}
