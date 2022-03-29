import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;



import java.util.ArrayList;


public class Map extends Scene{


    private StaticThing jour;
    private StaticThing nuit;
    private StaticThing qg;
    private static int etat=0;
    private Button rue = new Button("rue");
    private Button asso = new Button("asso");
    private Button admin = new Button("admin");
    private Button amphi = new Button("amphi");
    private Button td = new Button("td");
    private Button grasseMat = new Button("grasse mat");
    private ArrayList<Eleve> listedep = new ArrayList<Eleve>();
    private ArrayList<Eleve> listearr = new ArrayList<Eleve>();
    private ArrayList<Text> listText = new ArrayList<Text>();
    private ArrayList<Button> listButton = new ArrayList<Button>();




    public Map(Group parent, int etat){
        super(parent,1000,770);

        //ArrayList<String> lieux = new ArrayList<String>();

        listedep.add(new Eleve("Pierre"));
        listedep.add(new Eleve("Celia"));



        int i=0;
        for (Eleve eleve : listedep){
            Text txt = new Text(30,30+40*i,eleve.getName());
            txt.setScaleX(2.0);
            txt.setScaleY(2.0);
            listText.add(txt);
            i++;

        }


        listButton.add(rue);
        listButton.add(asso);
        listButton.add(admin);
        listButton.add(amphi);
        listButton.add(td);
        listButton.add(grasseMat);


            /*final Text source = new Text(30,30,"Pierre");
            source.setScaleX(2.0);
            source.setScaleY(2.0);

            final Text celia = new Text(30,70,"Celia");
            celia.setScaleX(2.0);
            celia.setScaleY(2.0);*/

        System.out.println(listText);


        for(Text txt : listText) {
            txt.setOnDragDetected(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    /* drag was detected, start drag-and-drop gesture*/
                    System.out.println("onDragDetected");

                    /* allow any transfer mode */
                    Dragboard db = txt.startDragAndDrop(TransferMode.ANY);

                    /* put a string on dragboard */
                    ClipboardContent content = new ClipboardContent();
                    content.putString(txt.getText());
                    db.setContent(content);

                    event.consume();
                }
            });
        }

 /*           celia.setOnDragDetected(new EventHandler <MouseEvent>() {
                public void handle(MouseEvent event) {
                    /* drag was detected, start drag-and-drop gesture*/
        System.out.println("onDragDetected");

        /* allow any transfer mode */
/*                    Dragboard db = celia.startDragAndDrop(TransferMode.ANY);

                    /* put a string on dragboard */
/*                    ClipboardContent content = new ClipboardContent();
                    content.putString(celia.getText());
                    db.setContent(content);

                    event.consume();
                }
            });
*/

        for(Button button : listButton) {
            button.setOnDragOver(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    /* data is dragged over the target */
                    System.out.println("onDragOver");

                    /* accept it only if it is  not dragged from the same node
                     * and if it has a string data */
                    if (event.getGestureSource() != button &&
                            event.getDragboard().hasString()) {
                        /* allow for both copying and moving, whatever user chooses */
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }

                    event.consume();
                }
            });
        }

        for(Button button : listButton) {
            button.setOnDragEntered(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    /* the drag-and-drop gesture entered the target */
                    System.out.println("onDragEntered");
                    /* show to the user that it is an actual gesture target */
                    if (event.getGestureSource() != button &&
                            event.getDragboard().hasString()) {
                        //button.setFill(Color.GREEN);
                    }

                    event.consume();
                }
            });
        }


        for(Button button : listButton) {
            button.setOnDragExited(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    /* mouse moved away, remove the graphical cues */
                    //button.setFill(Color.BLACK);


                    event.consume();
                }
            });
        }

        for(Button button : listButton) {
            button.setOnDragDropped(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    /* data dropped */
                    System.out.println("onDragDropped");
                    /* if there is a string data on dragboard, read it and use it */
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasString()) {
                        //target.setText(db.getString());
                        success = true;
                    }
                    /* let the source know whether the string was successfully
                     * transferred and used */
                    event.setDropCompleted(success);

                    event.consume();
                }
            });
        }


        for (Text txt : listText) {
            txt.setOnDragDone(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    /* the drag-and-drop gesture ended */
                    System.out.println("onDragDone");
                    listearr.add(listedep.get(listText.indexOf(txt)));
                    //listedep.remove(0);

                    System.out.println(listearr);
                    /* if the data was successfully moved, clear it */
                    if (event.getTransferMode() == TransferMode.MOVE) {
                        txt.setScaleX(0.5);
                        txt.setScaleY(0.5);
                        txt.setY(520+10*listearr.size());

                    }

                    event.consume();
                }
            });
        }


/*            celia.setOnDragDone(new EventHandler <DragEvent>() {
                public void handle(DragEvent event) {
                    /* the drag-and-drop gesture ended */
 /*                   System.out.println("onDragDone");
                    listearr.add("Celia");
                    //listedep.remove(0);

                    System.out.println(listearr);
                    /* if the data was successfully moved, clear it */
 /*                   if (event.getTransferMode() == TransferMode.MOVE) {
                        celia.setScaleX(0.5);
                        celia.setScaleY(0.5);
                        celia.setY(530);

                    }

                    event.consume();
                }
            });

*/


 /*           root.getChildren().add(source);
            root.getChildren().add(celia);
*/



        switch (etat){
            case 0 :
                jour = new StaticThing(0,5000,0,2000,0,"out/production/VideoGame-Project/mapJour.JPG");
                parent.getChildren().add(jour.getImg());
                parent.getChildren().add(rue);
                parent.getChildren().add(asso);
                parent.getChildren().add(admin);
                parent.getChildren().add(amphi);
                parent.getChildren().add(td);
                parent.getChildren().add(grasseMat);

                for(Text txt : listText){
                    parent.getChildren().add(txt);
                }


                rue.setLayoutX(350);
                rue.setLayoutY(500);

                asso.setLayoutX(700);
                asso.setLayoutY(200);

                admin.setLayoutX(150);
                admin.setLayoutY(530);

                amphi.setLayoutX(570);
                amphi.setLayoutY(630);

                td.setLayoutX(730);
                td.setLayoutY(560);

                grasseMat.setLayoutX(50);
                grasseMat.setLayoutY(50);
                break;
            /*case 1 :
                nuit = new StaticThing(0,2000,0,2000,0,"mapNuit.jpg");
                parent.getChildren().add(nuit.getImg());
                parent.getChildren().add(test);
                break;
            default :
                qg = new StaticThing(0,2000,0,2000,0,"mapQg.jpg");
                parent.getChildren().add(qg.getImg());
                parent.getChildren().add(test);
                break;*/
        }


    }

    public Button getTest() {
        return rue;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Button getAdmin() {
        return admin;
    }

    public Button getAmphi() {
        return amphi;
    }

    public Button getAsso() {
        return asso;
    }

    public Button getGrasseMat() {
        return grasseMat;
    }

    public Button getRue() {
        return rue;
    }

    public Button getTd() {
        return td;
    }
}
