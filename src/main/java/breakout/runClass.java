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
    public static final int BOUNCER_SIZE = 40;
    public static int BOUNCER_SPEED = 50;
    public static int BOUNCER_CONSTANT_X = 5;
    public static int BOUNCER_CONSTANT_Y = 7;
    public static final Paint MOVER_COLOR = Color.CORNFLOWERBLUE;
    public static int MOVER_SIZE = 50;
    public static final int MOVER_SPEED = 8;
    public static final Paint GROWER_COLOR = Color.DARKRED;
    public static final double GROWER_RATE = 1.1;
    public static int GROWER_SIZE = 50;
    public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    public static final Paint PADDLE_COLOR = Color.SEAGREEN;
    public static final int PADDLE_LENGTH = 140;
    public static final int PADDLE_WIDTH = (int) ( PADDLE_LENGTH / 10);
    public static final int PADDLE_SPEED = 20;
    private ImageView myBouncer;
    //private MainScreen screen1;
    private Rectangle myMover;
    private Rectangle myGrower;
    private Rectangle myPaddle;
    private int directionX;
    private int directionY;
    private int score;
    public static Text text2;


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
        myMover = new Rectangle(n1x, n1y, MOVER_SIZE, MOVER_SIZE);
        myMover.setFill(MOVER_COLOR);
        myGrower = new Rectangle(n2x, n2y, GROWER_SIZE, GROWER_SIZE);
        myGrower.setFill(GROWER_COLOR);
        // ADDING PADDLE
        myPaddle = new Rectangle(width / 2.0 - PADDLE_LENGTH / 2.0, height - 50, PADDLE_LENGTH, PADDLE_WIDTH);
        myPaddle.setFill(PADDLE_COLOR);
        //ADDING SCORE
        Text text1 = new Text(10, 20, "SCORE:");
        text1.setFill(Color.DARKRED);
        text1.setFont(new Font(20));
        text2 = new Text(100, 20, String.valueOf(score));
        text2.setFill(Color.DARKRED);
        text2.setFont(new Font(20));
        //text.setText("The quick brown fox jumps over the lazy dog");


        // order added to the group is the order in which they are drawn
        root.getChildren().add(myBouncer);
        root.getChildren().add(myMover);
        root.getChildren().add(myGrower);
        root.getChildren().add(myPaddle);
        root.getChildren().add(text1);
        root.getChildren().add(text2);

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
            directionY = -1 * directionY;
        }

        // check for collisions
        if (isIntersecting(myBouncer, myMover)) {
            bounceBallBrick(myBouncer, myMover);
            Score.updateScore(text2, 50);
        }
        if (isIntersecting(myBouncer, myGrower)) {
//            myGrower.setScaleX(1);
//            myGrower.setScaleY(1);
            bounceBallBrick(myBouncer, myGrower);
            Score.updateScore(text2, 100);
        }

        if (isIntersecting(myBouncer, myPaddle)) {
//            myGrower.setScaleX(1);
//            myGrower.setScaleY(1);
            // ADD IMPLEMENTATION FOR HOW THE BALL REFLECTS DEPENDING ON WHERE IT HITS THE PADDLE
            bounceBallBrick(myBouncer, myPaddle);
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
        if (myGrower.contains(x, y)) {
            myGrower.setScaleX(myGrower.getScaleX() * GROWER_RATE);
            myGrower.setScaleY(myGrower.getScaleY() * GROWER_RATE);
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
    private void bounceBallBrick(ImageView ball, Rectangle brick){
        //intersects from left
        if (ball.getX() + brick.getWidth()/2 < brick.getX()){
            directionX = -1;
        }
        //intersects from right
        else if (ball.getX() + brick.getWidth()/2 > brick.getX() + brick.getWidth()) {
            directionX = 1;
        }
        //intersects from top
        else if (ball.getY() + brick.getHeight()/2 < brick.getY()){
            directionY = -1;
        }
        //intersects from the bottom
        else if (ball.getY() + brick.getHeight()/2 > brick.getY() + brick.getHeight()) {
            directionY = 1;
        }

    }
    private void bounceBallPaddle(ImageView ball, Rectangle brick){
        //intersects from left
        if (ball.getX() + brick.getWidth()/2 < brick.getX()){
            directionX = -1;
        }
        //intersects from right
        else if (ball.getX() + brick.getWidth()/2 > brick.getX() + brick.getWidth()) {
            directionX = 1;
        }
        //intersects from top
        else if (ball.getY() + brick.getHeight()/2 < brick.getY()){
            // if ball hits left third
            if (ball.getX() + ball.getFitWidth()< brick.getX() + brick.getWidth() / 3){
                directionX = -1;
            }
            //if ball hits right third
            if(ball.getX() + ball.getFitWidth()>brick.getX() + 2* (brick.getWidth() / 3)){
                directionX = 1;
            }
            directionY = -1;

        }
        //intersects from the bottom
        else if (ball.getY() + brick.getHeight()/2 > brick.getY() + brick.getHeight()) {
            directionY = 1;
        }

    }

}
