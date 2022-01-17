package breakout;

import javafx.scene.text.Text;

public class Cheats {
    public Cheats(){

    }
    public static void addpoints(Text scoretext){
            scoretext.setText(String.valueOf(100 + Integer.parseInt(scoretext.getText())));
    }
    public static void cheatlives(Text numberoflives){
        numberoflives.setText(String.valueOf(Integer.parseInt(numberoflives.getText()) +1));

    }
}
