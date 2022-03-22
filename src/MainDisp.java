import javafx.application.Application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

// TO DO : base de prénoms pour la liste d'élèves.

    public class MainDisp extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{

            primaryStage.setTitle("ENSEA");
            Group root = new Group();
            Map scene = new Map(root, 0);


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
