package breakout;

import javafx.scene.shape.Rectangle;

public class Rectangleobj {
  public Rectangle Rect;
  public int numberofhits;
  //constructor
  public Rectangleobj(double x, double y, double width, double height, int num) {
    this.numberofhits = num;
    this.Rect = new Rectangle(x,  y, width, height);
  }
  public int getHits(){
    return numberofhits;
  }




}
