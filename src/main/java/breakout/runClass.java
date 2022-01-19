package breakout;

import javafx.scene.control.Button;
import javafx.scene.robot.Robot;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import java.nio.file.*;;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.text.*;
import java.lang.String;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * The class that setups the breakout window
 * depends on Main
 * @author Haris Adnan
 */
// in isIntersecting funtion, put in a check for what type of block it is
public class runClass {

  // /Users/harisadnan/Desktop/Spring_2022/CS_308/breakout_ha109/src/main/java/breakout/
  public static final String RESOURCE_PATH = "/";
  public static final String BOUNCER_IMAGE = RESOURCE_PATH + "img.png";
  public static final String LEVEL2 = RESOURCE_PATH + "level2.txt";
  public static final String LEVEL1 = RESOURCE_PATH + "level1.txt";
  public static final String LEVEL3 = RESOURCE_PATH + "level3.txt";
  public static final int BOUNCER_SIZE = 20;

  public static int BOUNCER_SPEED = 50;
  public static int BOUNCER_CONSTANT_X = 2;
  public static int BOUNCER_CONSTANT_Y = 5;
  public static final Paint BLOCK50_COLOR = Color.INDIGO;
  public static int BLOCK50_SIZE = 40;
  public static int BLOCKSOLID_SIZE = 22;
  public static final Paint BLOCKSOLID_COLOR = Color.GRAY;
  public static final Paint BLOCK100_COLOR = Color.DARKRED;
  public static final Paint BLOCKBALLSLOW_COLOR = Color.DARKGREEN;
  public static final Paint BLOCKINCPADDLE_COLOR = Color.PURPLE;
  public static int BLOCKSLOWPADDLE_SIZE = 25;
  public static final Paint BLOCKSLOWPADDLE_COLOR = Color.DARKCYAN;
  public static int BLOCK100_SIZE = 25;
  public static int BLOCKINCPADDLE_SIZE = 25;
  public static final Paint PADDLE_COLOR = Color.SEAGREEN;
  public static int PADDLE_LENGTH = 80;
  public static int PADDLE_WIDTH = (int) (PADDLE_LENGTH / 10);
  public static int PADDLE_SPEED = 20;
  public static ImageView myBouncer;
  public static Rectangle myPaddle;
  private int directionX;
  private int directionY;
  private int score;
  public static Text scorenumber;
  public static Text livesremaining;
  public static Text levelnumber;
  private int level;
  private int lives;
  public static Group root;
  private ArrayList<Rectangle> blocks50 = new ArrayList<>();
  private ArrayList<Rectangle> blocks100 = new ArrayList<>();
  private ArrayList<Rectangle> blocksballslow = new ArrayList<>();
  private ArrayList<Rectangle> blocksincpaddle = new ArrayList<>();
  private ArrayList<Rectangle> blocksslowpaddle = new ArrayList<>();
  private ArrayList<Rectangle> blockssolid = new ArrayList<>();
  private Scene scene;
  private Main main;
  private double changingballspeedconstant;
  private double changingpaddlelengthconstant;
  private double changingpaddlespeedconstant;

  public runClass(Main main) {
    this.main = main;

  }

  /**
   * function that provides the intial setup for the game. Parameters define the size, color and level of game
   */

