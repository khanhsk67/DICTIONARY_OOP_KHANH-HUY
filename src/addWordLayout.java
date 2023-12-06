import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.sql.*;



public class addWordLayout extends Component{
    Connection myConn = null;
    Statement myStmt = null;
    ResultSet myRs = null;

    @Override public VBox create() {
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dictionary2", "student", "student");
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        Label lblWord = new Label("Word:");
        lblWord.getStyleClass().add("add-word-lbl");

        TextField word = new TextField();
        word.setPromptText("Enter word:");
        word.getStyleClass().add("add-word-txt");

        Label lblType = new Label("Word type:");
        lblType.getStyleClass().add("add-word-lbl");

        TextField type = new TextField();
        type.setPromptText("Enter word type:");
        type.getStyleClass().add("add-type-txt");

        Label lblMeaning = new Label("Meaning:");
        lblMeaning.getStyleClass().add("add-word-lbl");

        TextField meaning = new TextField();
        meaning.setPromptText("Enter meaning:");
        meaning.getStyleClass().add("add-meaning-txt");

        Button btnSave = new Button("Save");
        btnSave.getStyleClass().add("add-word-save");
        btnSave.setOnAction(e -> {
            try {
                String insWord = word.getText();
                String insType = type.getText();
                String insMeaning = meaning.getText();
                if (!insWord.equals("") && !insType.equals("") && !insMeaning.equals("")) {
                    SearchLayout.updateWordList(insWord);
                    insWord = "'" + insWord + "'";
                    insType = "'" + insType + "'";
                    insMeaning = "'" + insMeaning + "'";
                    String query = "INSERT INTO english (word, type, meaning) VALUES (" + insWord + "," + insType + "," + insMeaning + ")";
                    myStmt = myConn.createStatement();
                    myStmt.executeUpdate(query);
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });
        HBox save = new HBox(btnSave);
        save.setAlignment(Pos.BOTTOM_RIGHT);
        save.getStyleClass().add(".add-word-save-container");

        VBox addWordInformation = new VBox(10, lblWord, word, lblType, type, lblMeaning, meaning, save);
        addWordInformation.getStyleClass().add("add-word-information");

        NavBar navBar = new NavBar();
        VBox addWordLayout = new VBox(10, navBar.create(), addWordInformation);
        addWordLayout.getStyleClass().add("add-word-layout");

        return addWordLayout;
    }

    @Override
    public void handleEvent() {

    }
}
