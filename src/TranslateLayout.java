import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TranslateLayout extends Component {
    Translation translationBox;

    @Override
    public BorderPane create() {
        Button btnTranslate = new Button("Dịch");
        btnTranslate.getStyleClass().add("button-translate");
        btnTranslate.setOnAction(e -> {
            String sentence = translationBox.getSentence();
            String filePath = "datadic.txt";
            try (Scanner fileScanner = new Scanner(new File(filePath))) {
                boolean foundTranslation = false;
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    if (line.startsWith("@" + sentence)) {
                        String translation = line.substring(sentence.length() + 1);
                        translationBox.setSentence(translation);
                        StringBuilder translationBuilder = new StringBuilder(translation);
                        while (fileScanner.hasNextLine()) {
                            String nextLine = fileScanner.nextLine();
                            if (nextLine.startsWith("@")) {
                                break;
                            }
                            translationBuilder.append('\n').append(nextLine);
                        }
                        translationBox.setSentence(translationBuilder.toString());

                        foundTranslation = true;
                        break;
                    }
                }
                if (!foundTranslation) {
                    translationBox.setSentence("Không tìm thấy bản dịch cho câu này.");
                }
            } catch (FileNotFoundException ex) {
                translationBox.setSentence("Đã xảy ra lỗi khi tra cứu từ điển.");
                ex.printStackTrace();
            }
        });
        HBox btnTranslateContainer = new HBox(btnTranslate);
        btnTranslateContainer.getStyleClass().add("button-translate-container");
        btnTranslateContainer.setAlignment(Pos.CENTER);

        translationBox = new Translation();
        NavBar navBar = new NavBar();
        BorderPane translateLayout = new BorderPane();
        translateLayout.setTop(navBar.create());
        translateLayout.setCenter(translationBox.create());
        translateLayout.setBottom(btnTranslateContainer);
        return translateLayout;
    }

    @Override
    public void handleEvent() {
    }
}
