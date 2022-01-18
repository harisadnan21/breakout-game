package breakout;

import static breakout.runClass.PADDLE_LENGTH;
import static breakout.runClass.myPaddle;

import javafx.scene.text.Text;

public class Cheats {
    public Cheats(){

    }
    public static void addpoints(Text scoretext){
            scoretext.setText(String.valueOf(100 + Integer.parseInt(scoretext.getText())));
    }
    public static void cheatlives(Text numberoflives){
        numberoflives.setText(String.valueOf(Integer.parseInt(numberoflives.getText()) +1));

    }
    public static void resetPositions(){
        runClass.myBouncer.setX(Main.SIZE / 2.0 - runClass.myBouncer.getBoundsInLocal().getWidth() / 2);
        runClass.myBouncer.setY(Main.SIZE / 2.0 - runClass.myBouncer.getBoundsInLocal().getHeight() / 2);
        runClass.myPaddle.setX(Main.SIZE / 2.0 - PADDLE_LENGTH / 2.0);
        runClass.myPaddle.setY(Main.SIZE - 50);
    }
    public static void toLeft(){
        runClass.myPaddle.setX(0);
    }
    public static void toRight(){
        runClass.myPaddle.setX(Main.SIZE - myPaddle.getWidth());
    }
}
