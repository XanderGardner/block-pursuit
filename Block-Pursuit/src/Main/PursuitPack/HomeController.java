package Main.PursuitPack;

import Main.PursuitPack.Board;
import Main.PursuitPack.RunGame;
import Main.PursuitPack.Sprite;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML AnchorPane backdrop, mapBackdrop;
    @FXML Button showTarButton;
    MediaPlayer mediaPlayer;
    Timer animation;
    MapTimer mapAnimation;
    Sprite r1, r2, r3, r4, mr1, mr2;
    long startTime;
    int anSpeed = 1;
    public enum Direction{ Up, Left, Down, Right};
    Direction r1d = Direction.Down;
    Direction r2d = Direction.Right;
    Direction r3d = Direction.Down;
    Direction r4d = Direction.Left;
    Direction mr1d = Direction.Down;
    Direction mr2d = Direction.Up;
    double rand;
    boolean showTar = false;
    boolean insane;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        String gmFl = "homeMusic.mp3";
        //Media gmM = new Media(new File(gmFl).toURI().toString());
        Media gmM = new Media(getClass().getResource(gmFl).toExternalForm());
        mediaPlayer = new MediaPlayer(gmM);
        mediaPlayer.play();

        r1 = new Sprite(30, 30, 30, 30, Color.RED, 5);
        r2 = new Sprite(30, 560, 30, 30, Color.GREEN, 5);
        r3 = new Sprite(560, 30, 30, 30, Color.BLUE, 5);
        r4 = new Sprite(560, 335, 30, 30, Color.ORANGE, 5);
        mr1 = new Sprite(65, 175, 30, 30, Color.RED, 4);
        mr2 = new Sprite(525, 525, 30, 30, Color.GREEN, 4);
        backdrop.getChildren().addAll(r1, r2, r3, r4);
        mapBackdrop.getChildren().addAll(mr1, mr2);
        startTime = System.nanoTime();
        mapAnimation = new MapTimer();
        animation = new Timer();
        animation.start();
    }

    public void normalClicked(){
        animation.stop();
        backdrop.setVisible(false);
        mapBackdrop.setVisible(true);
        mapAnimation.start();
        insane = false;
    }
    public void insaneClicked(){
        animation.stop();
        backdrop.setVisible(false);
        mapBackdrop.setVisible(true);
        mapAnimation.start();
        insane = true;
    }
    public void showTarClicked(){
        showTar = !showTar;
        if (showTar){
            showTarButton.setText("Show Target: ON");
        } else {
            showTarButton.setText("Show Target: OFF");
        }
    }
    public void homeC(){
        mapAnimation.stop();
        mapBackdrop.setVisible(false);
        backdrop.setVisible(true);
        animation.start();
    }
    public void arenaC(){
        Stage mainStage = (Stage) mapBackdrop.getScene().getWindow();
        mediaPlayer.stop();
        mapAnimation.stop();
        RunGame runGame = new RunGame();
        runGame.run(mainStage, Board.BoardType.Arena, insane, showTar);
    }
    public void forestC(){
        Stage mainStage = (Stage) mapBackdrop.getScene().getWindow();
        mediaPlayer.stop();
        mapAnimation.stop();
        RunGame runGame = new RunGame();
        runGame.run(mainStage, Board.BoardType.Forest, insane, showTar);
    }
    public void mazeC(){
        Stage mainStage = (Stage) mapBackdrop.getScene().getWindow();
        mediaPlayer.stop();
        mapAnimation.stop();
        RunGame runGame = new RunGame();
        runGame.run(mainStage, Board.BoardType.Maze, insane, showTar);
    }
    public void deathC(){
        Stage mainStage = (Stage) mapBackdrop.getScene().getWindow();
        mediaPlayer.stop();
        mapAnimation.stop();
        RunGame runGame = new RunGame();
        runGame.run(mainStage, Board.BoardType.Death_Realm, insane, showTar);
    }
    private class Timer extends AnimationTimer{
        @Override
        public void handle(long now){
            if (now - startTime < 19_600_000_000.0){
                anSpeed = 1;
            } else if (now - startTime < 25_000_000_000.0){
                anSpeed = 2;
            } else if (now - startTime < 32_000_000_000.0){
                anSpeed = 3;
            } else if (now - startTime < 60_000_000_000.0){
                anSpeed = 4;
            } else if (now - startTime < 90_000_000_000.0){
                anSpeed = 6;
            } else {
                anSpeed = 9;
            }
            while (anSpeed > 0) {
                switch (r1d) {
                    case Up:
                        r1.up();
                        break;
                    case Left:
                        r1.left();
                        break;
                    case Down:
                        r1.down();
                        break;
                    case Right:
                        r1.right();
                        break;
                }
                switch (r2d) {
                    case Up:
                        r2.up();
                        break;
                    case Left:
                        r2.left();
                        break;
                    case Down:
                        r2.down();
                        break;
                    case Right:
                        r2.right();
                        break;
                }
                switch (r3d) {
                    case Up:
                        r3.up();
                        break;
                    case Left:
                        r3.left();
                        break;
                    case Down:
                        r3.down();
                        break;
                    case Right:
                        r3.right();
                        break;
                }
                switch (r4d) {
                    case Up:
                        r4.up();
                        break;
                    case Left:
                        r4.left();
                        break;
                    case Down:
                        r4.down();
                        break;
                    case Right:
                        r4.right();
                        break;
                }
                n1();
                n2();
                n3();
                n4();
                n5();
                n6();
                n7();
                n8();
                n9();
                n10();
                n11();
                clrBug();
                anSpeed--;
            }
        }
    }
    private class MapTimer extends AnimationTimer{
        public void handle(long now){
            switch (mr1d){
                case Up:
                    mr1.up();
                    break;
                case Down:
                    mr1.down();
                    break;
            }
            switch (mr2d){
                case Up:
                    mr2.up();
                    break;
                case Down:
                    mr2.down();
                    break;
            }
            if (mr1.getY() > 525){
                mr1d = Direction.Up;
                mr1.setV((int)(10*Math.random() + 3));
            } else if (mr1.getY() < 175){
                mr1d = Direction.Down;
                mr1.setV((int)(10*Math.random() + 3));
            }
            if (mr2.getY() > 525){
                mr2d = Direction.Up;
                mr2.setV((int)(10*Math.random() + 3));
            } else if (mr2.getY() < 175){
                mr2d = Direction.Down;
                mr2.setV((int)(10*Math.random() + 3));
            }
        }
    }
    public void clrBug(){
        if (r1.getX() == 570){
            backdrop.getChildren().remove(r1);
            r1 = new Sprite(30, 30, 30, 30, Color.RED, 5);
            backdrop.getChildren().add(r1);
        }
        if (r2.getX() == 570){
            backdrop.getChildren().remove(r2);
            r2 = new Sprite(30, 560, 30, 30, Color.GREEN, 5);
            backdrop.getChildren().add(r2);
        }
        if (r3.getX() == 570){
            backdrop.getChildren().remove(r3);
            r3 = new Sprite(30, 30, 30, 30, Color.BLUE, 5);
            backdrop.getChildren().add(r3);
        }
        if (r4.getX() == 570){
            backdrop.getChildren().remove(r4);
            r4 = new Sprite(30, 560, 30, 30, Color.ORANGE, 5);
            backdrop.getChildren().add(r4);
        }
    }
    private void n1(){
        if (r1.getX() == 30 && r1.getX() == 30){
            rand = Math.random();
            switch (r1d){
                case Left:
                    if (rand < 0.3){
                        r1d = Direction.Right;
                    } else {
                        r1d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r1d = Direction.Down;
                    } else {
                        r1d = Direction.Right;
                    }
                    break;
            }
        }
        if (r2.getX() == 30 && r2.getX() == 30){
            rand = Math.random();
            switch (r2d){
                case Left:
                    if (rand < 0.3){
                        r2d = Direction.Right;
                    } else {
                        r2d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r2d = Direction.Down;
                    } else {
                        r2d = Direction.Right;
                    }
                    break;
            }
        }
        if (r3.getX() == 30 && r3.getX() == 30){
            rand = Math.random();
            switch (r3d){
                case Left:
                    if (rand < 0.3){
                        r3d = Direction.Right;
                    } else {
                        r3d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r3d = Direction.Down;
                    } else {
                        r3d = Direction.Right;
                    }
                    break;
            }
        }
        if (r4.getX() == 30 && r4.getX() == 30){
            rand = Math.random();
            switch (r4d){
                case Left:
                    if (rand < 0.3){
                        r4d = Direction.Right;
                    } else {
                        r4d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r4d = Direction.Down;
                    } else {
                        r4d = Direction.Right;
                    }
                    break;
            }
        }
    }
    private void n2(){
        if (r1.getX() == 560 && r1.getY() == 30){
            rand = Math.random();
            switch (r1d) {
                case Right:
                    if (rand < 0.3){
                        r1d = Direction.Left;
                    } else {
                        r1d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r1d = Direction.Down;
                    } else {
                        r1d = Direction.Left;
                    }
                    break;
            }
        }
        if (r2.getX() == 560 && r2.getY() == 30){
            rand = Math.random();
            switch (r2d) {
                case Right:
                    if (rand < 0.3){
                        r2d = Direction.Left;
                    } else {
                        r2d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r2d = Direction.Down;
                    } else {
                        r2d = Direction.Left;
                    }
                    break;
            }
        }
        if (r3.getX() == 560 && r3.getY() == 30){
            rand = Math.random();
            switch (r3d) {
                case Right:
                    if (rand < 0.3){
                        r3d = Direction.Left;
                    } else {
                        r3d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r3d = Direction.Down;
                    } else {
                        r3d = Direction.Left;
                    }
                    break;
            }
        }
        if (r4.getX() == 560 && r4.getY() == 30){
            rand = Math.random();
            switch (r4d) {
                case Right:
                    if (rand < 0.3){
                        r4d = Direction.Left;
                    } else {
                        r4d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r4d = Direction.Down;
                    } else {
                        r4d = Direction.Left;
                    }
                    break;
            }
        }
    }
    private void n3(){
        if (r1.getX() == 30 && r1.getY() == 220){
            rand = Math.random();
            switch (r1d){
                case Down:
                    if (rand < 0.1){
                        r1d = Direction.Up;
                        break;
                    } else if (rand < 0.45){
                        r1d = Direction.Right;
                    } else {
                        r1d = Direction.Down;
                    }
                    break;
                case Left:
                    if (rand < 0.1){
                        r1d = Direction.Right;
                    } else if (rand < 0.45){
                        r1d = Direction.Up;
                    } else {
                        r1d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r1d = Direction.Down;
                    } else if (rand < 0.45){
                        r1d = Direction.Right;
                    } else {
                        r1d = Direction.Up;
                    }
                    break;
            }
        }
        if (r2.getX() == 30 && r2.getY() == 220){
            rand = Math.random();
            switch (r2d){
                case Down:
                    if (rand < 0.1){
                        r2d = Direction.Up;
                        break;
                    } else if (rand < 0.45){
                        r2d = Direction.Right;
                    } else {
                        r2d = Direction.Down;
                    }
                    break;
                case Left:
                    if (rand < 0.1){
                        r2d = Direction.Right;
                    } else if (rand < 0.45){
                        r2d = Direction.Up;
                    } else {
                        r2d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r2d = Direction.Down;
                    } else if (rand < 0.45){
                        r2d = Direction.Right;
                    } else {
                        r2d = Direction.Up;
                    }
                    break;
            }
        }
        if (r3.getX() == 30 && r3.getY() == 220){
            rand = Math.random();
            switch (r3d){
                case Down:
                    if (rand < 0.1){
                        r3d = Direction.Up;
                        break;
                    } else if (rand < 0.45){
                        r3d = Direction.Right;
                    } else {
                        r3d = Direction.Down;
                    }
                    break;
                case Left:
                    if (rand < 0.1){
                        r3d = Direction.Right;
                    } else if (rand < 0.45){
                        r3d = Direction.Up;
                    } else {
                        r3d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r3d = Direction.Down;
                    } else if (rand < 0.45){
                        r3d = Direction.Right;
                    } else {
                        r3d = Direction.Up;
                    }
                    break;
            }
        }
        if (r4.getX() == 30 && r4.getY() == 220){
            rand = Math.random();
            switch (r4d){
                case Down:
                    if (rand < 0.1){
                        r4d = Direction.Up;
                        break;
                    } else if (rand < 0.45){
                        r4d = Direction.Right;
                    } else {
                        r4d = Direction.Down;
                    }
                    break;
                case Left:
                    if (rand < 0.1){
                        r4d = Direction.Right;
                    } else if (rand < 0.45){
                        r4d = Direction.Up;
                    } else {
                        r4d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r4d = Direction.Down;
                    } else if (rand < 0.45){
                        r4d = Direction.Right;
                    } else {
                        r4d = Direction.Up;
                    }
                    break;
            }
        }
    }
    private void n4(){
        if (r1.getX() == 355 && r1.getY() == 220){
            rand = Math.random();
            switch (r1d){
                case Right:
                    if (rand < 0.1){
                        r1d = Direction.Left;
                    } else {
                        r1d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r1d = Direction.Down;
                    } else {
                        r1d = Direction.Left;
                    }
                    break;
            }
        }
        if (r2.getX() == 355 && r2.getY() == 220){
            rand = Math.random();
            switch (r2d){
                case Right:
                    if (rand < 0.1){
                        r2d = Direction.Left;
                    } else {
                        r2d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r2d = Direction.Down;
                    } else {
                        r2d = Direction.Left;
                    }
                    break;
            }
        }
        if (r3.getX() == 355 && r3.getY() == 220){
            rand = Math.random();
            switch (r3d){
                case Right:
                    if (rand < 0.1){
                        r3d = Direction.Left;
                    } else {
                        r3d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r3d = Direction.Down;
                    } else {
                        r3d = Direction.Left;
                    }
                    break;
            }
        }
        if (r4.getX() == 355 && r4.getY() == 220){
            rand = Math.random();
            switch (r4d){
                case Right:
                    if (rand < 0.1){
                        r4d = Direction.Left;
                    } else {
                        r4d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r4d = Direction.Down;
                    } else {
                        r4d = Direction.Left;
                    }
                    break;
            }
        }
    }
    private void n5(){
        if (r1.getX() == 240 && r1.getY() == 335){
            switch (r1d){
                case Left:
                    r1d = Direction.Down;
                    break;
                case Up:
                    r1d = Direction.Right;
                    break;
            }
        }
        if (r2.getX() == 240 && r2.getY() == 335){
            switch (r2d){
                case Left:
                    r2d = Direction.Down;
                    break;
                case Up:
                    r2d = Direction.Right;
                    break;
            }
        }
        if (r3.getX() == 240 && r3.getY() == 335){
            switch (r3d){
                case Left:
                    r3d = Direction.Down;
                    break;
                case Up:
                    r3d = Direction.Right;
                    break;
            }
        }
        if (r4.getX() == 240 && r4.getY() == 335){
            switch (r4d){
                case Left:
                    r4d = Direction.Down;
                    break;
                case Up:
                    r4d = Direction.Right;
                    break;
            }
        }
    }
    private void n6(){
        if (r1.getX() == 355 && r1.getY() == 335){
            rand = Math.random();
            switch (r1d){
                case Down:
                    if (rand < 0.1){
                        r1d = Direction.Up;
                    } else if (rand < 0.45){
                        r1d = Direction.Left;
                    } else {
                        r1d = Direction.Right;
                    }
                    break;
                case Right:
                    if (rand < 0.1){
                        r1d = Direction.Left;
                    } else if (rand < 0.45) {
                        r1d = Direction.Up;
                    }
                    break;
                case Left:
                    if (rand < 0.1){
                        r1d = Direction.Right;
                    } else if (rand < 0.45){
                        r1d = Direction.Up;
                    }
                    break;
            }
        }
        if (r2.getX() == 355 && r2.getY() == 335){
            rand = Math.random();
            switch (r2d){
                case Down:
                    if (rand < 0.1){
                        r2d = Direction.Up;
                    } else if (rand < 0.45){
                        r2d = Direction.Left;
                    } else {
                        r2d = Direction.Right;
                    }
                    break;
                case Right:
                    if (rand < 0.1){
                        r2d = Direction.Left;
                    } else if (rand < 0.45) {
                        r2d = Direction.Up;
                    }
                    break;
                case Left:
                    if (rand < 0.1){
                        r2d = Direction.Right;
                    } else if (rand < 0.45){
                        r2d = Direction.Up;
                    }
                    break;
            }
        }
        if (r3.getX() == 355 && r3.getY() == 335){
            rand = Math.random();
            switch (r3d){
                case Down:
                    if (rand < 0.1){
                        r3d = Direction.Up;
                    } else if (rand < 0.45){
                        r3d = Direction.Left;
                    } else {
                        r3d = Direction.Right;
                    }
                    break;
                case Right:
                    if (rand < 0.1){
                        r3d = Direction.Left;
                    } else if (rand < 0.45) {
                        r3d = Direction.Up;
                    }
                    break;
                case Left:
                    if (rand < 0.1){
                        r3d = Direction.Right;
                    } else if (rand < 0.45){
                        r3d = Direction.Up;
                    }
                    break;
            }
        }
        if (r4.getX() == 355 && r4.getY() == 335){
            rand = Math.random();
            switch (r4d){
                case Down:
                    if (rand < 0.1){
                        r4d = Direction.Up;
                    } else if (rand < 0.45){
                        r4d = Direction.Left;
                    } else {
                        r4d = Direction.Right;
                    }
                    break;
                case Right:
                    if (rand < 0.1){
                        r4d = Direction.Left;
                    } else if (rand < 0.45) {
                        r4d = Direction.Up;
                    }
                    break;
                case Left:
                    if (rand < 0.1){
                        r4d = Direction.Right;
                    } else if (rand < 0.45){
                        r4d = Direction.Up;
                    }
                    break;
            }
        }
    }
    public void n7(){
        if (r1.getX() == 560 && r1.getY() == 335){
            rand = Math.random();
            switch (r1d){
                case Right:
                    if (rand < 0.1){
                        r1d = Direction.Left;
                    } else if (rand < 0.45){
                        r1d = Direction.Up;
                    } else {
                        r1d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r1d = Direction.Down;
                    } else if (rand < 0.45){
                        r1d = Direction.Left;
                    }
                    break;
                case Down:
                    if (rand < 0.1){
                        r1d = Direction.Up;
                    } else if (rand < 0.45){
                        r1d = Direction.Left;
                    }
                    break;
            }
        }
        if (r2.getX() == 560 && r2.getY() == 335){
            rand = Math.random();
            switch (r2d){
                case Right:
                    if (rand < 0.1){
                        r2d = Direction.Left;
                    } else if (rand < 0.45){
                        r2d = Direction.Up;
                    } else {
                        r2d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r2d = Direction.Down;
                    } else if (rand < 0.45){
                        r2d = Direction.Left;
                    }
                    break;
                case Down:
                    if (rand < 0.1){
                        r2d = Direction.Up;
                    } else if (rand < 0.45){
                        r2d = Direction.Left;
                    }
                    break;
            }
        }
        if (r3.getX() == 560 && r3.getY() == 335){
            rand = Math.random();
            switch (r3d){
                case Right:
                    if (rand < 0.1){
                        r3d = Direction.Left;
                    } else if (rand < 0.45){
                        r3d = Direction.Up;
                    } else {
                        r3d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r3d = Direction.Down;
                    } else if (rand < 0.45){
                        r3d = Direction.Left;
                    }
                    break;
                case Down:
                    if (rand < 0.1){
                        r3d = Direction.Up;
                    } else if (rand < 0.45){
                        r3d = Direction.Left;
                    }
                    break;
            }
        }
        if (r4.getX() == 560 && r4.getY() == 335){
            rand = Math.random();
            switch (r4d){
                case Right:
                    if (rand < 0.1){
                        r4d = Direction.Left;
                    } else if (rand < 0.45){
                        r4d = Direction.Up;
                    } else {
                        r4d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r4d = Direction.Down;
                    } else if (rand < 0.45){
                        r4d = Direction.Left;
                    }
                    break;
                case Down:
                    if (rand < 0.1){
                        r4d = Direction.Up;
                    } else if (rand < 0.45){
                        r4d = Direction.Left;
                    }
                    break;
            }
        }
    }
    private void n8(){
        if (r1.getX() == 30 && r1.getY() == 455){
            rand = Math.random();
            switch (r1d){
                case Down:
                    if (rand < 0.1){
                        r1d = Direction.Up;
                    } else if (rand < 0.45){
                        r1d = Direction.Right;
                    } else {
                        r1d = Direction.Down;
                    }
                    break;
                case Left:
                    if (rand < 0.1){
                        r1d = Direction.Right;
                    } else if (rand < 0.45){
                        r1d = Direction.Up;
                    } else {
                        r1d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r1d = Direction.Down;
                    } else if (rand < 0.45){
                        r1d = Direction.Right;
                    } else {
                        r1d = Direction.Up;
                    }
                    break;
            }
        }
        if (r2.getX() == 30 && r2.getY() == 455){
            rand = Math.random();
            switch (r2d){
                case Down:
                    if (rand < 0.1){
                        r2d = Direction.Up;
                    } else if (rand < 0.45){
                        r2d = Direction.Right;
                    } else {
                        r2d = Direction.Down;
                    }
                    break;
                case Left:
                    if (rand < 0.1){
                        r2d = Direction.Right;
                    } else if (rand < 0.45){
                        r2d = Direction.Up;
                    } else {
                        r2d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r2d = Direction.Down;
                    } else if (rand < 0.45){
                        r2d = Direction.Right;
                    } else {
                        r2d = Direction.Up;
                    }
                    break;
            }
        }
        if (r3.getX() == 30 && r3.getY() == 455){
            rand = Math.random();
            switch (r3d){
                case Down:
                    if (rand < 0.1){
                        r3d = Direction.Up;
                    } else if (rand < 0.45){
                        r3d = Direction.Right;
                    } else {
                        r3d = Direction.Down;
                    }
                    break;
                case Left:
                    if (rand < 0.1){
                        r3d = Direction.Right;
                    } else if (rand < 0.45){
                        r3d = Direction.Up;
                    } else {
                        r3d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r3d = Direction.Down;
                    } else if (rand < 0.45){
                        r3d = Direction.Right;
                    } else {
                        r3d = Direction.Up;
                    }
                    break;
            }
        }
        if (r4.getX() == 30 && r4.getY() == 455){
            rand = Math.random();
            switch (r4d){
                case Down:
                    if (rand < 0.1){
                        r4d = Direction.Up;
                    } else if (rand < 0.45){
                        r4d = Direction.Right;
                    } else {
                        r4d = Direction.Down;
                    }
                    break;
                case Left:
                    if (rand < 0.1){
                        r4d = Direction.Right;
                    } else if (rand < 0.45){
                        r4d = Direction.Up;
                    } else {
                        r4d = Direction.Down;
                    }
                    break;
                case Up:
                    if (rand < 0.1){
                        r4d = Direction.Down;
                    } else if (rand < 0.45){
                        r4d = Direction.Right;
                    } else {
                        r4d = Direction.Up;
                    }
                    break;
            }
        }
    }
    private void n9(){
        if (r1.getX() == 240 && r1.getY() == 455){
            rand = Math.random();
            switch (r1d){
                case Right:
                    if (rand < 0.1){
                        r1d = Direction.Left;
                    } else {
                        r1d = Direction.Up;
                    }
                    break;
                case Down:
                    if (rand < 0.1){
                        r1d = Direction.Up;
                    } else {
                        r1d = Direction.Left;
                    }
                    break;
            }
        }
        if (r2.getX() == 240 && r2.getY() == 455){
            rand = Math.random();
            switch (r2d){
                case Right:
                    if (rand < 0.1){
                        r2d = Direction.Left;
                    } else {
                        r2d = Direction.Up;
                    }
                    break;
                case Down:
                    if (rand < 0.1){
                        r2d = Direction.Up;
                    } else {
                        r2d = Direction.Left;
                    }
                    break;
            }
        }
        if (r3.getX() == 240 && r3.getY() == 455){
            rand = Math.random();
            switch (r3d){
                case Right:
                    if (rand < 0.1){
                        r3d = Direction.Left;
                    } else {
                        r3d = Direction.Up;
                    }
                    break;
                case Down:
                    if (rand < 0.1){
                        r3d = Direction.Up;
                    } else {
                        r3d = Direction.Left;
                    }
                    break;
            }
        }
        if (r4.getX() == 240 && r4.getY() == 455){
            rand = Math.random();
            switch (r4d){
                case Right:
                    if (rand < 0.1){
                        r4d = Direction.Left;
                    } else {
                        r4d = Direction.Up;
                    }
                    break;
                case Down:
                    if (rand < 0.1){
                        r4d = Direction.Up;
                    } else {
                        r4d = Direction.Left;
                    }
                    break;
            }
        }
    }
    private void n10(){
        if (r1.getX() == 30 && r1.getY() == 560){
            rand = Math.random();
            switch (r1d){
                case Down:
                    if (rand < 0.1){
                        r1d = Direction.Up;
                    } else {
                        r1d = Direction.Right;
                    }
                    break;
                case Left:
                    if (rand < 0.2){
                        r1d = Direction.Right;
                    } else {
                        r1d = Direction.Up;
                    }
                    break;
            }
        }
        if (r2.getX() == 30 && r2.getY() == 560){
            rand = Math.random();
            switch (r2d){
                case Down:
                    if (rand < 0.1){
                        r2d = Direction.Up;
                    } else {
                        r2d = Direction.Right;
                    }
                    break;
                case Left:
                    if (rand < 0.2){
                        r2d = Direction.Right;
                    } else {
                        r2d = Direction.Up;
                    }
                    break;
            }
        }
        if (r3.getX() == 30 && r3.getY() == 560){
            rand = Math.random();
            switch (r3d){
                case Down:
                    if (rand < 0.1){
                        r3d = Direction.Up;
                    } else {
                        r3d = Direction.Right;
                    }
                    break;
                case Left:
                    if (rand < 0.2){
                        r3d = Direction.Right;
                    } else {
                        r3d = Direction.Up;
                    }
                    break;
            }
        }
        if (r4.getX() == 30 && r4.getY() == 560){
            rand = Math.random();
            switch (r4d){
                case Down:
                    if (rand < 0.1){
                        r4d = Direction.Up;
                    } else {
                        r4d = Direction.Right;
                    }
                    break;
                case Left:
                    if (rand < 0.2){
                        r4d = Direction.Right;
                    } else {
                        r4d = Direction.Up;
                    }
                    break;
            }
        }
    }
    private void n11(){
        if (r1.getX() == 560 && r1.getY() == 560){
            rand = Math.random();
            switch (r1d){
                case Right:
                    if (rand < 0.2){
                        r1d = Direction.Left;
                    } else {
                        r1d = Direction.Up;
                    }
                    break;
                case Down:
                    if (rand < 0.1){
                        r1d = Direction.Up;
                    } else {
                        r1d = Direction.Left;
                    }
                    break;
            }
        }
        if (r2.getX() == 560 && r2.getY() == 560){
            rand = Math.random();
            switch (r2d){
                case Right:
                    if (rand < 0.2){
                        r2d = Direction.Left;
                    } else {
                        r2d = Direction.Up;
                    }
                    break;
                case Down:
                    if (rand < 0.1){
                        r2d = Direction.Up;
                    } else {
                        r2d = Direction.Left;
                    }
                    break;
            }
        }
        if (r3.getX() == 560 && r3.getY() == 560){
            rand = Math.random();
            switch (r3d){
                case Right:
                    if (rand < 0.2){
                        r3d = Direction.Left;
                    } else {
                        r3d = Direction.Up;
                    }
                    break;
                case Down:
                    if (rand < 0.1){
                        r3d = Direction.Up;
                    } else {
                        r3d = Direction.Left;
                    }
                    break;
            }
        }
        if (r4.getX() == 560 && r4.getY() == 560){
            rand = Math.random();
            switch (r4d){
                case Right:
                    if (rand < 0.2){
                        r4d = Direction.Left;
                    } else {
                        r4d = Direction.Up;
                    }
                    break;
                case Down:
                    if (rand < 0.1){
                        r4d = Direction.Up;
                    } else {
                        r4d = Direction.Left;
                    }
                    break;
            }
        }
    }
}