  public Scene setupGame(int width, int height, Paint background, int level) {
    this.level = level;
    root = new Group();
    directionX = 1;
    directionY = -1;
    //Smaller size of paddle, and the speed of the ball is faster.
    if (level == 2) {
      changingpaddlelengthconstant = 0.80;
      changingballspeedconstant = 1.2;
    }
    //Group root = new Group();
    Image image = new Image(getClass().getResourceAsStream(BOUNCER_IMAGE));
    myBouncer = new ImageView(image);
    myBouncer.setFitWidth(BOUNCER_SIZE);
    myBouncer.setFitHeight(BOUNCER_SIZE);
    myBouncer.setX(Main.SIZE / 2.0 - myBouncer.getBoundsInLocal().getWidth() / 2);
    myBouncer.setY(Main.SIZE / 2.0 - myBouncer.getBoundsInLocal().getHeight() / 2);
    //String data = readFileAsString(LEVEL2);
    // ADDING PADDLE
    myPaddle = new Rectangle(width / 2.0 - PADDLE_LENGTH / 2.0, height - 50, PADDLE_LENGTH,
        PADDLE_WIDTH);
    myPaddle.setFill(PADDLE_COLOR);
    //ADDING SCORE
    Text scorestring = new Text(10, 20, "SCORE:");
    scorestring.setFill(Color.ANTIQUEWHITE);
    scorestring.setFont(new Font(20));
    scorenumber = new Text(100, 20, String.valueOf(score));
    scorenumber.setFill(Color.ANTIQUEWHITE);
    scorenumber.setFont(new Font(20));
    //ADDING LIVES
    Text livestring = new Text(10, 50, "LIVES:");
    livestring.setFill(Color.ANTIQUEWHITE);
    livestring.setFont(new Font(20));
    lives = 3;
    livesremaining = new Text(100, 50, String.valueOf(lives));
    livesremaining.setFill(Color.ANTIQUEWHITE);
    livesremaining.setFont(new Font(20));
    //ADDING LEVEL NUMBER
    Text levelstring = new Text(10, 80, "LEVEL:");
    levelstring.setFill(Color.ANTIQUEWHITE);
    levelstring.setFont(new Font(20));
    levelnumber = new Text(100, 80, String.valueOf(level));
    levelnumber.setFill(Color.ANTIQUEWHITE);
    levelnumber.setFont(new Font(20));
    createLevel("/level" + level + ".txt");

    root.getChildren().add(myBouncer);
    root.getChildren().add(myPaddle);
    root.getChildren().add(scorestring);
    root.getChildren().add(scorenumber);
    root.getChildren().add(livestring);
    root.getChildren().add(livesremaining);
    root.getChildren().add(levelstring);
    root.getChildren().add(levelnumber);
    changingpaddlelengthconstant = 1;
    changingballspeedconstant = 1;
    changingpaddlespeedconstant = 1;
    // create a place to see the shapes
    scene = new Scene(root, width, height, background);
    // respond to input
    scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

    return scene;
  }

 /**
 function that updates each frame of the animation.

  */
  public void step(double elapsedTime) {
    System.out.println(blocks50);
    System.out.println(blocksballslow);
    System.out.println(blocksincpaddle);
    System.out.println(blocks100);
    System.out.println(blocksslowpaddle);
    myBouncer.setX(
        myBouncer.getX() + BOUNCER_SPEED * elapsedTime * directionX * BOUNCER_CONSTANT_X
            * changingballspeedconstant);
    myBouncer.setY(
        myBouncer.getY() + BOUNCER_SPEED * elapsedTime * directionY * BOUNCER_CONSTANT_Y
            * changingballspeedconstant);
    if (blocks50.isEmpty() && blocks100.isEmpty() && blocksballslow.isEmpty()
        && blocksincpaddle.isEmpty() && blocksslowpaddle.isEmpty()) {
      level += 1;
      main.shiftLevel(level);

    }
    if (!blocks50.isEmpty()) {
      blocks50.removeIf(brick -> handleblock50collision(brick));
    }
    if (!blocks100.isEmpty()) {
      blocks100.removeIf(brick -> handleblock100collision(brick));
    }
    if (!blocksballslow.isEmpty()) {
      blocksballslow.removeIf(brick -> handleblockballslowcollision(brick));
    }
    if (!blocksincpaddle.isEmpty()) {
      blocksincpaddle.removeIf(brick -> handleblockincpaddlecollision(brick));
    }
    if (!blocksslowpaddle.isEmpty()) {
      blocksslowpaddle.removeIf(brick -> handleblockslowpaddlecollision(brick));
    }
    if (!blocksslowpaddle.isEmpty()) {
      blockssolid.removeIf(brick -> handleblockssolidcollision(brick));
    }

    if (myBouncer.getX() <= 0 || myBouncer.getX() >= Main.SIZE - myBouncer.getFitWidth()) {
      directionX = -1 * directionX;
    }
    if (myBouncer.getY() <= 0) {
      if (myBouncer.getY() >= Main.SIZE - myBouncer.getFitHeight()) {
        Score.updateLives(livesremaining);
      }
      directionY = -1 * directionY;
    }

    if (isIntersecting(myBouncer, myPaddle)) {
      bounceBallBricks(myBouncer, myPaddle);
    }
    if (Integer.parseInt(livesremaining.getText()) == 0) {
      main.shiftLevel(level);

    }
    if (myBouncer.getY() >= Main.SIZE - myBouncer.getFitHeight()) {
      refreshBall();
    }

  }

