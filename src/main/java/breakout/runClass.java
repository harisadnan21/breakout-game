package breakout;


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
import java.util.Random;
import javafx.scene.text.*;
/**
 * The class that setups the breakout window
 *
 * @author Haris Adnan
 */

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
    private Rectangle block50;
    private Rectangle block100;
    private Rectangle myPaddle;
    private int directionX;
    private int directionY;
    private int score;
    public static Text scorenumber;
    public static Text livesremaining;
    private int lives;



    public Scene setupGame(int width, int height, Paint background) {
        directionX = 1;
        directionY = 1;
        Group root = new Group();
        Image image = new Image(getClass().getResourceAsStream(BOUNCER_IMAGE));
        myBouncer = new ImageView(image);
        myBouncer.setFitWidth(BOUNCER_SIZE);
        myBouncer.setFitHeight(BOUNCER_SIZE);
        Random rand = new Random();
        int n1x = rand.nextInt(Main.SIZE);
        int n1y = rand.nextInt(Main.SIZE);
        int n2x = rand.nextInt(Main.SIZE);
        int n2y = rand.nextInt(Main.SIZE);
        myBouncer.setX(Main.SIZE / 2.0 - myBouncer.getBoundsInLocal().getWidth() / 2);
        myBouncer.setY(Main.SIZE / 2.0 - myBouncer.getBoundsInLocal().getHeight() / 2);
        // make some shapes and set their properties

        block50 = new Rectangle(n1x, n1y, BLOCK50_SIZE, BLOCK50_SIZE);
        block50.setFill(BLOCK50_COLOR);
        block100 = new Rectangle(n2x, n2y, BLOCK100_SIZE, BLOCK100_SIZE);
        block100.setFill(BLOCK100_COLOR);
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
        //text.setText("The quick brown fox jumps over the lazy dog");

        // order added to the group is the order in which they are drawn
        root.getChildren().add(myBouncer);
        root.getChildren().add(block50);
        root.getChildren().add(block100);
        root.getChildren().add(myPaddle);
        root.getChildren().add(scorestring);
        root.getChildren().add(scorenumber);
        root.getChildren().add(livestring);
        root.getChildren().add(livesremaining);

        // create a place to see the shapes
        Scene scene = new Scene(root, width, height, background);
        // respond to input
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        scene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        return scene;
    }

    public void step(double elapsedTime) {


        myBouncer.setX(myBouncer.getX() + BOUNCER_SPEED * elapsedTime * directionX * BOUNCER_CONSTANT_X);
        myBouncer.setY(myBouncer.getY() + BOUNCER_SPEED * elapsedTime * directionY * BOUNCER_CONSTANT_Y);


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
        if (isIntersecting(myBouncer, block50)) {
            bounceBallBricks(myBouncer, block50);
            Score.updateScore(scorenumber, 50);
        }
        if (isIntersecting(myBouncer, block100)) {
//            myGrower.setScaleX(1);
//            myGrower.setScaleY(1);
            bounceBallBricks(myBouncer, block100);
            Score.updateScore(scorenumber, 100);
        }

        if (isIntersecting(myBouncer, myPaddle)) {
//            myGrower.setScaleX(1);
//            myGrower.setScaleY(1);
            // ADD IMPLEMENTATION FOR HOW THE BALL REFLECTS DEPENDING ON WHERE IT HITS THE PADDLE
            bounceBallBricks(myBouncer, myPaddle);
        }
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
    }

    // What to do each time a key is pressed
    private void handleMouseInput(double x, double y) {
        if (block100.contains(x, y)) {
            block100.setScaleX(block100.getScaleX() * BLOCK100_RATE);
            block100.setScaleY(block100.getScaleY() * BLOCK100_RATE);
        }
    }

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
//    private void bounceBallBrick(ImageView ball, Rectangle brick){
//        //intersects from left
//        if (ball.getX() + brick.getWidth()/2 < brick.getX()){
//            directionX = -1;
//        }
//        //intersects from right
//        else if (ball.getX() + brick.getWidth()/2 > brick.getX() + brick.getWidth()) {
//            directionX = 1;
//        }
//        //intersects from top
//        else if (ball.getY() + brick.getHeight()/2 < brick.getY()){
//            directionY = -1;
//        }
//        //intersects from the bottom
//        else if (ball.getY() + brick.getHeight()/2 > brick.getY() + brick.getHeight()) {
//            directionY = 1;
//        }

   // }
//    private void bounceBallPaddle(ImageView ball, Rectangle brick){
//        //intersects from left
//        if (ball.getX() + brick.getWidth()/2 < brick.getX()){
//            directionX = -1;
//        }
//        //intersects from right
//        else if (ball.getX() + brick.getWidth()/2 > brick.getX() + brick.getWidth()) {
//            directionX = 1;
//        }
//        //intersects from top
//        else if (ball.getY() + brick.getHeight()/2 < brick.getY()){
//            // if ball hits left third
//            if (ball.getX() + ball.getFitWidth()< brick.getX() + brick.getWidth() / 3){
//                directionX = -1;
//            }
//            //if ball hits right third
//            if(ball.getX() + ball.getFitWidth()>brick.getX() + 2* (brick.getWidth() / 3)){
//                directionX = 1;
//            }
//            directionY = -1;
//        }
//        //intersects from the bottom
//        else if (ball.getY() + brick.getHeight()/2 > brick.getY() + brick.getHeight()) {
//            directionY = 1;
//        }
//    }
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
