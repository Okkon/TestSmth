import javafx.scene.paint.Color;

/**
 * Created by Олег on 31.01.2018.
 */
public class Player {

    private int score = 0;
    private String name = "default Name";
    private Color color = Color.ALICEBLUE;

    public Color getColor() {
        return color;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
