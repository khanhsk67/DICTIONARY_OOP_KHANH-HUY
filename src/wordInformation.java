import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class wordInformation extends Component{
    static Label wordName;
    static Label ipa;
    static VBox wordInformation;
    Label wordType;
    Connection myConn = null;
    Statement myStmt = null;
    ResultSet myRs = null;
    @Override public VBox create() {
        wordName = new Label("Success");
        wordName.getStyleClass().add("word-name");

        Button btnUpdate = new Button("", createIcon("icon/edit.png", 40));
        btnUpdate.getStyleClass().add("btn-update");
        btnUpdate.setOnAction(e -> {
            updateWordBox.init(wordName.getText(), wordType.getText());
            for (int i = 0; i < wordInformation.getChildren().size(); ++ i) {
                if (wordInformation.getChildren().get(i).getStyleClass().size() > 0 &&
                        wordInformation.getChildren().get(i).getStyleClass().get(0).equals("definition")) {
                    Label lblMeaning = new Label("Meaning:");
                    lblMeaning.getStyleClass().add("update-word-lbl");

                    Text tmp = (Text) wordInformation.getChildren().get(i);
                    TextField meaning = new TextField();
                    meaning.setText(tmp.getText());
                    System.out.println();
                    meaning.getStyleClass().add("update-meaning-txt");

                    updateWordBox.add(lblMeaning);
                    updateWordBox.add(meaning);
                } else if (wordInformation.getChildren().get(i).getStyleClass().size() > 0 &&
                        wordInformation.getChildren().get(i).getStyleClass().get(0).equals("example")) {
                    Label lblMeaning = new Label("Example:");
                    lblMeaning.getStyleClass().add("update-word-lbl");

                    Text tmp = (Text) wordInformation.getChildren().get(i);
                    TextField example = new TextField();
                    example.setText(tmp.getText());
                    example.getStyleClass().add("update-meaning-txt");

                    updateWordBox.add(lblMeaning);
                    updateWordBox.add(example);
                }
            }
            List<String> updateInformation = updateWordBox.show();
            String updateWord = updateInformation.get(0);
            String updateType = updateInformation.get(1);
            String updateMeaning = updateInformation.get(2);
            String updateExample = "";
            if (updateInformation.size() >=4) {
                updateExample = updateInformation.get(3);
                updateExample = updateExample.substring(3);
            }
            if (!wordName.getText().equals(updateWord)) {
                SearchLayout.updateDeletedWordList(wordName.getText());
                SearchLayout.updateWordList(updateWord);
            }
            try {
                String query;
                if (!updateExample.equals("")) {
                    query = "UPDATE english set word = '" + updateWord + "', type = '" + updateType + "', meaning = '" + updateMeaning
                            + "', example = '" + updateExample + "' WHERE word = '" + wordName.getText() + "'";
                } else {
                    query = "UPDATE english set word = '" + updateWord + "', type = '" + updateType + "', meaning = '" + updateMeaning
                            + "' WHERE word = '" + wordName.getText() + "'";
                }
                myStmt = myConn.createStatement();
                myStmt.executeUpdate(query);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });
        Button btnDelete = new Button("",createIcon("icon/bin.png", 40));
        btnDelete.getStyleClass().add("btn-delete");
        btnDelete.setOnAction(e -> {
            boolean confirm = ConfirmationBox.show("Are you sure","Confirmation", "Yes", "No");
            if (confirm) {
                try {
                    SearchLayout.updateDeletedWordList(wordName.getText());
                    String delWord = "'" + wordName.getText() + "'";
                    String query = "DELETE FROM english WHERE word = " + delWord;
                    myStmt = myConn.createStatement();
                    myStmt.executeUpdate(query);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        });


        Region spacer = new Region();

        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox word = new HBox(wordName, spacer, btnUpdate, btnDelete);
        word.setPrefWidth(800);



        Label USSpell = new Label("US");
        USSpell.getStyleClass().add("spell-lbl");
        Label UKSpell = new Label("UK");
        UKSpell.getStyleClass().add("spell-lbl");
        Button btnUS = new Button("\uD83D\uDD0A");
        btnUS.getStyleClass().add("spell-btn");

        ipa = new Label();
        ipa.getStyleClass().add("ipa");

        wordType = new Label("noun");
        wordType.getStyleClass().add("word-type");

        Text definition = new Text();
        definition.setText("The achieving of the results wanted or hoped for:");
        definition.getStyleClass().add("definition");

        Text example = new Text();
        example.setText("•  " + "The success of almost any project depends largely on its manager.");
        example.getStyleClass().add("example");

        wordInformation = new VBox(10, word, ipa, wordType, definition, example);
        wordInformation.getStyleClass().add("word-information");
        return wordInformation;
    }

    @Override public void handleEvent() {

    }

    public static void setWord(String s) {
        wordName.setText(s);
    }

    public static void add(Node l) {
        wordInformation.getChildren().add(l);
    }

    public static void clear() {
        int endIndex = wordInformation.getChildren().size();
        wordInformation.getChildren().remove(3, endIndex);
    }
    public static void setIPA(String i) {
        ipa.setText(i);
    }

    public ImageView createIcon (String url, int size) {
        Image img = new Image(url);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(size);
        imgView.setFitHeight(size);
        imgView.setPreserveRatio(true);

        return imgView;
    }
}
