import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Inventory extends Scene {

    private StaticThing background;
    private ImageButton buttonCoffee;
    private ImageButton buttonPancakes;
    private ImageButton buttonBalloons;
    private ImageButton buttonFireworks;
    private ImageButton buttonCamera;
    private ImageButton buttonBeer;
    private ImageButton buttonWeapon;
    private ImageButton buttonExit;
    private Player player;
    private Group parent;

    public ImageButton getButtonExit(){
        return buttonExit;
    }
    public Parent getParent(){
        return parent;
    }


    public Inventory(Group parent, int width, int height, Player player) {
        super(parent, width, height);
        this.parent = parent;
        this.player = player;
        background = new StaticThing(0, 600, 0, 400, 0,"garde-manger.jpg");

        buttonCoffee = new ImageButton();
        buttonCoffee.updateImages(player, "Coffee", "cofeeIcon.png", "soldoutIcon.jpg");
        buttonCoffee.displayButton(1, 1, 0,0,110);
        buttonCoffee.hoverButton("  Cafetière", "    Bon pour le moral !    ", 25, "  -2 Fatigue  ", "Inventory");

        buttonPancakes = new ImageButton();
        buttonPancakes.updateImages(player, "Pancakes", "pancakesIcon.png", "soldoutIcon.jpg");
        buttonPancakes.displayButton(2, 1, 0,0,110);
        buttonPancakes.hoverButton("  Crêpes", "    Bon pour le ventre !    ", 1, "  +1 Popularité  ", "Inventory");

        buttonBalloons = new ImageButton();
        buttonBalloons.updateImages(player, "Balloons", "balloonsIcon.png", "balloonsIcon.png");
        buttonBalloons.displayButton(3, 1, 0,0,110);
        buttonBalloons.hoverButton("  Jeux gonflables", "    Nous ne serons pas responsables d'éventuels bobos    ", 50, "  Uniquement en semaine de liste  ", "Inventory");

        buttonFireworks = new ImageButton();
        buttonFireworks.updateImages(player, "Balloons", "fireworksIcon.png", "fireworksIcon.png");
        buttonFireworks.displayButton(1, 2,0,0, 110);
        buttonFireworks.hoverButton("  Feux d'artifice", "    Déconseillé en intérieur...    ",  30, "  Uniquement en semaine de liste  ", "Inventory");

        buttonBeer = new ImageButton();
        buttonBeer.updateImages(player, "Beer", "beerIcon.png", "beerIcon.png");
        buttonBeer.displayButton(2, 2, 0,0,110);
        buttonBeer.hoverButton("  Bière qui tue", "    Teneur : 80%. Réservé aux plus témeraires    ", 8, "  +2 Popularité, -1 Etudes, +1 Fatigue  ", "Inventory");

        buttonWeapon = new ImageButton();
        buttonWeapon.updateImages(player, "Weapon", "weaponIcon.png", "weaponIcon.png");
        buttonWeapon.displayButton(3, 2,0,0, 110);
        buttonWeapon.hoverButton("  Euh...", "    N'achète pas ça frérot...    ", 200, "   ...   ", "Inventory");

        buttonExit = new ImageButton();
        buttonExit.setButtonExit(110);

        parent.getChildren().add(background.getImg());
        parent.getChildren().addAll(buttonCoffee, buttonExit, buttonPancakes, buttonBalloons, buttonFireworks, buttonBeer, buttonWeapon);

    }


    public void updatePopup(){
        buttonCoffee.updateHovering();
        buttonPancakes.updateHovering();
        buttonBalloons.updateHovering();
        buttonFireworks.updateHovering();
        buttonBeer.updateHovering();
        buttonWeapon.updateHovering();
    }
}
