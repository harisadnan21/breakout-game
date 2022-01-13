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

import java.util.Random;


// Obtain a number between [0 - 49].

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
    public static int BOUNCER_CONSTANT_X = 7;
    public static int BOUNCER_CONSTANT_Y = 10;
    public static final Paint MOVER_COLOR = Color.CORNFLOWERBLUE;
    public static int MOVER_SIZE = 50;
    public static final int MOVER_SPEED = 8;
    public static final Paint GROWER_COLOR = Color.DARKRED;
    public static final double GROWER_RATE = 1.1;
    public static int GROWER_SIZE = 50;
    public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    public static final Paint PADDLE_COLOR = Color.SEAGREEN;
    public static final int PADDLE_SIZE = 70;
    public static final int PADDLE_SPEED = 20;


    private ImageView myBouncer;
    private Rectangle myMover;
    private Rectangle myGrower;
    private Rectangle myPaddle;
    private int directionX = 1;
    private int directionY = 1;

    public Scene setupGame(int width, int height, Paint background) {
        // create one top level collection to organize the things in the scene
        Group root = new Group();
        // make an image based on a file "resource" found using Java's classpath
        Image image = new Image(getClass().getResourceAsStream(BOUNCER_IMAGE));
        myBouncer = new ImageView(image);
        myBouncer.setFitWidth(BOUNCER_SIZE);
        myBouncer.setFitHeight(BOUNCER_SIZE);
        // x and y represent the top left corner, so center it in window by offsetting by the width nd height
        Random rand = new Random();
        int n1x = rand.nextInt(Main.SIZE);
        int n1y = rand.nextInt(Main.SIZE);
        int n2x = rand.nextInt(Main.SIZE);
        int n2y = rand.nextInt(Main.SIZE);
        myBouncer.setX(Main.SIZE / 2 - myBouncer.getBoundsInLocal().getWidth() / 2);
        myBouncer.setY(Main.SIZE / 2 - myBouncer.getBoundsInLocal().getHeight() / 2);
        // make some shapes and set their properties
        myMover = new Rectangle(n1x, n1y, MOVER_SIZE, MOVER_SIZE);
        myMover.setFill(MOVER_COLOR);
        myGrower = new Rectangle(n2x, n2y, GROWER_SIZE, GROWER_SIZE);
        myGrower.setFill(GROWER_COLOR);
        // ADDING PADDLE
        myPaddle = new Rectangle(width / 2 - PADDLE_SIZE / 2, height - 50, PADDLE_SIZE, PADDLE_SIZE / 5);
        myPaddle.setFill(PADDLE_COLOR);

        // order added to the group is the order in which they are drawn
        root.getChildren().add(myBouncer);
        root.getChildren().add(myMover);
        root.getChildren().add(myGrower);
        root.getChildren().add(myPaddle);

        // create a place to see the shapes
        Scene scene = new Scene(root, width, height, background);
        // respond to input
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        scene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        return scene;
    }

    public void step(double elapsedTime) {

        // update "actors" attributes
        // bouncer moves at a "constant" rate now matter how many frames are drawn per second

        myBouncer.setX(myBouncer.getX() + BOUNCER_SPEED * elapsedTime * directionX * BOUNCER_CONSTANT_X);
        myBouncer.setY(myBouncer.getY() + BOUNCER_SPEED * elapsedTime * directionY * BOUNCER_CONSTANT_Y);

        // shapes move at a "variable" rate, faster for higher frame rate
        //myMover.setRotate(myMover.getRotate() - 1);
        //myGrower.setRotate(myGrower.getRotate() + 1);


        if (myBouncer.getX() <= 0 || myBouncer.getX() >= Main.SIZE - myBouncer.getFitWidth()) {
            directionX = -1 * directionX;

        }
        if (myBouncer.getY() <= 0 || myBouncer.getY() >= Main.SIZE - myBouncer.getFitHeight()) {
            directionY = -1 * directionY;
        }
        // myMover.setFill(HIGHLIGHT);

        // check for collisions
        if (isIntersecting(myBouncer, myMover)) {

            //Hit from above or below
//            if((myBouncer.getY() <= myMover.getY() - (myMover.getY()/2)) || ((myBouncer.getY() >= myMover.getY() + (myMover.getY()/2)))){
//                directionY = -1 * directionY;
//            }
//
//
//            //Hit was on left
//            if(myBouncer.getX() < myMover.getX() || myBouncer.getX() > myMover.getX()){
//                directionX = -directionX;
//            }

            if (myBouncer.getX() >= myMover.getX() || myBouncer.getX() <= myMover.getX() + MOVER_SIZE) {
                directionX = -1 * directionX;
            } else if (myBouncer.getY() >= myMover.getY() || myBouncer.getY() <= myMover.getY() + MOVER_SIZE) {
                directionY = -1 * directionY;
            }
        }
//        else {
//            myMover.setFill(MOVER_COLOR);
//        }
        if (isIntersecting(myBouncer, myGrower)) {
//            myGrower.setScaleX(1);
//            myGrower.setScaleY(1);
            if (myBouncer.getX() >= myGrower.getX() || myBouncer.getX() <= myGrower.getX() + MOVER_SIZE) {
                directionX = -1 * directionX;
            } else if (myBouncer.getY() >= myGrower.getY() || myBouncer.getY() <= myGrower.getY() + MOVER_SIZE) {
                directionY = -1 * directionY;
            }


        }
        if (isIntersecting(myBouncer, myPaddle)) {
//            myGrower.setScaleX(1);
//            myGrower.setScaleY(1);
            // ADD IMPLEMENTATION FOR HOW THE BALL REFLECTS DEPENDING ON WHERE IT HITS THE PADDLE


            directionY = -1 * directionY;

        }


    }

    // What to do each time a key is pressed
    private void handleKeyInput(KeyCode code) {
        // NOTE new Java syntax that some prefer (but watch out for the many special cases!)
        //   https://blog.jetbrains.com/idea/2019/02/java-12-and-intellij-idea/
        switch (code) {
            case RIGHT -> myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
            case LEFT -> myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
            case UP -> myPaddle.setY(myPaddle.getY() - PADDLE_SPEED);
            case DOWN -> myPaddle.setY(myPaddle.getY() + PADDLE_SPEED);
        }
        // TYPICAL way to do it, definitely more readable for longer actions
//        if (code == KeyCode.RIGHT) {
//            myMover.setX(myMover.getX() + MOVER_SPEED);
//        }
//        else if (code == KeyCode.LEFT) {
//            myMover.setX(myMover.getX() - MOVER_SPEED);
//        }
//        else if (code == KeyCode.UP) {
//            myMover.setY(myMover.getY() - MOVER_SPEED);
//        }
//        else if (code == KeyCode.DOWN) {
//            myMover.setY(myMover.getY() + MOVER_SPEED);
//        }
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

}
