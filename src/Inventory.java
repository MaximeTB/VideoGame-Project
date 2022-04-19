import javafx.scene.Group;
import javafx.scene.Scene;

public class Inventory extends Scene {

    private StaticThing background;
    private ImageButton buttonCoffee;
    private ImageButton buttonExit;
    private Player player;

    public ImageButton getButtonExit(){
        return buttonExit;
    }


    public Inventory(Group parent, int width, int height, Player player) {
        super(parent, width, height);
        this.player = player;
        background = new StaticThing(0, 600, 0, 400, 0,"garde-manger.jpg");

        buttonCoffee = new ImageButton();
        buttonCoffee.updateImages(player, "Coffee", "cofeeIcon.png", "soldoutIcon.jpg");
        buttonCoffee.displayButton(1, 1, 110);
        buttonCoffee.hoverButton("  Cafetière", "    Bon pour le moral !    ", 25, "", "Shop");

        buttonExit = new ImageButton();
        buttonExit.setButtonExit(110);

        parent.getChildren().add(background.getImg());
        parent.getChildren().addAll(buttonCoffee, buttonExit);
    }

    public void displayBoughtItems(){

    }
}
