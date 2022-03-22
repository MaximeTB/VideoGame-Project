import javafx.animation.AnimationTimer;
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
    private Button rue = new Button("rue");
    private Button asso = new Button("asso");
    private Button admin = new Button("admin");
    private Button amphi = new Button("amphi");
    private Button td = new Button("td");
    private Button grasseMat = new Button("grasse mat");


    public Map(Group parent, int etat){
        super(parent,1000,770);


        switch (etat){
            case 0 :
                jour = new StaticThing(0,5000,0,2000,0,"out/production/VideoGame-Project/mapJour.JPG");
                parent.getChildren().add(jour.getImg());
                parent.getChildren().add(rue);
                parent.getChildren().add(asso);
                parent.getChildren().add(admin);
                parent.getChildren().add(amphi);
                parent.getChildren().add(td);
                parent.getChildren().add(grasseMat);

                rue.setLayoutX(350);
                rue.setLayoutY(500);

                asso.setLayoutX(700);
                asso.setLayoutY(200);

                admin.setLayoutX(150);
                admin.setLayoutY(530);

                amphi.setLayoutX(570);
                amphi.setLayoutY(630);

                td.setLayoutX(730);
                td.setLayoutY(560);

                grasseMat.setLayoutX(50);
                grasseMat.setLayoutY(50);
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
        }


    }

    public Button getTest() {
        return rue;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
