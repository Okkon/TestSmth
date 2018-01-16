package logic;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public static final Player NEUTRAL = new Player("Neutral", Color.GREY);
    private String name;
    private int score;
    private Color color;
    private List<GObj> availableUnits = new ArrayList<GObj>();
    private boolean AI;
    private Image image;

    @Override
    public String toString() {
        return name + String.format("(%d)", score);
    }

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.score = 0;
        this.AI = false;
    }

    public Color getColor() {
        return color;
    }

    public List<GObj> getAvailableUnits() {
        return availableUnits;
    }

    public void score(int point) {
        score += point;
    }

    public String getName() {
        return name;
    }

    public boolean isAI() {
        return AI;
    }

    public void setAI(boolean AI) {
        this.AI = AI;
    }

    public int getScore() {
        return score;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}