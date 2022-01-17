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

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.text.*;
/**
 * The class that setups the breakout window
 *
 * @author Haris Adnan
 */
// in isIntersecting funtion, put in a check for what type of block it is
public class runClass {
    public static final String RESOURCE_PATH = "/";
    public static final String BOUNCER_IMAGE = RESOURCE_PATH + "img.png";
    public static final int BOUNCER_SIZE = 20;
    public static int BOUNCER_SPEED = 50;
    public static int BOUNCER_CONSTANT_X = 3;
    public static int BOUNCER_CONSTANT_Y = 5;
    public static final Paint BLOCK50_COLOR = Color.CORNFLOWERBLUE;
    public static int BLOCK50_SIZE = 20;
    public static final Paint BLOCK100_COLOR = Color.DARKRED;
    public static final double BLOCK100_RATE = 1.1;
    public static int BLOCK100_SIZE = 30;
    public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    public static final Paint PADDLE_COLOR = Color.SEAGREEN;
    public static final int PADDLE_LENGTH = 80;
    public static final int PADDLE_WIDTH = (int) ( PADDLE_LENGTH / 10);
    public static final int PADDLE_SPEED = 20;
    private ImageView myBouncer;
    //private MainScreen screen1;
    //private Rectangle block50;

    private Rectangle myPaddle;
    private int directionX;
    private int directionY;
    private int score;
    public static Text scorenumber;
    public static Text livesremaining;
    private int lives;
    private Group root;
    private ArrayList<Rectangle> blocks50 = new ArrayList<>();
    private ArrayList<Rectangle> blocks100 = new ArrayList<>();
    private Scene scene;

    public Scene setupGame(int width, int height, Paint background) {

        root = new Group();
        directionX = 1;
        directionY = 1;

        //Group root = new Group();
        Image image = new Image(getClass().getResourceAsStream(BOUNCER_IMAGE));
        myBouncer = new ImageView(image);
        myBouncer.setFitWidth(BOUNCER_SIZE);
        myBouncer.setFitHeight(BOUNCER_SIZE);
        Random rand = new Random();

        int n2x = rand.nextInt(Main.SIZE);
        int n2y = rand.nextInt(Main.SIZE);
        myBouncer.setX(Main.SIZE / 2.0 - myBouncer.getBoundsInLocal().getWidth() / 2);
        myBouncer.setY(Main.SIZE / 2.0 - myBouncer.getBoundsInLocal().getHeight() / 2);
        // make some shapes and set their properties

        //block100 = new Rectangle(n2x, n2y, BLOCK100_SIZE, BLOCK100_SIZE);
        //block100.setFill(BLOCK100_COLOR);
        // ADDING PADDLE
        myPaddle = new Rectangle(width / 2.0 - PADDLE_LENGTH / 2.0, height - 50, PADDLE_LENGTH, PADDLE_WIDTH);
        myPaddle.setFill(PADDLE_COLOR);
        //ADDING SCORE
        Text scorestring = new Text(10, 20, "SCORE:");
        scorestring.setFill(Color.DARKRED);
        scorestring.setFont(new Font(20));
        scorenumber = new Text(100, 20, String.valueOf(score));
        scorenumber.setFill(Color.DARKRED);
        scorenumber.setFont(new Font(20));
        //ADDING LIVES
        Text livestring =new Text( 10, 50, "LIVES:");
        livestring.setFill(Color.DARKRED);
        livestring.setFont(new Font(20));
        lives = 3;
        livesremaining = new Text(100 , 50 , String.valueOf(lives));
        livesremaining.setFill(Color.DARKRED);
        livesremaining.setFont(new Font(20));
        //Button okButton = new Button("Start");
        //root.getChildren().add(okButton);
        root.getChildren().add(myBouncer);
        root.getChildren().add(myPaddle);
        root.getChildren().add(scorestring);
        root.getChildren().add(scorenumber);
        root.getChildren().add(livestring);
        root.getChildren().add(livesremaining);
        createBlock50();
        createBlock100();

        // create a place to see the shapes
        scene = new Scene(root, width, height, background);
        // respond to input
        //handlePaddlePosition();
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        //scene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        return scene;
    }

    public void step(double elapsedTime) {
        System.out.println(blocks100);

        myBouncer.setX(myBouncer.getX() + BOUNCER_SPEED * elapsedTime * directionX * BOUNCER_CONSTANT_X);
        myBouncer.setY(myBouncer.getY() + BOUNCER_SPEED * elapsedTime * directionY * BOUNCER_CONSTANT_Y);

        if(!blocks50.isEmpty()){
            blocks50.removeIf(brick -> handleblock50collision(brick));
        }
        if(!blocks100.isEmpty()){
            blocks100.removeIf(brick -> handleblock100collision(brick));
        }


        if (myBouncer.getX() <= 0 || myBouncer.getX() >= Main.SIZE - myBouncer.getFitWidth()) {
            directionX = -1 * directionX;
        }
        if (myBouncer.getY() <= 0 || myBouncer.getY() >= Main.SIZE - myBouncer.getFitHeight()) {
            if(myBouncer.getY() >= Main.SIZE - myBouncer.getFitHeight()){
                Score.updateLives(livesremaining);
            }
            directionY = -1 * directionY;
        }

        // check for collisions

        if (isIntersecting(myBouncer, myPaddle)) {
//            myGrower.setScaleX(1);
//            myGrower.setScaleY(1);
            // ADD IMPLEMENTATION FOR HOW THE BALL REFLECTS DEPENDING ON WHERE IT HITS THE PADDLE
            bounceBallBricks(myBouncer, myPaddle);
        }
        if (Integer.parseInt(livesremaining.getText()) == 0){


        }
    }

