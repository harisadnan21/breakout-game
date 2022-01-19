package breakout;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.FileInputStream;
/**
 * The class that contains methods for Score, Lives and Level counting
 *
 * @author Haris Adnan
 */

public class Score {

  public static final int MESSAGE_X = 75;
  public static final int MESSAGE_Y = 100;
  public static final int PREFERRED_WIDTH = 300;
  public static final int PREFERRED_HEIGHT = 200;

  public Score() {

  }

  /**
   * updates score by adding previuos score to the extra
   * @param scoretext: previous score
   * @param addition: score to be added
   */
  public static void updateScore(Text scoretext, int addition) {
    scoretext.setText(String.valueOf(addition + Integer.parseInt(scoretext.getText())));
  }

  /**
   * updates lives by subtracting a life from the present number of lives
   * @param numberoflives : present number of lives
   */
  public static void updateLives(Text numberoflives) {
    numberoflives.setText(String.valueOf(Integer.parseInt(numberoflives.getText()) - 1));
  }

  /**
   * updates the level by adding 1 to the present level number
   * @param levelnumber: present level number
   */
  public static void updateLevel(Text levelnumber) {
    levelnumber.setText(String.valueOf(Integer.parseInt(levelnumber.getText()) + 1));
  }

}
