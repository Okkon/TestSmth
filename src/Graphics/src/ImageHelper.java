import javafx.scene.image.Image;
import logic.UnitType;

public class ImageHelper {
    public static Image getImage(UnitType type) {
        final String imagePath = String.format("file:res/img/units/%s.bmp", type.getTypeName().toLowerCase());
        return new Image(imagePath);
    }

    /*public static Image getImage(Tower tower, boolean isMain) {
        final String imagePath = String.format("file:res/img/units/%s.jpg", isMain ? "maintower" : "tower");
        return new Image(imagePath);
    }*/

    public static Image getPlayerImage(String playerName) {
        final String imagePath = String.format("file:res/img/players/%s.jpg", playerName);
        return new Image(imagePath);
    }

    public static Image getImage(Player player) {
        final String imagePath = String.format("file:res/img/players/%s.jpg", "bar");
        return new Image(imagePath);
    }
}
