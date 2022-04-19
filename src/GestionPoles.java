import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class GestionPoles extends Scene {
    private Group parent;
    private StaticThing background;
    private Player player;
    private Pole currentPole;

    private ImageButton buttonExit;
    private ImageButton buttonLeft;
    private ImageButton buttonRight;

    private Text poleDisplay;
    private Integer indexPole = 0;
    private Integer indexEleve = 0;
    private ArrayList<ImageButton> buttonPlayers = new ArrayList<>();

    public ImageButton getButtonExit(){
        return buttonExit;
    }


    public GestionPoles(Group parent, int width, int height, Player player) {
        super(parent, width, height);
        this.parent = parent;
        this.player = player;
        this.currentPole = player.getPoles().get(indexPole);
        background = new StaticThing(0, 600, 0, 400, 0, "polesBG.jpg");

        updateEleves();

        buttonExit = new ImageButton();
        buttonExit.setButtonExit(110);

        buttonLeft = new ImageButton();
        buttonLeft.updateImages(player, "Direction", "leftArrow.png", "leftArrow.png");
        buttonLeft.displayButton(1, 1, 0, 180, 50);
        changePole(buttonLeft);

        buttonRight = new ImageButton();
        buttonRight.updateImages(player, "Direction", "rightArrow.png", "rightArrow.png");
        buttonRight.displayButton(6, 1, 100, 180, 50);
        changePole(buttonRight);

        Rectangle rectangle = new Rectangle();

        rectangle.setX(100);
        rectangle.setY(0);
        rectangle.setWidth(400);
        rectangle.setHeight(50);
        rectangle.setFill(Color.CADETBLUE);

        rectangle.setArcHeight(50);
        rectangle.setArcWidth(50);

        poleDisplay = new Text(120, 30, currentPole.getName() + "   niv." + currentPole.getLevel() + "  XP : " + currentPole.getXP() + "/" + currentPole.getRequiredXP());
        poleDisplay.setTextAlignment(TextAlignment.CENTER);
        poleDisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        poleDisplay.setFill(Color.ORANGE);

        parent.getChildren().addAll(background.getImg(), rectangle, poleDisplay);
        parent.getChildren().addAll(buttonExit, buttonLeft, buttonRight);

    }

    public void changePole(ImageButton b){
        b.setOnAction((e -> {
            if (b==buttonLeft){
                indexPole = (indexPole + player.getPoles().size() - 1)%player.getPoles().size();
                currentPole = player.getPoles().get(indexPole);
                //updateEleves();

            }else{
                indexPole = (indexPole + 1)%player.getPoles().size();
                currentPole = player.getPoles().get(indexPole);
                //updateEleves();
            }
            updatePoleDisplay();
        }));
    }

    public void updateEleves(){
        for (Eleve e : player.getPoles().get(indexPole).getMember()){
            ImageButton tempButton = new ImageButton();
            tempButton.updateImages(player,"Eleve", "msnIcon.png", "msnIcon.png");
            tempButton.displayButton(1 + indexEleve%5, 1+indexEleve/5,200,50,40);

            buttonPlayers.add(tempButton);
            indexEleve = indexEleve + 1;
        }
        for (ImageButton b : buttonPlayers){
            parent.getChildren().add(b);
        }
    }

    public void updatePoleDisplay(){
        poleDisplay.setText(currentPole.getName() + "   niv." + currentPole.getLevel() + "  XP : " + currentPole.getXP() + "/" + currentPole.getRequiredXP());
    }

    public void updateButtons(){
        for (ImageButton b : buttonPlayers){
            b.setOnMouseClicked((event) -> {
                Group root = new Group();
                PopupEleve scene = new PopupEleve(root, 400, 200, player, player.getPoles().get(indexPole).getMember().get(indexEleve));
                Stage stage = new Stage();
                stage.setTitle("New Window");
                stage.setScene(scene);
                stage.show();
            });
        }
    }

}