/*import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class QG extends Scene implements EventHandler<ActionEvent> {
    int width;
    int height;
    Player listeJoueurs;

    private StaticThing background;
    private StaticThing money;
    private Button buttonList;
    private Button buttonShop;
    private Button buttonExit;

    public QG(Group parent, int width, int height){
        super(parent, width, height);
        listeJoueurs = new Player(); // mal geré (revoir méthodo de Liste.java)

        background = new StaticThing(0,353,0,345, 0, "C:\\Users\\rotci\\IdeaProjects\\VideoGame-Project\\src\\mapQg.png");
        buttonList = new Button();
        buttonList.setLayoutX(360);
        buttonList.setLayoutY(0);
        buttonList.setText("Liste");

        buttonShop = new Button();
        buttonShop.setLayoutX(360);
        buttonShop.setLayoutY(100);
        buttonShop.setText("Magasin");

        buttonExit = new Button();
        buttonExit.setLayoutX(360);
        buttonExit.setLayoutY(200);
        buttonExit.setText("Quitter le QG");

        buttonList.setOnAction(this);
        buttonShop.setOnAction(this);
        buttonExit.setOnAction(this);

        parent.getChildren().add(background.getImg());
        parent.getChildren().add(buttonList);
        parent.getChildren().add(buttonShop);
        parent.getChildren().add(buttonExit);

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == buttonList) {
            listeJoueurs.generateList(20);
        }else if (actionEvent.getSource() == buttonShop){
            // image du magasin
        }
    }

    void liste(){

    }

    void magasin(){
        System.out.println("Que veux-tu acheter poto ?");
        System.out.println("1. Cafetière 2. Cuisine 3. Jeux gonflables 4. Louer une salle 5. Feux d'artifice 6. Inviter une célebrité");
    }

}
*/