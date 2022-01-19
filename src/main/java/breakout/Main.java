package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

import static breakout.runClass.livesremaining;


/**
 * Main Class for running program
 * @author Haris Adnan
 */
public class Main extends Application {

  // useful names for constant values used
  public static final String TITLE = "Breakout Project";
  public static final int SIZE = 400;
  public static final Paint BACKGROUND = Color.DARKSLATEGRAY;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static Text WINNING_MESSAGE;
  // instance variables
  private runClass myGame;
  private Timeline animation;
  private Stage stage;

  /**
   * Initializes what will be displayed. stage is the window that is being set up
   */
  @Override
  public void start(Stage stage) {
    myGame = new runClass(this);

    // attach scene to the stage and display it
    Scene scene = myGame.setupGame(SIZE, SIZE, BACKGROUND, 1);
    this.stage = stage;
    stage.setScene(scene);
    stage.setTitle(TITLE);
    stage.show();
    // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> myGame.step(SECOND_DELAY)));
    animation.play();


  }

  /**
   * handles the shift of the level from one to another.
   * @param level: new level to be set up.
   */

  public void shiftLevel(int level) {

    //animation.stop();
    if (level > 3) {
      animation.stop();
      WINNING_MESSAGE = new Text(40, SIZE / 2, "CONGRATULATIONS, YOU WON!");
      WINNING_MESSAGE.setFill(Color.ANTIQUEWHITE);
      WINNING_MESSAGE.setFont(new Font(20));
      runClass.root.getChildren().add(WINNING_MESSAGE);

    }
    Scene scene = myGame.setupGame(SIZE, SIZE, BACKGROUND, level);
    this.stage.setScene(scene);


  }

}
