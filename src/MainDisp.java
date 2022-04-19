import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.util.ArrayList;

// TO DO : base de prénoms pour la liste d'élèves.

    public class MainDisp extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{

            primaryStage.setTitle("ENSEA");
            Group mapRoot = new Group();
            Group mapNuitRoot = new Group();
            ArrayList<Eleve> listeEleve=new ArrayList<Eleve>();
            PoolOfLocation poolLieux = new PoolOfLocation("C:\\VideoGame-Project\\data\\ListesLieux.csv");
            Player player = new Player("bde","moi",poolLieux);
            System.out.println(player);
            for(int i=0 ; i<15; i++){
                player.recrute(1);
            }
            listeEleve= player.getListeEleve();
            Map map = new Map(mapRoot, listeEleve);

            ArrayList<Eleve> listeEleveNuit = (ArrayList<Eleve>)listeEleve.clone();
            MapNuit mapNuit = new MapNuit(mapNuitRoot, listeEleveNuit);



            primaryStage.setScene(map);
            map.getButtonNuit().setOnAction((e -> primaryStage.setScene(mapNuit)));
            mapNuit.getButtonJour().setOnAction((e -> primaryStage.setScene(map)));


            primaryStage.show();

        }


        public static void main(String[] args) {
            launch(args);
        }
    /*
    public static void main(String[] args) {
        for (int i = 0 ; i < 20; i++) {
            Eleve test = new Eleve();

        }

    }
     */
    }