  private void refreshBall() {
    Score.updateLives(livesremaining);
    myBouncer.setX(Main.SIZE / 2);
    myBouncer.setY(Main.SIZE / 2);
    directionY = -1;
  }

  private void createLevel(String file) {
    Scanner input = new Scanner(getClass().getResourceAsStream(file));
    int yval = 0;
    int diff = (Main.SIZE - 50) / 5;
    while (input.hasNextLine()) {
      String data = input.nextLine();
      for (int i = 0; i < data.length(); i++) {
        if (Character.getNumericValue(data.charAt(i)) == 0 && file.equals("/level3.txt")) {
          createBlockSolid(i * diff, yval);
        } else if (Character.getNumericValue(data.charAt(i)) == 1) {
          createBlock50(i * diff, yval);
        } else if (Character.getNumericValue(data.charAt(i)) == 2) {
          createBlock100(i * diff, yval);
        } else if (Character.getNumericValue(data.charAt(i)) == 3) {
          createBlockBallSlow(i * diff, yval);
        } else if (Character.getNumericValue(data.charAt(i)) == 4) {
          createBlockIncPaddle(i * diff, yval);
        } else if (Character.getNumericValue(data.charAt(i)) == 5) {
          createBlockSlowPaddle(i * diff, yval);
        }

      }
      yval = yval + diff - 20;
    }
    input.close();
  }

  private boolean handleblock50collision(Rectangle block50) {
    if (isIntersecting(myBouncer, block50)) {
      bounceBallBricks(myBouncer, block50);
      Score.updateScore(scorenumber, 50);
      removeBrick(block50);
      return true;
    }
    return false;
  }


  private boolean handleblock100collision(Rectangle block100) {
    if (isIntersecting(myBouncer, block100)) {
      bounceBallBricks(myBouncer, block100);
      Score.updateScore(scorenumber, 100);
      removeBrick(block100);
      return true;
    }
    return false;
  }

  private boolean handleblockballslowcollision(Rectangle blockballslow) {
    if (isIntersecting(myBouncer, blockballslow)) {
      bounceBallBricks(myBouncer, blockballslow);
      Score.updateScore(scorenumber, 100);
      removeBrick(blockballslow);
      changingballspeedconstant = changingballspeedconstant * 0.93;
      return true;
    }
    return false;
  }

  private boolean handleblockincpaddlecollision(Rectangle blockincpaddle) {
    if (isIntersecting(myBouncer, blockincpaddle)) {
      bounceBallBricks(myBouncer, blockincpaddle);
      Score.updateScore(scorenumber, 100);
      removeBrick(blockincpaddle);
      changingpaddlelengthconstant = changingpaddlelengthconstant * 1.05;
      myPaddle.setWidth(myPaddle.getWidth() * changingpaddlelengthconstant);
      return true;
    }
    return false;
  }

  private boolean handleblockslowpaddlecollision(Rectangle blockslowpaddle) {
    if (isIntersecting(myBouncer, blockslowpaddle)) {
      bounceBallBricks(myBouncer, blockslowpaddle);
      Score.updateScore(scorenumber, 100);
      removeBrick(blockslowpaddle);
      changingpaddlespeedconstant = changingpaddlespeedconstant * 0.90;
      return true;
    }
    return false;
  }

