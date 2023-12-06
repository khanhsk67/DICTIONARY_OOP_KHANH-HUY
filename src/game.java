import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class game extends Component{
    String exactCurWord = "";
    String curWord = "";
    String guessWord;
    String answer;
    String curHint;
    word word;
    ImageView imgViewHangman;
    Label currentWord;
    Label hint;
    Label incorrectGuess;
    Button[] button;
    int[] count;
    @Override public BorderPane create() {

        word = new word();
        word.load();

        int wordListSize = word.getWordList().size();
        Random rand = new Random();
        int randomIndex = rand.nextInt(wordListSize);
        answer = word.getWordList().get(randomIndex);
        System.out.println(answer);
        curHint = word.getHintList().get(randomIndex);
        for (int i = 0; i < answer.length(); ++ i) {
            exactCurWord += "_";
            curWord += "_";
            if (i != answer.length() - 1) {
                curWord += " ";
            }
        }

        Image imgHangman = new Image("image/hangman-0.png");
        imgViewHangman = new ImageView();
        imgViewHangman.setImage(imgHangman);
        HBox hboxHangman = new HBox(imgViewHangman);
        hboxHangman.setAlignment(Pos.CENTER);

        Label lblHangman = new Label("HANGMAN GAME");
        lblHangman.getStyleClass().add("label-hangman");
        VBox vboxHangman = new VBox(30, hboxHangman, lblHangman);
        vboxHangman.setAlignment(Pos.CENTER);
        vboxHangman.getStyleClass().add("vbox-hangman");

        currentWord = new Label(curWord);
        currentWord.getStyleClass().add("game-current-word");

        hint = new Label(curHint);
        hint.getStyleClass().add("game-text");
        hint.setWrapText(true);
        incorrectGuess = new Label("Incorrect guesses: 0/6");
        incorrectGuess.getStyleClass().add("game-text");

        count = new int[]{0};
        button = new Button[26];
        for (int i = 0; i < 26; ++ i) {
            final int index = i;
            char letter = (char)('A' + i);
            button[i] = new Button("" + letter);
            button[i].getStyleClass().add("game-button");
            button[i].setOnAction(e -> {
                button[index].getStyleClass().clear();
                button[index].getStyleClass().add("pressed");
                button[index].setAlignment(Pos.CENTER);

                boolean isRight = false;
                StringBuilder sb = new StringBuilder(curWord);
                StringBuilder sb2 = new StringBuilder(exactCurWord);
                for (int pos = 0; pos < answer.length(); ++ pos) {
                    String tmp = "" + answer.charAt(pos);
                    if (button[index].getText().toLowerCase().equals(tmp.toLowerCase())) {
                        isRight = true;
                        sb.setCharAt(2 * pos, answer.charAt(pos));
                        sb2.setCharAt(pos,answer.charAt(pos));
                    }
                }
                exactCurWord = sb2.toString();
                curWord = sb.toString();
                currentWord.setText(curWord);
                if (exactCurWord.equals(answer)) {
                    MessageBox.show(true, answer);
                    reset();
                }
                if (isRight == false) {
                    count[0]++;
                    incorrectGuess.setText("Incorrect guesses: " + count[0] + "/6");
                    final String path = "image/hangman-" + count[0] + ".png";
                    Image image = new Image(path);
                    imgViewHangman.setImage(image);
                    if (count[0] == 6) {
                        MessageBox.show(false,answer);
                        reset();
                    }
                }
            });
        }

        HBox row1 = new HBox(10);
        for (int i = 0; i < 9; ++ i) {
            row1.getChildren().add(button[i]);
        }
        row1.setAlignment(Pos.CENTER);

        HBox row2 = new HBox(10);
        for (int i = 9; i < 18; ++ i) {
            row2.getChildren().add(button[i]);
        }
        row2.setAlignment(Pos.CENTER);

        HBox row3 = new HBox(10);
        for (int i = 18; i < 26; ++ i) {
            row3.getChildren().add(button[i]);
        }
        row3.setAlignment(Pos.CENTER);

        VBox gameButton = new VBox(10, row1, row2, row3);
        VBox container = new VBox(30, currentWord, hint, incorrectGuess, gameButton);
        container.setAlignment(Pos.CENTER);

        HBox gameContainer = new HBox(50, vboxHangman, container);
        gameContainer.getStyleClass().add("game-container");

        NavBar navBar = new NavBar();

        BorderPane screen = new BorderPane();
        screen.setCenter(gameContainer);
        screen.setTop(navBar.create());
        screen.getStyleClass().add("screen");

        return screen;
    }

    @Override
    public void handleEvent() {

    }

    public void reset() {
        int wordListSize = word.getWordList().size();
        Random rand = new Random();
        int randomIndex = rand.nextInt(wordListSize);
        answer = word.getWordList().get(randomIndex);
        System.out.println(answer);
        curHint = word.getHintList().get(randomIndex);
        exactCurWord = "";
        curWord = "";
        for (int i = 0; i < answer.length(); ++ i) {
            exactCurWord += "_";
            curWord += "_";
            if (i != answer.length() - 1) {
                curWord += " ";
            }
        }
        Image imgHangman = new Image("image/hangman-0.png");
        imgViewHangman.setImage(imgHangman);
        currentWord.setText(curWord);
        hint.setText(curHint);
        incorrectGuess.setText("Incorrect guesses: 0/6");

        for (int index = 0; index < 26; ++ index) {
            button[index].getStyleClass().clear();
            button[index].getStyleClass().add("game-button");
            button[index].setAlignment(Pos.CENTER);
        }

        count = new int[] {0};
    }

    public boolean isWin() {
        return true;
    }
}

class word {
    private ArrayList<String> wordList = new ArrayList<>();
    private ArrayList<String> hintList = new ArrayList<>();
    public void load() {
        int count = 0;
        File file = new File("src/data/hangman-game-word-list.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                count ++;
                if (count % 4 == 3) {
                    String word = sc.nextLine();
                    wordList.add(word.substring(15, word.length() - 2));
                } else if (count % 4 == 0) {
                    String hint = sc.nextLine();
                    hintList.add(hint.substring(15, hint.length() - 1));
                } else {
                    sc.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file is not found");
        }
    }

    public ArrayList<String> getWordList() {
        return wordList;
    }

    public ArrayList<String> getHintList() {
        return hintList;
    }
}