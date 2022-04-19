import javafx.scene.control.Button;

import java.util.ArrayList;

public class PlayerInventory {

    private ArrayList<Achetable> Inventory= new ArrayList<Achetable>();
    private Integer coffee;
    private Integer pancakes;
    private Integer balloons;
    private Integer fireworks;
    private Integer camera;
    private Integer beer;
    private Integer weapon;



    public PlayerInventory(){
        this.coffee = 0;
        this.pancakes = 0;
        this.balloons = 0;
        this.fireworks = 0;
        this.camera = 0;
        this.beer = 0;
        this.weapon = 0;
    }

    public void setAttribute(String s, Integer a){
        switch(s){
            case "Coffee" :
                coffee = a;
                break;
            case "Pancakes" :
                pancakes = a;
                break;
            case "Balloons" :
                balloons = a;
                break;
            case "Fireworks" :
                fireworks = a;
                break;
            case "Camera" :
                camera = a;
                break;
            case "Beer" :
                beer = a;
                break;
            case "Weapon" :
                weapon = a;
                break;
        }
    }

    public Integer getAttribute(String s){
        switch(s){
            case "Coffee" :
                return coffee;
            case "Pancakes" :
                return pancakes;
            case "Balloons" :
                return balloons;
            case "Fireworks" :
                return fireworks;
            case "Camera" :
                return camera;
            case "Beer" :
                return beer;
            case "Weapon" :
                return weapon;
        }
        return 0;
    }


    public ArrayList<Achetable> getInventory() {
        return Inventory;
    }
}
