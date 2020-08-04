package Main.PursuitPack;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Board extends Pane {
    public enum BoardType { Arena, Forest, Maze, Death_Realm}

    Label scoreL;
    double score;
    boolean insane;
    boolean showTar;
    ArrayList<Barrier> barriers;
    Sprite player;
    Sprite cBox;
    Sprite ai;
    Sprite aBox;
    Rectangle[] lilpads;
    Rectangle bestLil = new Rectangle(0,0,1,1);
    double costBest;
    int playerV = 10;
    int aiV = 5;

    public Board(BoardType type, boolean insane, boolean showTar){
        this.insane = insane;
        this.showTar = showTar;
        barriers = new ArrayList<>();
        setPrefSize(620,620);
        setStyle("-fx-background-color: black;");
        switch(type) {
            case Arena:
                createArena();
                break;
            case Forest:
                createForest();
                break;
            case Maze:
                createMaze();
                break;
            case Death_Realm:
                createDeathRealm();
                break;
        }
    }
    private void createArena(){
        getChildren().clear();
        createWalls(Color.SANDYBROWN);
        barriers.add(new Barrier(110, 100, 10,70, Color.LIGHTGREY, true));
        barriers.add(new Barrier(120,200,360,10, Color.LIGHTGREY, true));
        barriers.add(new Barrier(290,310,10,90, Color.LIGHTGREY, true));
        barriers.add(new Barrier(330,310,220,10, Color.LIGHTGREY, true));
        barriers.add(new Barrier(300,80,10,50, Color.LIGHTGREY, true));
        barriers.add(new Barrier(70,280,150,10, Color.LIGHTGREY, true));
        barriers.add(new Barrier(210,370,10,180, Color.LIGHTGREY, true));
        barriers.add(new Barrier(300,490,80,10, Color.LIGHTGREY, true));
        barriers.add(new Barrier(460,400,10,90, Color.LIGHTGREY, true));
        getChildren().addAll(barriers);
        if (showTar) {
            for (Barrier b : barriers) {
                Rectangle[] s = b.getLilpads();
                for (Rectangle p : s) {
                    p.setFill(Color.DARKGRAY);
                    getChildren().add(p);
                }
            }
        }
        createScore();
        createPlayers();
    }
    private void createForest(){
        getChildren().clear();
        createWalls(Color.SADDLEBROWN);
        barriers.add(new Barrier(90,70,10,210, Color.DARKOLIVEGREEN, true));
        barriers.add(new Barrier(240,70,10,90, Color.DARKOLIVEGREEN, true));
        barriers.add(new Barrier(340,130,210,10, Color.DARKOLIVEGREEN, true));
        barriers.add(new Barrier(270,270,80,80, Color.SADDLEBROWN, true));
        barriers.add(new Barrier(460,240,90,10, Color.DARKOLIVEGREEN, true));
        barriers.add(new Barrier(70,370,90,10, Color.DARKOLIVEGREEN, true));
        barriers.add(new Barrier(520,340,10,210, Color.DARKOLIVEGREEN, true));
        barriers.add(new Barrier(70,520,210,10, Color.DARKOLIVEGREEN, true));
        barriers.add(new Barrier(370,460,10,90, Color.DARKOLIVEGREEN, true));
        getChildren().addAll(barriers);
        if (showTar) {
            for (Barrier b : barriers) {
                Rectangle[] s = b.getLilpads();
                for (Rectangle p : s) {
                    p.setFill(Color.DARKGRAY);
                    getChildren().add(p);
                }
            }
        }
        createScore();
        createPlayers();
    }
    private void createMaze(){
        getChildren().clear();
        createWalls(Color.WHEAT);
        barriers.add(new Barrier(160,90,180,10, Color.WHEAT, true));
        barriers.add(new Barrier(160,130,10,110, Color.WHEAT, true));
        barriers.add(new Barrier(460,100,10,90, Color.WHEAT, true));
        barriers.add(new Barrier(290,190,10,200, Color.WHEAT, true));
        barriers.add(new Barrier(10,280,150,10, Color.WHEAT, true));
        barriers.add(new Barrier(160,420,240,10, Color.WHEAT, true));
        barriers.add(new Barrier(400,250,210,50, Color.WHEAT, true));
        barriers.add(new Barrier(50,340,50,150, Color.WHEAT, true));
        barriers.add(new Barrier(130,570,110,10, Color.WHEAT, true));
        barriers.add(new Barrier(270,490,10,90, Color.WHEAT, true));
        barriers.add(new Barrier(380,460,10,150, Color.WHEAT, true));
        barriers.add(new Barrier(490,340,10,240, Color.WHEAT, true));
        getChildren().addAll(barriers);
        if (showTar) {
            for (Barrier b : barriers) {
                Rectangle[] s = b.getLilpads();
                for (Rectangle p : s) {
                    p.setFill(Color.DARKGRAY);
                    getChildren().add(p);
                }
            }
        }
        createScore();
        createPlayers();
    }
    private void createDeathRealm(){
        getChildren().clear();
        createWalls(Color.RED);
        barriers.add(new Barrier(70,70,10,180, Color.CHARTREUSE, true));
        barriers.add(new Barrier(130,70,170,10, Color.BLUEVIOLET, true));
        barriers.add(new Barrier(160,160,10,180, Color.WHITE, true));
        barriers.add(new Barrier(200,160,170,10, Color.ORANGE, true));
        barriers.add(new Barrier(250,250,10,180, Color.GREENYELLOW, true));
        barriers.add(new Barrier(290,250,170,10, Color.BLUE, true));
        barriers.add(new Barrier(340,340,180,10, Color.RED, true));
        barriers.add(new Barrier(340,430,10,120, Color.LIMEGREEN, true));
        barriers.add(new Barrier(380,540,110,10, Color.CADETBLUE, true));
        barriers.add(new Barrier(400,430,110,10, Color.PALEVIOLETRED, true));
        barriers.add(new Barrier(540,400,10,150, Color.DARKSEAGREEN, true));
        barriers.add(new Barrier(400,70,10,120, Color.BEIGE, true));
        barriers.add(new Barrier(440,70,70,10, Color.DEEPSKYBLUE, true));
        barriers.add(new Barrier(490,140,60,10, Color.YELLOW, true));
        barriers.add(new Barrier(540,190,10,90, Color.LAWNGREEN, true));
        barriers.add(new Barrier(100,310,10,150, Color.LIGHTBLUE, true));
        barriers.add(new Barrier(140,450,80,10, Color.ORANGERED, true));
        barriers.add(new Barrier(130,520,10,60, Color.LIGHTGREEN, true));
        barriers.add(new Barrier(250,520,10,90, Color.VIOLET, true));
        getChildren().addAll(barriers);
        if (showTar) {
            for (Barrier b : barriers) {
                Rectangle[] s = b.getLilpads();
                for (Rectangle p : s) {
                    p.setFill(Color.DARKGRAY);
                    getChildren().add(p);
                }
            }
        }
        createScore();
        createPlayers();
    }
    private void createWalls(Color c){
        barriers.add(new Barrier(0,0,620,10, c, false));
        barriers.add(new Barrier(0,10,10,600, c, false));
        barriers.add(new Barrier(0,610,620,10, c, false));
        barriers.add(new Barrier(610,10,10,600, c, false));
    }
    private void createPlayers(){
        player = new Sprite(40,550,30,30, Color.LIMEGREEN);
        cBox = new Sprite(41,551,28,28, Color.BLUE);
        ai = new Sprite(550, 40,30,30, Color.RED);
        aBox = new Sprite(541,41,28,28, Color.PURPLE);
        getChildren().addAll(player, ai);
    }
    private void createScore(){
        scoreL = new Label("SCORE: 0");
        scoreL.setFont(Font.font("OCR A EXTENDED", 16));
        scoreL.setTextFill(Color.WHITE);
        scoreL.setPrefSize(120, 20);
        scoreL.setLayoutX(15);
        scoreL.setLayoutY(15);
        getChildren().add(scoreL);
    }
    public void setScore(double score){
        this.score = score;
        scoreL.setText("SCORE: " + score);
    }
    public void setAIV(int newVel){
        aiV = newVel;
    }
    public void setPlayerV(int newVel){
        playerV = newVel;
    }

    //Player
    public void playerUp(){
        for (int i = 0; i < playerV; i++) {
            cBox.setUp();
            if (cSafe()) {
                cBox.up();
                player.up();
            }
        }
    }
    public void playerRight(){
        for (int i = 0; i < playerV; i++) {
            cBox.setRight();
            if (cSafe()) {
                cBox.right();
                player.right();
            }
        }
    }
    public void playerDown(){
        for (int i = 0; i < playerV; i++) {
            cBox.setDown();
            if (cSafe()) {
                cBox.down();
                player.down();
            }
        }
    }
    public void playerLeft(){
        for (int i = 0; i < playerV; i++) {
            cBox.setLeft();
            if (cSafe()) {
                cBox.left();
                player.left();
            }
        }
    }
    public boolean cSafe(){
        for (Barrier wall: barriers){
            if (cBox.getLayoutBounds().intersects(wall.getLayoutBounds())){
                return false;
            }
        }
        return true;
    }
    public boolean caught(){
        return aBox.getLayoutBounds().intersects(player.getLayoutBounds());
    }

    //AI
    public void moveAI(){
        bestLil.setFill(Color.DARKGRAY);
        if (pathSafe(player.getX() + 1, player.getY() + 1)) {
            moveTo(player.getX(), player.getY());
        } else {
            bestLil = lilpads[0];
            costBest = Math.abs(bestLil.getY() - cBox.getY()) + Math.abs(bestLil.getX() - aBox.getX());
            for (int i = 1; i < lilpads.length; i++) {
                if (Math.abs(lilpads[i].getY() - cBox.getY()) + Math.abs(lilpads[i].getX() - aBox.getX()) <= costBest) {
                    bestLil = lilpads[i];
                    costBest = Math.abs(lilpads[i].getY() - cBox.getY()) + Math.abs(lilpads[i].getX() - aBox.getX());
                }
            }
            if (showTar) {
                bestLil.setFill(Color.DARKOLIVEGREEN);
            }
            pathAI();
        }

    }
    public void pathAI(){
        int t = 0;
        while (!pathSafe(bestLil.getX(), bestLil.getY()) && t < 10) {
            bestLil = lilpads[0];
            costBest = Math.abs(bestLil.getY() - cBox.getY() + Math.abs(bestLil.getX() - aBox.getX()));
            for (int i = 1; i < lilpads.length; i++) {
                if (Math.abs(lilpads[i].getY() - cBox.getY()) + Math.abs(lilpads[i].getX() - aBox.getX()) <= costBest) {
                    bestLil = lilpads[i];
                    costBest = Math.abs(lilpads[i].getY() - cBox.getY()) + Math.abs(lilpads[i].getX() - aBox.getX());
                }
            }
            t++;
            if (t == 10){
                //System.out.println("Bug detected -> force movement");
            }
        }
        moveTo(bestLil.getX() - 1, bestLil.getY() - 1);
    }
    private boolean pathSafe(double x, double y){
        int blocksH = Math.abs((int)(aBox.getX() - x)) / 30;
        if (aBox.getX() > x){
            for (int h = 0; h < blocksH; h++){
                aBox.bLeft();
                if(!aSafe()){
                    resetABox();
                    return false;
                }
            }
        } else {
            for (int h = 0; h < blocksH; h++){
                aBox.bRight();
                if(!aSafe()){
                    resetABox();
                    return false;
                }
            }
        }
        if(aBox.getX() != x){
            aBox.setX(x);
            if(!aSafe()){
                resetABox();
                return false;
            }
        }
        int blocksV = Math.abs((int)(aBox.getY() - y)) / 30;
        if (aBox.getY() > y){
            for (int v = 0; v < blocksV; v++){
                aBox.bUp();
                if(!aSafe()){
                    resetABox();
                    return false;
                }
            }
        } else {
            for (int v = 0; v < blocksV; v++){
                aBox.bDown();
                if(!aSafe()){
                    resetABox();
                    return false;
                }
            }
        }
        if(aBox.getY() != y){
            aBox.setY(y);
            if(!aSafe()){
                resetABox();
                return false;
            }
        }
        resetABox();
        return true;
    }
    private void moveTo(double x, double y){
        int numMov = 0;
        while(ai.getX() != x && numMov < aiV){
            if(ai.getX() > x){
                ai.left();
                aBox.left();
            } else {
                ai.right();
                aBox.right();
            }
            numMov++;
        }
        while(ai.getY() != y && numMov < aiV){
            if(ai.getY() > y){
                ai.up();
                aBox.up();
            } else {
                ai.down();
                aBox.down();
            }
            numMov++;
        }
    }
    private boolean aSafe(){
        for (Barrier barrier: barriers){
            if (aBox.getLayoutBounds().intersects(barrier.getLayoutBounds())){
                lilpads = barrier.getLilpads();
                return false;
            }
        }
        return true;
    }
    private void resetABox(){
        aBox.setX(ai.getX()+1);
        aBox.setY(ai.getY()+1);
    }

    //AI Insane Movement
    boolean lastUp = false;
    boolean lastLeft = false;
    boolean lastDown = false;
    boolean lastRight = false;
    public void insaneReturnAI(){
        if (lastUp){
            ai.setY(ai.getY() + 20);
            lastUp = false;
        }
        if (lastLeft){
            ai.setX(ai.getX() + 20);
            lastLeft = false;
        }
        if (lastDown){
            ai.setY(ai.getY() - 20);
            lastDown = false;
        }
        if (lastRight){
            ai.setX(ai.getX() - 20);
            lastRight = false;
        }
    }
    public void insaneMoveAI(){
        double rand = Math.random();
        if (rand < 0.2){
            lastUp = true;
            ai.setY(ai.getY() - 20);
        } else if (rand < 0.4){
            lastLeft = true;
            ai.setX(ai.getX() - 20);
        } else if (rand < 0.6){
            lastDown = true;
            ai.setY(ai.getY() + 20);
        } else if (rand < 0.8){
            lastRight = true;
            ai.setX(ai.getX() + 20);
        }
    }
}
