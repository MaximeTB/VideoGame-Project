import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class QG extends Scene implements EventHandler<ActionEvent> {
    int width;
    int height;
    Player listeJoueurs;

    private StaticThing background;
    private StaticThing money;
    private Button buttonPoles;
    private Button buttonList;
    private Button buttonShop;
    private Button buttonInventory;
    private Button buttonExit;

    public Button getButtonShop(){
        return buttonShop;
    }

    public QG(Group parent, int width, int height){
        super(parent, width, height);
        listeJoueurs = new Player(); // mal geré (revoir méthodo de Liste.java)

        background = new StaticThing(0,353,0,345, 0, "C:\\Users\\rotci\\IdeaProjects\\VideoGame-Project\\src\\mapQg.png");

        buttonPoles = new Button();
        buttonPoles.setLayoutX(360);
        buttonPoles.setLayoutY(0);
        buttonPoles.setText("Gestion des pôles");

        buttonList = new Button();
        buttonList.setLayoutX(360);
        buttonList.setLayoutY(100);
        buttonList.setText("Liste");

        buttonShop = new Button();
        buttonShop.setLayoutX(360);
        buttonShop.setLayoutY(150);
        buttonShop.setText("Magasin");

        buttonInventory = new Button();
        buttonInventory.setLayoutX(360);
        buttonInventory.setLayoutY(200);
        buttonInventory.setText("Inventaire");

        buttonExit = new Button();
        buttonExit.setLayoutX(360);
        buttonExit.setLayoutY(250);
        buttonExit.setText("Quitter le QG");

        buttonList.setOnAction(this);
       // buttonShop.setOnAction(this);
        buttonExit.setOnAction(this);
        buttonInventory.setOnAction(this);
        buttonPoles.setOnAction(this);

        parent.getChildren().add(background.getImg());
        parent.getChildren().add(buttonList);
        parent.getChildren().add(buttonShop);
        parent.getChildren().add(buttonExit);
        parent.getChildren().add(buttonPoles);
        parent.getChildren().add(buttonInventory);

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == buttonList) {
            listeJoueurs.generateList(20);
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
