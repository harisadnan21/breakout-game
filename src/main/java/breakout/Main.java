package breakout;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;


/**
 * Feel free to completely change this code or delete it entirely.
 *
 * @author Haris Adnan
 */
public class Main extends Application {
    // useful names for constant values used
    public static final String TITLE = "Example JavaFX Animation";
    public static final int SIZE = 750;


    /**
     * Initialize what will be displayed.
     */
    @Override
    public void start (Stage stage) {
        Circle shape = new Circle(SIZE / 2 , SIZE / 2, 14);
        shape.setFill(Color.LIGHTSTEELBLUE);

        Group root = new Group();
        root.getChildren().add(shape);

        Scene scene = new Scene(root, SIZE, SIZE, Color.DARKBLUE);
        stage.setScene(scene);

        stage.setTitle(TITLE);
        stage.show();
    }
}
