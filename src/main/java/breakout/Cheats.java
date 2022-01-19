package breakout;

import static breakout.runClass.PADDLE_LENGTH;
import static breakout.runClass.myPaddle;

import javafx.scene.text.Text;
/**
 * The class that sets up cheats
 *
 * @author Haris Adnan
 */
public class Cheats {

  public Cheats() {

  }

  public static void addpoints(Text scoretext) {
    scoretext.setText(String.valueOf(100 + Integer.parseInt(scoretext.getText())));
  }

  /**
   * updates number of lives by the cheat
   * @param numberoflives: number of lives currently
   */
  public static void cheatlives(Text numberoflives) {
    numberoflives.setText(String.valueOf(Integer.parseInt(numberoflives.getText()) + 1));

  }

  /**
   * resets position of the ball and paddle to initial positions
   */
  public static void resetPositions() {
    runClass.myBouncer.setX(Main.SIZE / 2.0 - runClass.myBouncer.getBoundsInLocal().getWidth() / 2);
    runClass.myBouncer.setY(
        Main.SIZE / 2.0 - runClass.myBouncer.getBoundsInLocal().getHeight() / 2);
    runClass.myPaddle.setX(Main.SIZE / 2.0 - PADDLE_LENGTH / 2.0);
    runClass.myPaddle.setY(Main.SIZE - 50);
  }

  /**
   * sets position of paddle to the left of screen.
   */
  public static void toLeft() {
    runClass.myPaddle.setX(0);
  }
  /**
   * sets position of paddle to the right of screen.
   */
  public static void toRight() {
    runClass.myPaddle.setX(Main.SIZE - myPaddle.getWidth());
  }


}
