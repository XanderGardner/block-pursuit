package Main.PursuitPack;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Barrier extends Rectangle {
    Rectangle[] lilpads;
    public Barrier (int x, int y, int w, int h, Color fill, boolean makeLil){
        super(x,y,w,h);
        setFill(fill);
        if (makeLil) {
            if (y < 40) {
                lilpads = new Rectangle[2];
                lilpads[0] = (new Rectangle(x-29,y+h+1,28,28)); //bottom left
                lilpads[1] = (new Rectangle(x+w+1,y+h+1,28,28)); //bottom right
            } else if (x < 40){
                lilpads = new Rectangle[2];
                lilpads[0] = (new Rectangle(x+w+1, y-29, 28,28)); //top right
                lilpads[1] = (new Rectangle(x+w+1,y+h+1,28,28)); //bottom right
            } else if (y+h > 580) {
                lilpads = new Rectangle[2];
                lilpads[0] = (new Rectangle(x-29, y-29, 28,28)); //top left
                lilpads[1] = (new Rectangle(x+w+1, y-29, 28,28)); //top right
            } else if (x+w > 580){
                lilpads = new Rectangle[2];
                lilpads[0] = (new Rectangle(x-29, y-29, 28,28)); //top left
                lilpads[1] = (new Rectangle(x-29,y+h+1,28,28)); //bottom left
            } else {
                lilpads = new Rectangle[4];
                lilpads[0] = (new Rectangle(x-29, y-29, 28,28)); //top left
                lilpads[1] = (new Rectangle(x+w+1, y-29, 28,28)); //top right
                lilpads[2] = (new Rectangle(x-29,y+h+1,28,28)); //bottom left
                lilpads[3] = (new Rectangle(x+w+1,y+h+1,28,28)); //bottom right
            }
        } else {
            lilpads = new Rectangle[0];
        }
    }
    public Rectangle[] getLilpads(){
        return lilpads;
    }
}