    public boolean handleblock50collision(Rectangle block50){
        if (isIntersecting(myBouncer, block50)) {
            bounceBallBricks(myBouncer, block50);
            Score.updateScore(scorenumber, 50);
            removeBrick(block50);
            return true;
        }
        return false;
    }
    public boolean handleblock100collision(Rectangle block100){
        if (isIntersecting(myBouncer, block100)) {
            bounceBallBricks(myBouncer, block100);
            Score.updateScore(scorenumber, 100);
            int numberofhits = Integer.parseInt(block100.getId());
            numberofhits++;
            block100.setId(String.valueOf(numberofhits));
            if(numberofhits == 2){
                removeBrick(block100);
                return true;
            }
        }
        return false;
    }


    // What to do each time a key is pressed
    private void handleKeyInput(KeyCode code) {

        if (code == KeyCode.RIGHT && (myPaddle.getX() + PADDLE_LENGTH< Main.SIZE)) {
            myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT &&(myPaddle.getX()> 0)) {
            myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
        }
        else if (code == KeyCode.UP &&(myPaddle.getY()>(4*Main.SIZE) /5)) {
            myPaddle.setY(myPaddle.getY() - PADDLE_SPEED);
        }
        else if (code == KeyCode.DOWN && ((myPaddle.getY() + PADDLE_WIDTH)< Main.SIZE)) {
            myPaddle.setY(myPaddle.getY() + PADDLE_SPEED);
        }
        else if (code == KeyCode.E){
            Cheats.cheatlives(livesremaining);
        }
        else if (code == KeyCode.S){
            Cheats.addpoints(scorenumber);
        }
    }

//    public void handlePaddlePosition(){
//        Bounds bounds = root.localToScreen(root.getBoundsInLocal());
//        double sceneXPos = 0;
//
//        double xPos = robot.getMouseX();
//
//
//        if(xPos >= sceneXPos + (PADDLE_LENGTH/2) && xPos <= (sceneXPos +Main.SIZE) - (PADDLE_LENGTH/2)){
//            myPaddle.setLayoutX(xPos - sceneXPos - (PADDLE_LENGTH/2));
//        } else if (xPos < sceneXPos + (PADDLE_LENGTH/2)){
//            myPaddle.setLayoutX(0);
//        } else if (xPos > (sceneXPos + Main.SIZE) - (PADDLE_LENGTH/2)){
//
//            myPaddle.setLayoutX(Main.SIZE - PADDLE_LENGTH);
//        }
//    }
    public void createBlock50(){
        double width = Main.SIZE;
        double height = Main.SIZE;
        int spaceCheck = 1;

        for (double i = 0; i < height- (4 * height)/5 ; i = i + 50){
            for (double j = width; j > 0; j = j-25){
                if(spaceCheck %2 == 0){
                    Rectangle brick = new Rectangle(j, i, BLOCK50_SIZE,BLOCK50_SIZE);
                    brick.setFill(BLOCK50_COLOR);
                    root.getChildren().add(brick);
                    blocks50.add(brick);

                }
                spaceCheck++;
            }
        }
    }

    public void createBlock100(){
        double width = Main.SIZE;
        double height = Main.SIZE;
        int spaceCheck = 1;

        for (double i = height- (4 * height)/5; i < height- (3 * height)/5 ; i = i + 50){
            for (double j = width; j > 0; j = j-25){
                if(spaceCheck %2 == 0){
                    Rectangle brick = new Rectangle(j, i, BLOCK100_SIZE,BLOCK100_SIZE);
                    brick.setId("0");
                    brick.setFill(BLOCK100_COLOR);
                    root.getChildren().add(brick);
                    blocks100.add(brick);

                }
                spaceCheck++;
            }
        }
    }

    public void removeBrick(Rectangle brick){
        root.getChildren().remove(brick);
    }
    //Gets the specific rectangle
//    public Rectangle getRect(){
//
//    }

    // What to do each time a key is pressed
//    private void handleMouseInput(double x, double y) {
//        if (block100.contains(x, y)) {
//            block100.setScaleX(block100.getScaleX() * BLOCK100_RATE);
//            block100.setScaleY(block100.getScaleY() * BLOCK100_RATE);
//        }
//    }

    // Name for a potentially complex comparison to make code more readable
    private boolean isIntersecting(ImageView a, Rectangle b) {
        // with images can only check bounding box (as it is calculated in container with other objects)
        return b.getBoundsInParent().intersects(a.getBoundsInParent());
        // with shapes, can check precisely (in this case, it is easy because the image is circular)
//        Shape bouncerBounds = new Circle(a.getX() + a.getFitWidth() / 2,
//                                       a.getY() + a.getFitHeight() / 2,
//                                       a.getFitWidth() / 2 - BOUNCER_SIZE / 20);
//        return ! Shape.intersect(bouncerBounds, b).getBoundsInLocal().isEmpty();
    }
    private void bounceBallBricks(ImageView ball, Rectangle brick){
        if (ball.getX() + BOUNCER_SIZE/2 < brick.getX()){
            directionX = -1;
        }
        //intersects from right
        else if (ball.getX() + BOUNCER_SIZE/2 > brick.getX() + brick.getWidth()) {
            directionX = 1;
        }
        //intersects from top
        else if (ball.getY() + BOUNCER_SIZE/2 < brick.getY()){
            directionY = -1;
        }
        //intersects from the bottom
        else if (ball.getY() + BOUNCER_SIZE/2 > brick.getY() + brick.getHeight()) {
            directionY = 1;
        }
    }

}
