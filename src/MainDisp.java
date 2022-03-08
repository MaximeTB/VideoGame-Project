import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.stage.Stage;
import java.util.Date;


public class MainDisp extends Application{
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Demo");
        Group root = new Group();

        Map theScene = new Map(root,0);//Création de la Game scene

        theScene.getTest().setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                System.out.println(theScene.getEtat());
                int e = theScene.getEtat()+1;
                e%=3;
                theScene.setEtat(e);
            }
        });

        primaryStage.setScene(theScene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

}
