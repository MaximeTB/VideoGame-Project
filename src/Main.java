import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("ENSEA");
        Group rootQG = new Group();
        Group rootShop = new Group();
        QG qg = new QG(rootQG, 600, 400);
        Shop shop = new Shop(rootShop, 600, 400);


        primaryStage.setScene(qg);
        qg.getButtonShop().setOnAction((e -> primaryStage.setScene(shop)));


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
