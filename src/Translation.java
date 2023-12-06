import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Translation extends Component {
    TextArea translateInput;
    TextArea translateOutput;
    static TextField inputLanguage;

    private String sentence;

    @Override public HBox create() {
        inputLanguage = new TextField();
        inputLanguage.setText("English");
        inputLanguage.getStyleClass().add("en-language");
        inputLanguage.setEditable(false);
        translateInput = new TextArea();
        translateInput.getStyleClass().add("translate-input");
        translateInput.setPromptText("Enter Text");
        translateInput.setWrapText(true);
        VBox input = new VBox(20, inputLanguage, translateInput);

        TextField outputLanguage = new TextField();
        outputLanguage.getStyleClass().add("vi-language");
        outputLanguage.setText("Vietnamese");
        outputLanguage.setEditable(false);
        translateOutput = new TextArea();
        translateOutput.getStyleClass().add("translate-output");
        translateOutput.setPromptText("Translation");
        translateOutput.setWrapText(true);
        translateOutput.setEditable(false);
        VBox output = new VBox(20, outputLanguage, translateOutput);

        Button btnExchange = new Button();
        btnExchange.getStyleClass().add("exchange-icon");
        btnExchange.setOnAction(e -> {
            String inputLang = inputLanguage.getText();
            String inputStyleClass = inputLanguage.getStyleClass().get(2);
            String outputLang = outputLanguage.getText();
            String outputStyleClass = outputLanguage.getStyleClass().get(2);
            inputLanguage.setText(outputLang);
            inputLanguage.getStyleClass().set(2, outputStyleClass);

            outputLanguage.setText(inputLang);
            outputLanguage.getStyleClass().set(2, inputStyleClass);
        });
        HBox iconContainer = new HBox(btnExchange);
        iconContainer.setAlignment(Pos.CENTER);


        HBox translateBox = new HBox(50, input, iconContainer, output);
        translateBox.setAlignment(Pos.TOP_CENTER);
        translateBox.getStyleClass().add("translate-box");
        return translateBox;
    }

    @Override public void handleEvent() {}

    public String getSentence() {
        return translateInput.getText();
    }

    public void setSentence(String sentence) {
        translateOutput.setText(sentence);
    }
    public ImageView createIcon (String url, int size) {
        Image img = new Image(url);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(size);
        imgView.setFitHeight(size);
        imgView.setPreserveRatio(true);

        return imgView;
    }

    public static String getInputLanguage() {
        return inputLanguage.getText();
    }
}
