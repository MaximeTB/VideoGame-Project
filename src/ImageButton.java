import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ImageButton extends Button {

    private String label;                   // gives name of the item to buy
    private Player player;                  // allows access to relevant attributes
    private DropShadow shadow;
    private PopupFollow popup;
    private String title;
    private String description;
    private Integer price;
    private String effect;
    private String state;
    private ImageView iv; // image view
    private Integer indexX;
    private Integer indexY;
    private Integer prefSize;


    public String getLabel(){
        return this.label;
    }
    public Integer getPrice(){
        return this.price;
    }
    public PopupFollow getPopup(){
        return this.popup;
    }

    public Integer getIndexX() { return this.indexX; }
    public Integer getIndexY() { return this.indexY; }
    public Integer getPrefSize() { return this.prefSize; }

    public void updateImages(final Player player, final String label, final String StringSelected, final String StringUnselected) {
        this.label = label;
        this.player = player;
        Image selected = new Image(StringSelected);
        final Image unselected = new Image(StringUnselected);
        this.iv = new ImageView(selected);
        this.getChildren().add(iv);

        iv.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                iv.setImage(unselected);
            }
        });
        iv.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                iv.setImage(selected);
            }
        });

        super.setGraphic(iv);
    }

    public void displayButton(int indexX, int indexY, int offsetX, int offsetY, int prefSize){
        this.indexX = indexX;
        this.indexY = indexY;
        this.prefSize = prefSize;
        int stepX = (int) prefSize / 2;
        int stepY = (int) prefSize /4;
        this.setPrefSize(prefSize, prefSize);
        //this.iv.setFitHeight(prefSize);
        //this.iv.setFitWidth(prefSize);
        this.setLayoutX(offsetX + stepX*indexX + prefSize*(indexX-1));
        this.setLayoutY(offsetY + stepY*indexY + prefSize*(indexY-1));

    }

    public void setButtonExit(int stepSize){
        this.updateImages(player, "Exit", "backIcon.png", "backIcon.png");
        this.hoverButton("Retour au bercail", "On se casse !", 0, "", "");
        int step = (int) stepSize / 4;
        this.setPrefSize(75, 50);
        this.setLayoutX(step);
        this.setLayoutY(400-step-50);
    }

    public void hoverButton(String title, String description, Integer price, String effect, String state){
        this.shadow = new DropShadow();
        this.popup = new PopupFollow();
        this.title = title;
        this.description = description;
        this.price = price;
        this.effect = effect;
        this.state = state;

        Label label = new Label();
        StringBuilder msg = new StringBuilder();
        msg.append(title);
        msg.append("\n");
        msg.append(description);
        msg.append("\n");
        switch(state){
            case "Shop" :
                msg.append(" Prix : " + price.toString() + "€ ");
                msg.append("\n");
                msg.append("  Inventaire : " + player.getInventory().getAttribute(this.getLabel()));
                break;
            case "Inventory" :
                msg.append(effect);
                msg.append("\n");
                msg.append("  Inventaire : " + player.getInventory().getAttribute(this.getLabel()));
                break;
        }
        msg.append("\n");
        label.setText(msg.toString());
        label.setStyle(" -fx-background-color: white;");

        popup.getContent().add(label);

        this.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> {
                    this.setEffect(shadow);
                    popup.show((Stage)((Node) e.getSource()).getScene().getWindow());
                });

        this.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> {
                    this.setEffect(null);
                    popup.hide();
                });
    }

    // updateHovering - calls again hoverButton to update the inventory display in shop
    public void updateHovering(){
        hoverButton(this.title, this.description, this.price, this.effect, this.state);
    }

}