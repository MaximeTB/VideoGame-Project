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
            Group root = new Group();
            ArrayList<Eleve> listeEleve=new ArrayList<Eleve>();
            for(int i=0 ; i<15; i++){
                listeEleve.add(new Eleve("Pierre"));
            }
            Map scene = new Map(root, listeEleve);



            primaryStage.setScene(scene);
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
