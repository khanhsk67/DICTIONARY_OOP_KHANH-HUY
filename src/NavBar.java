import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

class NavBar extends Component{
    @Override public HBox create() {
        Button btnHome = new Button("Home", createIcon("icon/meow1.png", 50));
        btnHome.getStyleClass().add("menu-button");
        btnHome.setOnAction(e -> Main.switchScene(e));
        Button btnTranslate = new Button("Translate", createIcon("icon/meow3.png", 50));
        btnTranslate.getStyleClass().add("menu-button");
        btnTranslate.setOnAction(e -> Main.switchScene(e));
        Button btnAddWord = new Button("Add", createIcon("icon/meow4.png", 50));
        btnAddWord.getStyleClass().add("menu-button");
        btnAddWord.setOnAction(e -> Main.switchScene(e));
        Button btnGame = new Button("Game", createIcon("icon/meow5.png", 50));
        btnGame.getStyleClass().add("menu-button");
        btnGame.setOnAction(e -> Main.switchScene(e));

        HBox hboxMenu = new HBox(10, btnHome, btnTranslate, btnAddWord, btnGame);
        hboxMenu.setAlignment(Pos.BOTTOM_RIGHT);
        hboxMenu.getStyleClass().add("menu");
        return hboxMenu;
    }

    public ImageView createIcon (String url, int size) {
        Image img = new Image(url);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(size);
        imgView.setFitHeight(size);
        imgView.setPreserveRatio(true);

        return imgView;
    }
    @Override public void handleEvent(){

    }
}
