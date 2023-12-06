import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageBox {
    static Stage stage;
    public static boolean show(boolean isWin, String word) {
        Image image;
        Label message1;
        Label message2;
        if (isWin) {
            image = new Image("image/victory.png");
            message1 = new Label("Congrats!");
            message2 = new Label("You found the word: " + word);
        } else {
            image = new Image("image/lost.gif");
            message1 = new Label("Game Over!");
            message2 = new Label("The correct word was: " + word);
        }
        message1.getStyleClass().add("message1");
        message2.getStyleClass().add("message2");

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(200);

        Button playAgainBtn = new Button("Play Again");
        playAgainBtn.getStyleClass().add("hangman-play-again-btn");
        playAgainBtn.setOnAction(e -> {
            stage.close();
        });

        VBox container = new VBox(30, imageView, message1, message2, playAgainBtn);
        container.setAlignment(Pos.CENTER);
        Scene scene = new Scene(container, 700, 500);
        scene.getStylesheets().add("style/stylesheet.css");
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        return true;
    }
}
