import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.ScrollPane;
public class mainLayout extends Component {
    @Override public BorderPane create() {
        NavBar navBar = new NavBar();
        wordInformation wordInformation = new wordInformation();

        ScrollPane wordContent = new ScrollPane();
        wordContent.setContent(wordInformation.create());
        wordContent.getStyleClass().add("word-content");

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(navBar.create());
        mainLayout.setCenter(wordContent);

        return mainLayout;
    }

    @Override public void handleEvent() {}
}