  private boolean handleblockssolidcollision(Rectangle blocksolid) {
    //these blocks add 0 points when hit and dont get destroyed
    if (isIntersecting(myBouncer, blocksolid)) {
      bounceBallBricks(myBouncer, blocksolid);
      Score.updateScore(scorenumber, 0);
      removeBrick(blocksolid);

      return true;
    }
    return false;
  }


  // What to do each time a key is pressed
  private void handleKeyInput(KeyCode code) {

    if (code == KeyCode.RIGHT && (myPaddle.getX() + PADDLE_LENGTH < Main.SIZE)) {
      myPaddle.setX(myPaddle.getX() + (PADDLE_SPEED * changingpaddlespeedconstant));
    } else if (code == KeyCode.LEFT && (myPaddle.getX() > 0)) {
      myPaddle.setX(myPaddle.getX() - (PADDLE_SPEED * changingpaddlespeedconstant));
    } else if (code == KeyCode.UP && (myPaddle.getY() > (4 * Main.SIZE) / 5)) {
      myPaddle.setY(myPaddle.getY() - (PADDLE_SPEED * changingpaddlespeedconstant));
    } else if (code == KeyCode.DOWN && ((myPaddle.getY() + PADDLE_WIDTH) < Main.SIZE)) {
      myPaddle.setY(myPaddle.getY() + (PADDLE_SPEED * changingpaddlespeedconstant));
    } else if (code == KeyCode.L) {
      Cheats.cheatlives(livesremaining);
    } else if (code == KeyCode.S) {
      Cheats.addpoints(scorenumber);
    } else if (code == KeyCode.R) {
      Cheats.resetPositions();
    } else if (code == KeyCode.A) {
      Cheats.toLeft();
    } else if (code == KeyCode.D) {
      Cheats.toRight();
    } else if (code == KeyCode.DIGIT1) {
      main.shiftLevel(1);
    } else if (code == KeyCode.DIGIT2) {
      main.shiftLevel(2);
    } else if (code == KeyCode.DIGIT3) {
      main.shiftLevel(3);
    }

  }

  // TRIED USING MOUSE TO CONTROL PADDLE
  //    public void handlePaddlePosition(){
//        Bounds bounds = root.localToScreen(root.getBoundsInLocal());
//        double sceneXPos = 0;
//        double xPos = robot.getMouseX();
//        if(xPos >= sceneXPos + (PADDLE_LENGTH/2) && xPos <= (sceneXPos +Main.SIZE) - (PADDLE_LENGTH/2)){
//            myPaddle.setLayoutX(xPos - sceneXPos - (PADDLE_LENGTH/2));
//        } else if (xPos < sceneXPos + (PADDLE_LENGTH/2)){
//            myPaddle.setLayoutX(0);
//        } else if (xPos > (sceneXPos + Main.SIZE) - (PADDLE_LENGTH/2)){
//
//            myPaddle.setLayoutX(Main.SIZE - PADDLE_LENGTH);
//        }
//    }

  /**
   * Creates large purple block, adds 50 when hit
   * @param xval: xpos
   * @param yval: ypos
   */
  private void createBlock50(int xval, int yval) {
    double width = Main.SIZE;
    double height = Main.SIZE;

    Rectangle brick = new Rectangle(xval, yval, BLOCK50_SIZE, BLOCK50_SIZE);
    brick.setFill(BLOCK50_COLOR);
    root.getChildren().add(brick);
    blocks50.add(brick);

  }

  /**
   * Creates dark red block, adds 100 to points when hit
   * @param xval: xpos
   * @param yval:ypos
   */
  private void createBlock100(int xval, int yval) {
    double width = Main.SIZE;
    double height = Main.SIZE;
    int spaceCheck = 1;
    Rectangle brick = new Rectangle(xval, yval, BLOCK100_SIZE, BLOCK100_SIZE);
    brick.setFill(BLOCK100_COLOR);
    root.getChildren().add(brick);
    blocks100.add(brick);

  }

