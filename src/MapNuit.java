import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;


public class MapNuit extends Scene {
    private StaticThing nuit;
    private int numeroList=10;
    private Button soiree = new Button("soirée");
    private Button dodo = new Button("dodo");
    private Button argent = new Button("argent");
    private Button qg = new Button("QG");
    private Button centreDoc = new Button("Centre doc");
    private ArrayList<Eleve> listedep = new ArrayList<Eleve>();

    private ArrayList<Text> listText = new ArrayList<Text>();
    private ArrayList<Button> listButton = new ArrayList<Button>();

    private ArrayList<Eleve> listeSoiree = new ArrayList<Eleve>();
    private ArrayList<Eleve> listeDodo = new ArrayList<Eleve>();
    private ArrayList<Eleve> listeArgent = new ArrayList<Eleve>();
    private ArrayList<Eleve> listeQg = new ArrayList<Eleve>();
    private ArrayList<Eleve> listeCentreDoc = new ArrayList<Eleve>();

    private Button buttonJour = new Button("jour");



    public MapNuit(Group parent, ArrayList<Eleve> listeEleve) {
        super(parent, 1000, 770);

        this.listedep=listeEleve;

        Random alea = new Random();

        int i=0;
        for (Eleve eleve : listedep){
            Text txt = new Text(30,30+40*i,eleve.getName());
            txt.setScaleX(2.0);
            txt.setScaleY(2.0);
            listText.add(txt);
            txt.setFill(Color.WHITE);
            i++;

        }

        listButton.add(soiree);
        listButton.add(dodo);
        listButton.add(argent);
        listButton.add(qg);
        listButton.add(centreDoc);


        for(Text txt : listText) {
            txt.setOnDragDetected(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    /* drag was detected, start drag-and-drop gesture*/
                    System.out.println("onDragDetected");
                    System.out.println("Txt "+txt +"\n" + listText );

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
                            button.setStyle("-fx-background-color: #60FF60; ");
                    }

                    event.consume();
                }
            });
        }

        for(Button button : listButton) {
            button.setOnDragExited(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    /* mouse moved away, remove the graphical cues */
                    button.setStyle("-fx-background-color: #FF6060; ");


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

                        switch (button.getText()) {
                            case "soirée":
                                numeroList = 0;
                                break;

                            case "dodo":
                                numeroList = 1;
                                break;

                            case "argent":
                                numeroList = 2;
                                break;

                            case "QG":
                                numeroList = 3;
                                break;

                            case "Centre doc":
                                numeroList = 4;
                                break;

                            default: numeroList=10;
                                break;

                        }

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
                    System.out.println(listText.indexOf(txt));

                    System.out.println(numeroList);

                    switch (numeroList){
                        case 0 :
                            if (listText.indexOf(txt)!=-1) {
                                listeSoiree.add(listedep.get(listText.indexOf(txt)));

                                if (event.getTransferMode() == TransferMode.MOVE) {
                                    txt.setScaleX(0.5);
                                    txt.setScaleY(0.5);
                                    txt.setX(soiree.getLayoutX());
                                    txt.setY(soiree.getLayoutY() + 30 + 10 * listeSoiree.size());
                                    listedep.remove(listedep.get(listText.indexOf(txt)));
                                    listText.remove(txt);

                                }
                            }
                            numeroList=10;
                            break;

                        case 1 :
                            if (listText.indexOf(txt)!=-1) {
                                listeDodo.add(listedep.get(listText.indexOf(txt)));

                                if (event.getTransferMode() == TransferMode.MOVE) {
                                    txt.setScaleX(0.5);
                                    txt.setScaleY(0.5);
                                    txt.setX(dodo.getLayoutX());
                                    txt.setY(dodo.getLayoutY() + 30 + 10 * listeDodo.size());
                                    listedep.remove(listedep.get(listText.indexOf(txt)));
                                    listText.remove(txt);

                                }
                            }
                            numeroList=10;
                            break;

                        case 2 :
                            if (listText.indexOf(txt)!=-1) {
                                listeArgent.add(listedep.get(listText.indexOf(txt)));

                                if (event.getTransferMode() == TransferMode.MOVE) {
                                    txt.setScaleX(0.5);
                                    txt.setScaleY(0.5);
                                    txt.setX(argent.getLayoutX());
                                    txt.setY(argent.getLayoutY() + 30 + 10 * listeArgent.size());
                                    listedep.remove(listedep.get(listText.indexOf(txt)));
                                    listText.remove(txt);

                                }
                            }
                            numeroList=10;
                            break;

                        case 3 :
                            if (listText.indexOf(txt)!=-1) {
                                listeQg.add(listedep.get(listText.indexOf(txt)));

                                if (event.getTransferMode() == TransferMode.MOVE) {
                                    txt.setScaleX(0.5);
                                    txt.setScaleY(0.5);
                                    txt.setX(qg.getLayoutX());
                                    txt.setY(qg.getLayoutY() + 30 + 10 * listeQg.size());
                                    listedep.remove(listedep.get(listText.indexOf(txt)));
                                    listText.remove(txt);

                                }
                            }
                            numeroList=10;
                            break;

                        case 4 :
                            if (listText.indexOf(txt)!=-1) {
                                listeCentreDoc.add(listedep.get(listText.indexOf(txt)));

                                if (event.getTransferMode() == TransferMode.MOVE) {
                                    txt.setScaleX(0.5);
                                    txt.setScaleY(0.5);
                                    txt.setX(centreDoc.getLayoutX());
                                    txt.setY(centreDoc.getLayoutY() + 30 + 10 * listeCentreDoc.size());
                                    listedep.remove(listedep.get(listText.indexOf(txt)));
                                    listText.remove(txt);

                                }
                            }
                            numeroList=10;
                            break;


                        default:
                            break;


                    }

                    System.out.println("txt" + listText);

                    //listearr.add(listedep.get(listText.indexOf(txt)));
                    //listedep.remove(0);

                    System.out.println("soirée : "+listeSoiree);
                    System.out.println("dodo : "+listeDodo);
                    System.out.println("argent : "+listeArgent);
                    System.out.println("QG : "+listeQg);
                    System.out.println("Centre doc : "+listeCentreDoc);


                    event.consume();
                }
            });
        }







        nuit = new StaticThing(0,5000,0,2000,0,"mapNuit.JPG");
        parent.getChildren().add(nuit.getImg());

        parent.getChildren().add(soiree);
        parent.getChildren().add(dodo);
        parent.getChildren().add(argent);
        parent.getChildren().add(qg);
        parent.getChildren().add(centreDoc);
        parent.getChildren().add(buttonJour);


        for(Text txt : listText){
            parent.getChildren().add(txt);
        }

        soiree.setLayoutX(150+alea.nextInt(700));
        soiree.setLayoutY(150+alea.nextInt(470));
        soiree.setStyle("-fx-background-color: #FF6060; ");

        dodo.setLayoutX(900);
        dodo.setLayoutY(600);
        dodo.setStyle("-fx-background-color: #FF6060; ");

        argent.setLayoutX(700);
        argent.setLayoutY(500);
        argent.setStyle("-fx-background-color: #FF6060; ");

        qg.setLayoutX(425);
        qg.setLayoutY(325);
        qg.setStyle("-fx-background-color: #FF6060; ");

        centreDoc.setLayoutX(425);
        centreDoc.setLayoutY(235);
        centreDoc.setStyle("-fx-background-color: #FF6060; ");

        buttonJour.setLayoutX(965);



    }



    public ArrayList<Eleve> getListeCentreDoc() {
        return listeCentreDoc;
    }

    public ArrayList<Eleve> getListeArgent() {
        return listeArgent;
    }

    public ArrayList<Eleve> getListeDodo() {
        return listeDodo;
    }

    public ArrayList<Eleve> getListeQg() {
        return listeQg;
    }

    public ArrayList<Eleve> getListeSoiree() {
        return listeSoiree;
    }

    public Button getButtonJour() {
        return buttonJour;
    }
}
