/*import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;


public class Map extends Scene{


    private StaticThing jour;
    private StaticThing nuit;
    private StaticThing qg;
    private static int etat=0;
    private Button test = new Button("Click Me");
    private Label label = new Label("");


    public Map(Group parent, int etat){
        super(parent,1000,770);


        switch (etat){
            case 0 :
                jour = new StaticThing(0,5000,0,2000,0,"map.jpg");
                parent.getChildren().add(jour.getImg());
                parent.getChildren().add(test);
                break;
            /*case 1 :
                nuit = new StaticThing(0,2000,0,2000,0,"mapNuit.jpg");
                parent.getChildren().add(nuit.getImg());
                parent.getChildren().add(test);
                break;
            default :
                qg = new StaticThing(0,2000,0,2000,0,"mapQg.jpg");
                parent.getChildren().add(qg.getImg());
                parent.getChildren().add(test);
                break;*/
     /*   }


    }

    public Button getTest() {
        return test;
    }

    public int getEtat() {
        return etat;
    }

    public Label getLabel() {
        return label;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
*/