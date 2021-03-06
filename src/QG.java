import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Menu QG.</br>
 * De ce menu, on peut accéder à trois composantes essentielles du jeu : la gestion de pôles, le magasin et l'inventaire.
 */
public class QG extends Scene implements EventHandler<ActionEvent> {
    int width;
    int height;
    Player player;

    private StaticThing background;
    private StaticThing money;
    private ImageButton buttonPoles;
    private ImageButton buttonShop;
    private ImageButton buttonInventory;
    private ImageButton buttonExit;

    public Button getButtonShop(){
        return buttonShop;
    }
    public Button getButtonInventory() { return buttonInventory; }
    public Button getButtonPoles() {
        return buttonPoles;
    }

    public QG(Group parent, int width, int height){
        super(parent, width, height);
        player = new Player();

        background = new StaticThing(0, 600, 0, 400, 0, "QG.jpg");

        buttonPoles = new ImageButton();
        buttonPoles.updateImages(player, "Poles", "polesIcon.jpg", "soldoutIcon.jpg");
        buttonPoles.displayButton(1, 1, 0, 0,120);
        buttonPoles.hoverButton("  Gestion des pôles ", "    Gère ta bande de bras cassés    ", 0, "", "QG");

        buttonShop = new ImageButton();
        buttonShop.updateImages(player, "Shop", "shopIcon.png", "soldoutIcon.jpg");
        buttonShop.displayButton(2, 1, 0,0, 120);
        buttonShop.hoverButton("  Magasin ", "    L'argent c'est fait pour être dépensé    ", 0, "", "QG");

        buttonInventory = new ImageButton();
        buttonInventory.updateImages(player, "Inventory", "bagIcon.png", "soldoutIcon.jpg");
        buttonInventory.displayButton(3, 1,0,0, 120);
        buttonInventory.hoverButton("  Inventaire ", "    Consulte tes effets personnels    ", 0, "", "QG");


        buttonExit = new ImageButton();
        buttonExit.setButtonExit(110);

        buttonExit.setOnAction(this);
        buttonPoles.setOnAction(this);

        parent.getChildren().add(background.getImg());
        parent.getChildren().addAll(buttonShop, buttonPoles, buttonInventory, buttonExit);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == buttonPoles) {
            player.generateList(20);
        }else if (actionEvent.getSource() == buttonShop){
            // image du magasin
        }else if (actionEvent.getSource() == buttonExit){
            handleCloseButtonAction(actionEvent);
        }
    }

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }


}
