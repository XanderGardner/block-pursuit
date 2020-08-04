package Main.PursuitPack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeadController implements Initializable {
    @FXML Label message, score, highscore, enterName;
    @FXML
    TextField entry;
    @FXML
    Button enter;
    @FXML
    AnchorPane backdrop;
    MediaPlayer mediaPlayer, mediaPlayer2;
    //ScoreRecord scoreRecord;
    int mode = -1;

    public void initialize(URL location, ResourceBundle resources){
        message.setText(getMessage());
        String gmFl = "deathMusic.mp3";
        Media gmM = new Media(getClass().getResource(gmFl).toExternalForm());
        mediaPlayer = new MediaPlayer(gmM);
        mediaPlayer.play();
        String gmFl2 = "death2Music.mp3";
        Media gmM2 = new Media(getClass().getResource(gmFl2).toExternalForm());
        mediaPlayer2 = new MediaPlayer(gmM2);
        mediaPlayer2.play();
        backdrop.setOnKeyPressed(e -> {
           switch (e.getCode()){
               case ENTER:
                   enterC();
                   break;
               case R:
                   homeC();
                   break;
           }
        });
    }
    public void homeC(){
        if (enter.visibleProperty().getValue()){
            enterC();
        }
        Parent startRoot = null;
        try {
            startRoot = FXMLLoader.load(getClass().getResource("Home.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage mainStage = (Stage) message.getScene().getWindow();
        Scene homeScene = null;
        if (startRoot != null) {
            homeScene = new Scene(startRoot, 620, 620);
        }
        mediaPlayer.stop();
        mediaPlayer2.stop();
        mainStage.setScene(homeScene);
    }
    public void enterC(){
        if (!entry.getText().equals("")) {
            try {
                //scoreRecord = new ScoreRecord();
                if (mode == 0) {
                    System.out.println("NORMAL NAME IS " + entry.getText());
                    ScoreRecord.setNormalName(entry.getText());
                    setNormal();
                } else if (mode == 1) {
                    System.out.println("INSANE NAME IS " + entry.getText());
                    ScoreRecord.setInsaneName(entry.getText());
                    setInsane();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                //scoreRecord = new ScoreRecord();
                if (mode == 0) {
                    ScoreRecord.setNormalName("N/A");
                    setNormal();
                } else if (mode == 1) {
                    ScoreRecord.setInsaneName("N/A");
                    setInsane();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        enterName.setVisible(false);
        entry.setVisible(false);
        enter.setVisible(false);
    }
    public void setScore(double score){
        this.score.setText("SCORE: " + score);
    }
    public void setInsane() throws FileNotFoundException {
        //scoreRecord = new ScoreRecord();
        double highscore = ScoreRecord.getInsaneScore();
        this.highscore.setText("HIGHSCORE: " + highscore + " " + ScoreRecord.getInsaneName() + " INSANE");
        if (!ScoreRecord.getInsaneName().equals("")){
            enterName.setVisible(false);
            entry.setVisible(false);
            enter.setVisible(false);
        } else {
            message.setText("NEW HIGHSCORE!");
        }
        mode = 1;
    }
    public void setNormal() throws FileNotFoundException {
        //scoreRecord = new ScoreRecord();
        double highscore = ScoreRecord.getNormalScore();
        this.highscore.setText("HIGHSCORE: " + highscore + " " + ScoreRecord.getNormalName());
        if (!ScoreRecord.getNormalName().equals("")){
            enterName.setVisible(false);
            entry.setVisible(false);
            enter.setVisible(false);
        } else {
            message.setText("NEW HIGHSCORE!");
        }
        mode = 0;
    }
    private String getMessage(){
        double r = Math.random();
        String m;
        if(r < 0.01) {
            m = "Demeaning Message";
        } else if (r < 0.1){
            m = "You play like an infant";
        } else if(r < 0.2) {
            m ="It hurts to watch you play";
        } else if(r < 0.3) {
            m = "Terrible. Just Terrible.";
        } else if(r < 0.4) {
            m = "My grandma's faster than you";
        } else if(r < 0.5) {
            m = "bruh. Are you even trying?";
        } else if(r < 0.6) {
            m = "Great Job...";
        } else if(r < 0.7) {
            m = "FRICKEN NOOB";
        } else if(r < 0.8) {
            m = "Lemme guess... Disabled?";
        } else if(r < 0.9) {
            m = "Absolutely DESTROYED";
        } else {
            m = "Snails, Turtles, Grandmas, You";
        }
        return m;
    }
}
