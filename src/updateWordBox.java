import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class updateWordBox {
    static Stage stage;
    static VBox addWordInformation;
    static TextField word;
    static TextField type;
    static List<String> updateInformation;
    public static void init(String curWord, String curWordType) {
        updateInformation = new ArrayList<>();
        Label lblWord = new Label("Word:");
        lblWord.getStyleClass().add("update-word-lbl");

        word = new TextField();
        word.setText(curWord);
        word.getStyleClass().add("update-word-txt");

        Label lblType = new Label("Word type:");
        lblType.getStyleClass().add("update-word-lbl");

        type = new TextField();
        type.setText(curWordType);
        type.getStyleClass().add("update-type-txt");


        Button btnSave = new Button("Save");
        btnSave.getStyleClass().add("update-word-save");
        btnSave.setOnAction(e -> {
            updateInformation.add(word.getText());
            updateInformation.add(type.getText());
            for (int i = 5; i < addWordInformation.getChildren().size() - 1; i += 2) {
                TextField tmp = (TextField) addWordInformation.getChildren().get(i);
                updateInformation.add(tmp.getText());
            }
            stage.close();
        });


        HBox save = new HBox(btnSave);
        save.setAlignment(Pos.BOTTOM_RIGHT);
        save.getStyleClass().add("update-word-save-container");

        addWordInformation = new VBox(10, lblWord, word, lblType, type, save);
        addWordInformation.getStyleClass().add("update-word-information");
    }

    public static List<String> show() {
        ScrollPane pane = new ScrollPane();
        pane.setContent(addWordInformation);
        Scene scene = new Scene(pane, 1200, 700);
        scene.getStylesheets().add("style/stylesheet.css");
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        return updateInformation;
    }
    public static void add(Node l) {
        int insIndex = addWordInformation.getChildren().size() - 1;
        addWordInformation.getChildren().add(insIndex, l);
    }

    public static void clear() {
        if (addWordInformation.getChildren().size() > 5) {
            int endIndex = addWordInformation.getChildren().size() - 1;
            addWordInformation.getChildren().remove(4, endIndex);
        }
    }
}