  /**
   * Creates DarkGreen block, adds 100 points when hit and slows ball down
   * @param xval:xpos
   * @param yval:ypos
   */
  private void createBlockBallSlow(int xval, int yval) {

    Rectangle brick = new Rectangle(xval, yval, BLOCK100_SIZE, BLOCK100_SIZE);
    brick.setFill(BLOCKBALLSLOW_COLOR);
    root.getChildren().add(brick);
    blocksballslow.add(brick);
  }

  /**
   * Creates Purple block, adds 100 when hit, and increases the length of the paddle
   * @param xval: xpos
   * @param yval : ypos
   */
  private void createBlockIncPaddle(int xval, int yval) {

    Rectangle brick = new Rectangle(xval, yval, BLOCKINCPADDLE_SIZE, BLOCKINCPADDLE_SIZE);
    brick.setFill(BLOCKINCPADDLE_COLOR);
    root.getChildren().add(brick);
    blocksincpaddle.add(brick);

  }

  /**
   * Creates Dark Cyan block, adds 100 pts when hit and slows paddle's speed
   * @param xval: xpos
   *
   * @param yval: ypos
   */
  private void createBlockSlowPaddle(int xval, int yval) {

    Rectangle brick = new Rectangle(xval, yval, BLOCKSLOWPADDLE_SIZE, BLOCKSLOWPADDLE_SIZE);
    brick.setFill(BLOCKSLOWPADDLE_COLOR);
    root.getChildren().add(brick);
    blocksslowpaddle.add(brick);

  }

  /**
   * Creates gray block that adds 0 pts when hit. only implemented in level 3.
   * @param xval
   * @param yval
   */
  private void createBlockSolid(int xval, int yval) {

    Rectangle brick = new Rectangle(xval, yval, BLOCKSOLID_SIZE, BLOCKSOLID_SIZE);
    brick.setFill(BLOCKSOLID_COLOR);
    root.getChildren().add(brick);
    blockssolid.add(brick);

  }

  private void removeBrick(Rectangle brick) {
    root.getChildren().remove(brick);
  }

  private void removeBrickobj(Rectangleobj brick) {
    root.getChildren().remove(brick);
  }

  // Name for a potentially complex comparison to make code more readable
  private boolean isIntersecting(ImageView a, Rectangle b) {
    // with images can only check bounding box (as it is calculated in container with other objects)
    return b.getBoundsInParent().intersects(a.getBoundsInParent());
  }

  private boolean isIntersectingobj(ImageView a, Rectangleobj b) {
    return b.getBoundsInParent().intersects(a.getBoundsInParent());
  }

  private void bounceBallBricks(ImageView ball, Rectangle brick) {
    if (ball.getX() + BOUNCER_SIZE / 2 < brick.getX()) {
      directionX = -1;
    }
    //intersects from right
    else if (ball.getX() + BOUNCER_SIZE / 2 > brick.getX() + brick.getWidth()) {
      directionX = 1;
    }
    //intersects from top
    else if (ball.getY() + BOUNCER_SIZE / 2 < brick.getY()) {
      directionY = -1;
    }
    //intersects from the bottom
    else if (ball.getY() + BOUNCER_SIZE / 2 > brick.getY() + brick.getHeight()) {
      directionY = 1;
    }
  }
  //method for bouncing rectangle object, didn't end up using
  private void bounceBallBricksobj(ImageView ball, Rectangleobj brick) {
    if (ball.getX() + BOUNCER_SIZE / 2 < brick.getX()) {
      directionX = -1;
    }
    //intersects from right
    else if (ball.getX() + BOUNCER_SIZE / 2 > brick.getX() + brick.getWidth()) {
      directionX = 1;
    }
    //intersects from top
    else if (ball.getY() + BOUNCER_SIZE / 2 < brick.getY()) {
      directionY = -1;
    }
    //intersects from the bottom
    else if (ball.getY() + BOUNCER_SIZE / 2 > brick.getY() + brick.getHeight()) {
      directionY = 1;
    }
  }


}



