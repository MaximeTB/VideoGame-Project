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

        Game game = new Game();

        primaryStage.setTitle("ENSEA");
        Group rootQG = new Group();
        Group rootShop = new Group();
        Group rootPoles = new Group();
        Group rootInventory = new Group();
        QG qg = new QG(rootQG, 600, 400);
        Inventory inventory = new Inventory(rootInventory, 600, 400, game.getPlayer());
        GestionPoles poles = new GestionPoles(rootPoles, 600, 400, game.getPlayer());
        Shop shop = new Shop(rootShop, 600, 400, game.getPlayer(), inventory);



        primaryStage.setScene(qg);
        qg.getButtonShop().setOnAction((e -> primaryStage.setScene(shop)));
        qg.getButtonInventory().setOnAction((e -> primaryStage.setScene(inventory)));
        qg.getButtonPoles().setOnAction((e -> primaryStage.setScene(poles)));
        shop.getButtonExit().setOnAction((e -> primaryStage.setScene(qg)));
        inventory.getButtonExit().setOnAction((e -> primaryStage.setScene(qg)));
        poles.getButtonExit().setOnAction((e -> primaryStage.setScene(qg)));
        poles.updateButtons();

        primaryStage.show();

    }

    static ArrayList<Eleve> Test= new ArrayList();


    /*
    public static void main(String[] args) {
        launch(args);

        Scanner clavier =new Scanner(System.in);
        Game game = new Game();
        int k;
        for(k=1;k<=10;k++){
            game.Tour(clavier);

        }
        System.out.println("Fin de la partie");
    }*/

}
