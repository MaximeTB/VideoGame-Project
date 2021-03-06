import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.text.Text;



import java.util.ArrayList;


public class Map extends Scene{


    private StaticThing jour;
    private int numeroList=10;
    private Button rue = new Button("rue");
    private Button asso = new Button("asso");
    private Button admin = new Button("admin");
    private Button amphi = new Button("amphi");
    private Button td = new Button("td");
    private Button grasseMat = new Button("grasse mat");
    private ArrayList<Eleve> listedepJour = new ArrayList<Eleve>();
    private ArrayList<Text> listTextJour = new ArrayList<Text>();
    private ArrayList<Button> listButton = new ArrayList<Button>();
    private ArrayList<Eleve> listeRue = new ArrayList<Eleve>();
    private ArrayList<Eleve> listeAsso = new ArrayList<Eleve>();
    private ArrayList<Eleve> listeAdmin = new ArrayList<Eleve>();
    private ArrayList<Eleve> listeAmphi = new ArrayList<Eleve>();
    private ArrayList<Eleve> listeTd = new ArrayList<Eleve>();
    private ArrayList<Eleve> listeGrasseMat = new ArrayList<Eleve>();

    private Button buttonNuit = new Button("nuit");




    public Map(Group parent, ArrayList<Eleve> listeEleve){
        super(parent,1000,770);

        this.listedepJour=listeEleve;

        int i=0;
        for (Eleve eleve : listedepJour){
            Text txt = new Text(30,30+20*i,eleve.getName());
            txt.setScaleX(1.0);
            txt.setScaleY(1.0);

            listTextJour.add(txt);
            i++;

        }


        listButton.add(rue);
        listButton.add(asso);
        listButton.add(admin);
        listButton.add(amphi);
        listButton.add(td);
        listButton.add(grasseMat);


        System.out.println("taille"+listTextJour.size());


        for(Text txt : listTextJour) {
            txt.setOnDragDetected(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    /* drag was detected, start drag-and-drop gesture*/
                    System.out.println("onDragDetected");
                    System.out.println("Txt "+txt +"\n" + listTextJour.size() );

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
                            case "rue":
                                numeroList = 0;
                                break;

                            case "asso":
                                numeroList = 1;
                                break;

                            case "admin":
                                numeroList = 2;
                                break;

                            case "amphi":
                                numeroList = 3;
                                break;

                            case "td":
                                numeroList = 4;
                                break;

                            case "grasse mat":
                                numeroList = 5;
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


        for (Text txt : listTextJour) {
            txt.setOnDragDone(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    /* the drag-and-drop gesture ended */
                    System.out.println("onDragDone");
                    System.out.println(listTextJour.indexOf(txt));

                    System.out.println("no " + numeroList);

                    switch (numeroList){
                        case 0 :
                            if (listTextJour.indexOf(txt)!=-1) {
                                listeRue.add(listedepJour.get(listTextJour.indexOf(txt)));

                                if (event.getTransferMode() == TransferMode.MOVE) {
                                    txt.setScaleX(0.5);
                                    txt.setScaleY(0.5);
                                    txt.setX(rue.getLayoutX());
                                    txt.setY(rue.getLayoutY() + 30+ 10 * listeRue.size());
                                    listedepJour.remove(listedepJour.get(listTextJour.indexOf(txt)));
                                    listTextJour.remove(txt);

                                }
                            }
                        numeroList=10;
                        break;

                        case 1 :
                            if (listTextJour.indexOf(txt)!=-1) {
                                listeAsso.add(listedepJour.get(listTextJour.indexOf(txt)));

                                if (event.getTransferMode() == TransferMode.MOVE) {
                                    txt.setScaleX(0.5);
                                    txt.setScaleY(0.5);
                                    txt.setX(asso.getLayoutX());
                                    txt.setY(asso.getLayoutY() + 30+ 10 * listeAsso.size());
                                    listedepJour.remove(listedepJour.get(listTextJour.indexOf(txt)));
                                    listTextJour.remove(txt);

                                }
                            }
                            numeroList=10;
                            break;

                        case 2 :
                            if (listTextJour.indexOf(txt)!=-1) {
                                listeAdmin.add(listedepJour.get(listTextJour.indexOf(txt)));

                                if (event.getTransferMode() == TransferMode.MOVE) {
                                    txt.setScaleX(0.5);
                                    txt.setScaleY(0.5);
                                    txt.setX(admin.getLayoutX());
                                    txt.setY(admin.getLayoutY() + 30+10 * listeAdmin.size());
                                    listedepJour.remove(listedepJour.get(listTextJour.indexOf(txt)));
                                    listTextJour.remove(txt);

                                }
                            }
                            numeroList=10;
                            break;

                        case 3 :
                            if (listTextJour.indexOf(txt)!=-1) {
                                listeAmphi.add(listedepJour.get(listTextJour.indexOf(txt)));

                                if (event.getTransferMode() == TransferMode.MOVE) {
                                    txt.setScaleX(0.5);
                                    txt.setScaleY(0.5);
                                    txt.setX(amphi.getLayoutX());
                                    txt.setY(amphi.getLayoutY() + 30+ 10 * listeAmphi.size());
                                    listedepJour.remove(listedepJour.get(listTextJour.indexOf(txt)));
                                    listTextJour.remove(txt);

                                }
                            }
                            numeroList=10;
                            break;

                        case 4 :
                            if (listTextJour.indexOf(txt)!=-1) {
                                listeTd.add(listedepJour.get(listTextJour.indexOf(txt)));

                                if (event.getTransferMode() == TransferMode.MOVE) {
                                    txt.setScaleX(0.5);
                                    txt.setScaleY(0.5);
                                    txt.setX(td.getLayoutX());
                                    txt.setY(td.getLayoutY() + 30+  10 * listeTd.size());
                                    listedepJour.remove(listedepJour.get(listTextJour.indexOf(txt)));
                                    listTextJour.remove(txt);

                                }
                            }
                            numeroList=10;
                            break;

                        case 5 :
                            if (listTextJour.indexOf(txt)!=-1) {
                                listeGrasseMat.add(listedepJour.get(listTextJour.indexOf(txt)));

                                if (event.getTransferMode() == TransferMode.MOVE) {
                                    txt.setScaleX(0.5);
                                    txt.setScaleY(0.5);
                                    txt.setX(grasseMat.getLayoutX());
                                    txt.setY(grasseMat.getLayoutY() + 30+ 10 * listeGrasseMat.size());
                                    listedepJour.remove(listedepJour.get(listTextJour.indexOf(txt)));
                                    listTextJour.remove(txt);

                                }
                            }
                            numeroList=10;
                            break;

                        default:
                            break;


                    }

                    System.out.println("txt" + listTextJour.size());


                    System.out.println("rue : "+listeRue);
                    System.out.println("asso : "+listeAsso);
                    System.out.println("admin : "+listeAdmin);
                    System.out.println("amphi : "+listeAmphi);
                    System.out.println("td : "+listeTd);
                    System.out.println("gm : "+listeGrasseMat);


                    event.consume();
                }
            });
        }





                jour = new StaticThing(0,5000,0,2000,0,"mapJour.JPG");
                parent.getChildren().add(jour.getImg());
                parent.getChildren().add(rue);
                parent.getChildren().add(asso);
                parent.getChildren().add(admin);
                parent.getChildren().add(amphi);
                parent.getChildren().add(td);
                parent.getChildren().add(grasseMat);

                parent.getChildren().add(buttonNuit);

                for(Text txt : listTextJour){
                    parent.getChildren().add(txt);
                }


                rue.setLayoutX(350);
                rue.setLayoutY(500);
                rue.setStyle("-fx-background-color: #FF6060; ");

                asso.setLayoutX(700);
                asso.setLayoutY(200);
                asso.setStyle("-fx-background-color: #FF6060; ");

                admin.setLayoutX(150);
                admin.setLayoutY(530);
        admin.setStyle("-fx-background-color: #FF6060; ");

                amphi.setLayoutX(570);
                amphi.setLayoutY(630);
        amphi.setStyle("-fx-background-color: #FF6060; ");

                td.setLayoutX(730);
                td.setLayoutY(560);
        td.setStyle("-fx-background-color: #FF6060; ");

                grasseMat.setLayoutX(900);
                grasseMat.setLayoutY(600);
        grasseMat.setStyle("-fx-background-color: #FF6060; ");


        buttonNuit.setLayoutX(965);




    }


    public Button getButtonNuit() {
        return buttonNuit;
    }
}
