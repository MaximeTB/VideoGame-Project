import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Shop extends Scene{

    private StaticThing background;
    private ImageButton buttonCoffee;


    public Shop(Group parent, int width, int height) {
        super(parent, width, height);
        background = new StaticThing(0, 600, 0, 400, 0,"C:\\Users\\rotci\\IdeaProjects\\VideoGame-Project\\src\\3FCergyBG.jpg");

        buttonCoffee = new ImageButton();
        buttonCoffee.updateImages("C:\\Users\\rotci\\IdeaProjects\\VideoGame-Project\\src\\cofeeIcon.png", "C:\\Users\\rotci\\IdeaProjects\\VideoGame-Project\\src\\soldoutIcon.jpg");
        buttonCoffee.setPrefSize(75,75);
        buttonCoffee.setLayoutX(20);
        buttonCoffee.setLayoutY(20);

        parent.getChildren().add(background.getImg());
        parent.getChildren().addAll(buttonCoffee);
    }
}
