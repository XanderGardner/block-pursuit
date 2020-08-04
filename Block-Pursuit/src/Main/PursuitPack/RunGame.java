package Main.PursuitPack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.animation.AnimationTimer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RunGame {
    Stage stage;
    private Board board;
    private Boolean wCap = false;
    private Boolean aCap = false;
    private Boolean sCap = false;
    private Boolean dCap = false;
    MediaPlayer mediaPlayer;
    Timer timer;
    private double score;
    private long startTime;
    private ScoreRecord scoreRecord;
    private boolean insane;

    public void run(Stage stage, Board.BoardType boardType, boolean insane, boolean showTar){
        this.stage = stage;
        this.insane = insane;
        board = new Board(boardType, insane, showTar);
        Scene scene = new Scene(board, 620, 620);
        scene.setCursor(Cursor.NONE);
        stage.setScene(scene);
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    aCap = true;
                    break;
                case RIGHT:
                    dCap = true;
                    break;
                case UP:
                    wCap = true;
                    break;
                case DOWN:
                    sCap = true;
                    break;
            }
        });
        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case LEFT:
                    aCap = false;
                    break;
                case RIGHT:
                    dCap = false;
                    break;
                case UP:
                    wCap = false;
                    break;
                case DOWN:
                    sCap = false;
                    break;
            }
        });
        String gmFl;
        if (insane){
            gmFl = "insaneMusic.mp3";
        } else {
            gmFl = "normalMusic.mp3";
        }
        Media gmM = new Media(getClass().getResource(gmFl).toExternalForm());
        mediaPlayer = new MediaPlayer(gmM);
        mediaPlayer.play();

        /*
        try {
            scoreRecord = new ScoreRecord();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         */
        timer = new Timer();
        startTime = System.nanoTime();
        timer.start();
    }
    private class Timer extends AnimationTimer{
        @Override
        public void handle(long now) {
            score = (int)((now - startTime)/100_000_000.0)/10.0;
            board.setScore(score);
            int time = (int)score;
            if (insane){
                board.setAIV(10 + time/10);
                board.setPlayerV(11 + time/10);
                board.insaneReturnAI();
            } else {
                board.setAIV(4 + time/10);
                board.setPlayerV(6 + time/10);
            }
            if (wCap){
                board.playerUp();
            }
            if (aCap){
                board.playerLeft();
            }
            if (sCap){
                board.playerDown();
            }
            if (dCap){
                board.playerRight();
            }
            board.moveAI();
            if (insane){
                board.insaneMoveAI();
            }
            if (board.caught()){
                timer.stop();
                mediaPlayer.stop();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dead.fxml"));
                Parent root = null;
                try {
                    root = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DeadController deadController = fxmlLoader.getController();
                deadController.setScore(score);
                stage.setScene(new Scene(root));
                if (insane){
                    try {
                        ScoreRecord.trySetInsane("", score);
                        deadController.setInsane();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        ScoreRecord.trySetNormal("", score);
                        deadController.setNormal();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
