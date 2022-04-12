import javafx.stage.Popup;

import java.awt.*;

public class PopupFollow extends Popup {
    public PopupFollow(){
        super();
    }

    @Override public void show()
    {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();

        setX(mouseLocation.getX());
        setY(mouseLocation.getY());

        super.show();
    }
}
