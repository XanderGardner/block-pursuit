package Main.PursuitPack;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sprite extends Rectangle {
    Boolean top = false;
    Boolean left = false;
    Boolean bottom = false;
    Boolean right = false;
    int v = 1; //checks every v
    public Sprite(int x, int y, int w, int h, Color c){
        super(x,y,w,h);
        setFill(c);
    }
    public Sprite(int x, int y, int w, int h, Color c, int v){
        super(x,y,w,h);
        setFill(c);
        this.v = v;
    }
    public void down(){
        setY(getY() + v);

    }
    public void up(){
        setY(getY() - v);

    }
    public void right(){
        setX(getX() + v);

    }
    public void left(){
        setX(getX() - v);

    }
    public void bDown(){
        setY(getY() + 30);

    }
    public void bUp(){
        setY(getY() - 30);

    }
    public void bRight(){
        setX(getX() + 30);

    }
    public void bLeft(){
        setX(getX() - 30);

    }
    public void setUp(){
        if (!top){
            setY(getY() - v);
            if (left){
                setX(getX() + v);
                left = false;
            } else if (bottom){
                setY(getY() - v);
                bottom = false;
            } else if (right){
                setX(getX() - v);
                right = false;
            }
            top = true;
        }
    }
    public void setLeft(){
        if (!left){
            setX(getX() - v);
            if (top){
                setY(getY() + v);
                top = false;
            } else if (right){
                setX(getX() - v);
                right = false;
            } else if (bottom){
                setY(getY() - v);
                bottom = false;
            }
            left = true;
        }
    }
    public void setDown(){
        if (!bottom){
            setY(getY() + v);
            if (left){
                setX(getX() + v);
                left = false;
            } else if (top){
                setY(getY() + v);
                top = false;
            } else if (right){
                setX(getX() - v);
                right = false;
            }
            bottom = true;
        }
    }
    public void setRight(){
        if (!right){
            setX(getX() + v);
            if (top){
                setY(getY() + v);
                top = false;
            } else if (left){
                setX(getX() + v);
                left = false;
            } else if (bottom){
                setY(getY() - v);
                bottom = false;
            }
            right = true;
        }
    }
    public void setV(int v){
        this.v = v;
    }
    public int getV(){
        return v;
    }
}