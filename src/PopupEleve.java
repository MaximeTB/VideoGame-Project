import javafx.scene.Group;
import javafx.scene.Scene;

public class PopupEleve extends Scene {
    private Group parent;
    private Player player;
    private StaticThing background;

    public PopupEleve(Group parent, int width, int height, Player player) {
        super(parent, width, height);
        parent = parent;
        player = player;
        background = new StaticThing(0, width, 0, height, 0, "enseaBG.jpg");

        parent.getChildren().addAll(background.getImg());


    }

}
