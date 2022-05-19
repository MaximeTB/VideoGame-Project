import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.awt.*;

public class Shop extends Scene {

    private StaticThing background;
    private StaticThing money;
    private ImageButton buttonCoffee;
    private ImageButton buttonPancakes;
    private ImageButton buttonBalloons;
    private ImageButton buttonFireworks;
    private ImageButton buttonCamera;
    private ImageButton buttonBeer;
    private ImageButton buttonWeapon;
    private ImageButton buttonExit;
    private Player player;
    private Text moneyDisplay;
    private Inventory inventory;

    public ImageButton getButtonExit() {
        return buttonExit;
    }

    public Inventory getInventory(){ return inventory;}


    /**
     * Constructeur. Responsable de l'affichage du menu.
     * @param parent Utilise en parametre du constructeur de la classe Scene.
     * @param width Utilise en parametre du constructeur de la classe Scene.
     * @param height Utilise en parametre du constructeur de la classe Scene.
     * @param player Joueur de la partie en cour. Permet d'avoir acces a certain attributs.
     * @param inventory Permet d'avoir acces a l'inventaire du Joueur.
     */
    public Shop(Group parent, int width, int height, Player player, Inventory inventory) {
        super(parent, width, height);
        this.player = player;
        this.inventory = inventory;
        background = new StaticThing(0, 600, 0, 400, 0, "3FCergyBG.jpg");
        money = new StaticThing(0, 256, 0, 256, 0, "walletIcon.png");
        money.getImg().setFitHeight(50);
        money.getImg().setFitWidth(50);
        money.getImg().setX(525);
        money.getImg().setY(325);

        buttonCoffee = new ImageButton();
        buttonCoffee.updateImages(player, "Coffee", "cofeeIcon.png", "soldoutIcon.jpg");
        buttonCoffee.displayButton(1, 1, 0, 0, 110);
        buttonCoffee.hoverButton("  Cafetiere", "    Bon pour le moral !    ", 25, "", "Shop");
        buyButton(buttonCoffee, buttonCoffee.getPrice());

        buttonPancakes = new ImageButton();
        buttonPancakes.updateImages(player, "Pancakes", "pancakesIcon.png", "soldoutIcon.jpg");
        buttonPancakes.displayButton(2, 1, 0, 0, 110);
        buttonPancakes.hoverButton("  Crêpes", "    Bon pour le ventre !    ", 1, "", "Shop");
        buyButton(buttonPancakes, buttonPancakes.getPrice());

        buttonBalloons = new ImageButton();
        buttonBalloons.updateImages(player, "Balloons", "balloonsIcon.png", "balloonsIcon.png");
        buttonBalloons.displayButton(3, 1, 0, 0, 110);
        buttonBalloons.hoverButton("  Jeux gonflables", "    Nous ne serons pas responsables d'eventuels bobos    ", 50, "", "Shop");
        buyButton(buttonBalloons, buttonBalloons.getPrice());

        buttonFireworks = new ImageButton();
        buttonFireworks.updateImages(player, "Balloons", "fireworksIcon.png", "fireworksIcon.png");
        buttonFireworks.displayButton(1, 2, 0, 0, 110);
        buttonFireworks.hoverButton("  Feux d'artifice", "    Deconseille en interieur...    ",  30, "", "Shop");
        buyButton(buttonFireworks, buttonFireworks.getPrice());

        buttonBeer = new ImageButton();
        buttonBeer.updateImages(player, "Beer", "beerIcon.png", "beerIcon.png");
        buttonBeer.displayButton(2, 2, 0, 0, 110);
        buttonBeer.hoverButton("  Biere qui tue", "    Teneur : 80%. Reserve aux plus temeraires    ", 8, "", "Shop");
        buyButton(buttonBeer, buttonBeer.getPrice());

        buttonWeapon = new ImageButton();
        buttonWeapon.updateImages(player, "Weapon", "weaponIcon.png", "weaponIcon.png");
        buttonWeapon.displayButton(3, 2, 0, 0, 110);
        buttonWeapon.hoverButton("  Euh...", "    N'achete pas ça frerot...    ", 200, "", "Shop");
        buyButton(buttonWeapon, buttonWeapon.getPrice());


        buttonExit = new ImageButton();
        buttonExit.setButtonExit(110);

        Rectangle rectangle = new Rectangle();

        rectangle.setX(380);
        rectangle.setY(330);
        rectangle.setWidth(200);
        rectangle.setHeight(40);
        rectangle.setFill(Color.CADETBLUE);

        rectangle.setArcHeight(50);
        rectangle.setArcWidth(50);

        moneyDisplay = new Text(400, 350, "Argent disponible :" + player.getArgent());
        moneyDisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 9));
        moneyDisplay.setFill(Color.LIGHTGOLDENRODYELLOW);
        parent.getChildren().addAll(background.getImg(), rectangle, money.getImg(), moneyDisplay);
        parent.getChildren().addAll(buttonCoffee, buttonExit, buttonPancakes, buttonBalloons, buttonFireworks, buttonBeer, buttonWeapon);

    }

    /**
     * Decremente le portefeuille du joueur lors d'un achat. Met egalement a jour l'inventaire courant.
     * @param button Fait correspondre l'action a un bouton predefini.
     * @param price Prix de l'objet en vente.
     */
    public void buyButton(ImageButton button, Integer price) {
        button.setOnAction((e -> {
            if (player.getArgent() >= price) {
                player.setArgent(player.getArgent() -  price); // mettre une fonction depense(int) dans la classe player
                player.getInventory().setAttribute(button.getLabel(), player.getInventory().getAttribute(button.getLabel()) + 1);
                System.out.println(player.getInventory().getAttribute(button.getLabel()));
                System.out.println("Argent : " + player.getArgent());
            } else {
                System.out.println("Achat impossible - Gagne des sous avant...");
            }
            updateMoney();
            button.getPopup().hide();
            updatePopup();
            this.getInventory().updatePopup();
            // player.getList(). ... // Activates the bought up item's effect on the player's list.
        }));
    }

    // Updates

    /**
     * Met a jour la fenêtre popup de description. Cette fonction est appelee lors d'un achat.
     */
    public void updatePopup(){
        buttonCoffee.updateHovering();
        buttonPancakes.updateHovering();
        buttonBalloons.updateHovering();
        buttonFireworks.updateHovering();
        buttonBeer.updateHovering();
        buttonWeapon.updateHovering();
    }

    /**
     * Met a jour l'affichage du portefeuille.
     */
    public void updateMoney(){
        moneyDisplay.setText("Argent disponible :" + player.getArgent());
    }

    /*
    public Integer salesEventOn(){
        if (player.getCurrentEvent().getName() == "Soldes") return 0;
        else return 1;
    }
    */

    /**
     * Idee laissee a l'abandon. Initialement, des evenements tels que des soldes interviendraient,
     * et auraient pour effet par exemple de modifier les prix du magasin.
     * @return coefficient multiplicateur
     */
    public double skillMETROon(){
        for (Eleve e : player.getListeEleve()){
            for (Skills s : e.getSkillsList()){
                if (s.getName() == "Carte Metro") return 0.5;
            }
        }
        return 1;
    }

}