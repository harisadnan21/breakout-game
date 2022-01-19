package breakout;


import javafx.scene.shape.Rectangle;
/**
 *
 *tried to use this class to make a different typf block that takes multiple hits before getting destroyed, but didnt end up using
 * @author Haris Adnan
 */
//
public class Rectangleobj {

  public static Rectangle Rect;
  public int numberofhits;

  //constructor
  public Rectangleobj(double x, double y, double width, double height, int num) {
    this.numberofhits = num;
    this.Rect = new Rectangle(x, y, width, height);
  }

  public double getX() {
    return Rect.getX();
  }

  public double getY() {
    return Rect.getY();
  }

  public int getHits() {
    return numberofhits;
  }

  public void setHits(int num) {
    numberofhits = num;
  }

  public double getWidth() {
    return Rect.getWidth();
  }

  public static double getHeight() {
    return Rect.getHeight();
  }

  public javafx.geometry.Bounds getBoundsInParent() {
    return Rect.getBoundsInParent();
  }

  public final void setFill(javafx.scene.paint.Paint paint) {
    Rect.setFill(paint);
  }
}


