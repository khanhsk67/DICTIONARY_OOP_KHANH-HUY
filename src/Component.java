import javafx.scene.*;
import javafx.stage.Stage;

abstract class Component {
    abstract public Node create();
    abstract public void handleEvent();
}
