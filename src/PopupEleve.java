import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;

public class PopupEleve extends Scene {
    private Group parent;
    private Player player;
    private Eleve eleve;
    private StaticThing background;
    private ImageButton buttonLeft;
    private ImageButton buttonRight;
    private ImageButton buttonExit;

    /**
     * Cree une fenetre popup dependante d'un eleve du menu de gestion de poles.
     * @param parent
     * @param width
     * @param height
     * @param player
     * @param eleve
     */
    public PopupEleve(Group parent, int width, int height, Player player, Eleve eleve) {
        super(parent, width, height);
        parent = parent;
        player = player;
        eleve = eleve;
        background = new StaticThing(0, width, 0, height, 0, "enseaBG.jpg");

        parent.getChildren().addAll(background.getImg());
        buttonLeft = new ImageButton();
        buttonLeft.updateImages(player, "Direction", "leftArrow.png", "leftArrow.png");
        buttonLeft.displayButton(1, 1, 0, 180, 50);
        changePole(buttonLeft, player, eleve);

        buttonRight = new ImageButton();

        buttonExit = new ImageButton();
        buttonExit.setButtonExit(110);
        buttonExit.quitWindow();

        parent.getChildren().addAll(background.getImg(), buttonLeft, buttonRight, buttonExit);


    }

    /**
     * Actionné par l'appui d'un bouton (flèche gauche ou droite). Permet de changer le pôle d'appartenance
     * d'un élève.
     * @param b
     * @param p
     * @param eleve
     */
    public void changePole(ImageButton b, Player p, Eleve eleve) {

        b.setOnAction((e -> {
            int index = 0;
            for (Pole pole : p.getPoles()) {
                if (pole == eleve.getPole()) {
                    index = p.getPoles().indexOf(pole);
                }
            }

            if (b == buttonRight) {
                eleve.setPole(p.getPoles().get((index + 1) % 5));
            } else {
                eleve.setPole(p.getPoles().get((index + player.getPoles().size() - 1) % 5));
            }
            updatePoleDisplay();
        }));
    }


    public void updatePoleDisplay() {

    }
}
