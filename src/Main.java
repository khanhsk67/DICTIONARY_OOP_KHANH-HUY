import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.*;
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    static Home home = new Home();
    static TranslateLayout translateLayout = new TranslateLayout();
    static addWordLayout addWordLayout = new addWordLayout();
    static game gameLayout = new game();
    static Scene translateScene = new Scene(translateLayout.create(), 1200, 700);
    static Scene homeScene = new Scene(home.create(), 1200, 700);

    static Scene addWordScene = new Scene(addWordLayout.create(), 1200, 700);
    static Scene gameScene = new Scene(gameLayout.create(), 1200, 700);
    static Stage stage;
    public static void switchScene(ActionEvent e) {
        Button b = (Button) e.getSource();
        if (b.getText().equals("Translate")) {
            stage.setScene(translateScene);
        } else if (b.getText().equals("Home")) {
            stage.setScene(homeScene);
        } else if (b.getText().equals("Add")) {
            stage.setScene(addWordScene);
        } else if (b.getText().equals("Game")) {
            stage.setScene(gameScene);
        }
    }
    @Override public void start(Stage primaryStage) {
        stage = primaryStage;
        translateScene.getStylesheets().add("style/stylesheet.css");
        homeScene.getStylesheets().add("style/stylesheet.css");
        addWordScene.getStylesheets().add("style/stylesheet.css");
        gameScene.getStylesheets().add("style/stylesheet.css");
        stage.setTitle("Dictionary");
        stage.setScene(gameScene);
        stage.show();
    }

}