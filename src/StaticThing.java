import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;


public class StaticThing {
    private ImageView img;


    public StaticThing(double debutX,double finX,double debutY,double finY,double offset, String fileName){
        Image background = new Image(fileName);   // ouverture du fichier image
        img = new ImageView(background);    // création de l'ImageView
        img.setViewport(new Rectangle2D(debutX,debutY,finX,finY));  // définition du rectangle à afficher
        img.setX(offset);           // affichage à l'offset choisi


    }

    public ImageView getImg() {
        return img;
    }

    public static void main(String[] args) {
        System.out.println("hello world");

    }
}
