import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.event.*;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;
import java.io.File;

public class SearchLayout extends Component{
    static wordTree wordTree;
    static ListView<String> wordList;
    Connection myConn = null;
    Statement myStmt = null;
    ResultSet myRs = null;
    static TextField searchBar;
    @Override public VBox create() {
        loadData("jdbc:mysql://localhost:3306/dictionary2", "student", "student");


        wordList = new ListView<>();
        wordList.getItems().addAll(wordTree.getWordList());
        wordList.getStyleClass().add("word-list");

        searchBar = new TextField();
        searchBar.setOnKeyPressed(e -> updateWordList(e));
        searchBar.setPromptText("Search English");
        searchBar.getStyleClass().add("search-bar");
        wordList.setOnMouseClicked(e -> {
            String selectedWord = wordList.getSelectionModel().getSelectedItem();

            String ipa = "";
            String wordtype = "";
            String definition = "";
            String example = "";
            wordInformation.clear();
            try {
                myStmt = myConn.createStatement();
                myRs = myStmt.executeQuery("SELECT type, meaning, example, pronunciation FROM english WHERE word = " + "'" + selectedWord + "'");
                while (myRs.next()) {
                    wordtype = myRs.getString("type");
                    definition = myRs.getString("meaning");
                    example = myRs.getString("example");
                    ipa = myRs.getString("pronunciation");
                    if (wordtype.equals("")) {
                        wordtype = "phrase";
                    }
                    Label wordType = new Label(wordtype);
                    wordType.getStyleClass().add("word-type");
                    wordInformation.add(wordType);

                    Text def = new Text();
                    def.setText(definition);
                    def.getStyleClass().add("definition");
                    def.setWrappingWidth(800);
                    wordInformation.add(def);

                    if (example != null) {
                        if (!example.equals("")) {
                            Text ex = new Text();
                            ex.setText("•  " + example);
                            ex.getStyleClass().add("example");
                            ex.setWrappingWidth(800);
                            wordInformation.add(ex);
                        }
                    }

                    if (ipa == null || ipa.equals("")) {
                        ipa = "/" + selectedWord + "/";
                    }
                    wordInformation.setIPA(ipa);
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }

            searchBar.setText(selectedWord);
            wordList.getItems().clear();
            wordList.getItems().addAll(wordTree.autoSuggest(selectedWord));
            wordInformation.setWord(selectedWord);
            //wordInformation.setIPA(ipa);
        });
        VBox searchLayout = new VBox(20, searchBar, wordList);
        searchLayout.getStyleClass().add("search-layout");
        searchLayout.setAlignment(Pos.TOP_CENTER);
        return searchLayout;
    }
    @Override public void handleEvent() {

    }
    String s = "";
    public void updateWordList(KeyEvent e) {
        wordList.getItems().clear();
        TextField tmp =(TextField)e.getSource();
        switch (e.getCode()) {
            case A:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "a"));
                break;
            case B:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "b"));
                break;
            case C:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "c"));
                break;
            case D:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "d"));
                break;
            case E:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "e"));
                break;
            case F:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "f"));
                break;
            case G:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "g"));
                break;
            case H:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "h"));
                break;
            case I:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "i"));
                break;
            case J:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "j"));
                break;
            case K:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "k"));
                break;
            case L:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "l"));
                break;
            case M:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "m"));
                break;
            case N:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "n"));
                break;
            case O:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "o"));
                break;
            case P:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "p"));
                break;
            case Q:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "q"));
                break;
            case R:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "r"));
                break;
            case S:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "s"));
                break;
            case T:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "t"));
                break;
            case U:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "u"));
                break;
            case V:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "v"));
                break;
            case W:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "w"));
                break;
            case X:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "x"));
                break;
            case Y:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "y"));
                break;
            case Z:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase() + "z"));
                break;
            case BACK_SPACE:
                if (tmp.getText().length() <= 1) {
                    wordList.getItems().addAll(wordTree.getWordList());
                } else if (tmp.getText().length() > 1) {
                    String t = tmp.getText().substring(0, tmp.getText().length() - 1).toLowerCase();
                    wordList.getItems().addAll(wordTree.autoSuggest(t));
                }
                break;
            default:
                wordList.getItems().addAll(wordTree.autoSuggest(tmp.getText().toLowerCase()));
                break;
        }
    }

    public void loadData(String connectionUrl, String user, String password) {
        int count = 0;
        wordTree = new wordTree();
        try {
            myConn = DriverManager.getConnection(connectionUrl, user, password);
            System.out.println("Database connection successful!\n");
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT DISTINCT word FROM english");
            while (myRs.next()) {
                if (count == 0) {
                    count++;
                    continue;
                }
                String s = myRs.getString("word");
                wordTree.insert(s.toLowerCase());
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void updateWordList(String word) {
        wordTree.insert(word);
        wordList.getItems().add(word);
    }

    public static void updateDeletedWordList(String word) {
        wordList.getItems().remove(word);
        wordTree.removeWord(word);
        searchBar.setText("");
    }

}
