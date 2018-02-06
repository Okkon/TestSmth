import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import logic.GMod;
import logic.GUnit;
import logic.Skill;
import logic.UnitType;
import logic.events.ActionSelectionEvent;

import java.util.ArrayList;
import java.util.List;

public class UnitInfoPanel extends GridPane {

    private GUnit unit;
    private Label hpLabel;
    private Label mpLabel;

    public UnitInfoPanel() {
        super();
        getStyleClass().add("unitPanel");
    }

    public void setObjType(UnitType unitType) {
        getChildren().clear();
        setVgap(5);
        if (unitType == null) {
            setVisible(false);
            return;
        }
        setVisible(true);
//        setGridLinesVisible(true);

        final TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setText("TYPE DESCRIPTION OF " + unitType.getTypeName());
        textArea.setEditable(false);
        textArea.setPrefWidth(120);

        ListView<GMod> modListView = new ListView<GMod>();
        ObservableList<GMod> items = FXCollections.observableArrayList(unitType.getMods());
        modListView.setItems(items);
        modListView.setMaxHeight(75);
        modListView.setCellFactory(new Callback<ListView<GMod>, ListCell<GMod>>() {
            @Override
            public ListCell<GMod> call(ListView<GMod> gModListView) {
                return new ListCell<GMod>() {
                    @Override
                    protected void updateItem(GMod gMod, boolean b) {
                        super.updateItem(gMod, b);
                        setText(gMod == null ? "" : gMod.getName());
                    }

                    @Override
                    public void updateSelected(boolean b) {
                        super.updateSelected(b);
                        if (b) {
                            textArea.setText("TYPE DESCRIPTION OF " + getItem().getClass().getSimpleName());
                        }
                    }
                };
            }
        });

        HBox skillsBox = new HBox(10);

//        final boolean belongsToActivePlayer = GameModel.MODEL.getActivePlayer().isOwnerFor(unit);
        final List<Skill> unitSkills = new ArrayList<Skill>();
        unitSkills.addAll(unitType.getSkills());
//        unitSkills.addAll(unit.getExtraSkills());
        for (final Skill skill : unitSkills) {
//            skill.setActor(unit);
            final Button button = new Button();
            Image img = getSkillImage(skill);
            final ImageView imageView = new ImageView(img);
            imageView.setPreserveRatio(true);
            final int buttonSize = 55;
            imageView.setFitWidth(buttonSize);
            button.setGraphic(imageView);
            button.setOnMousePressed(mouseEvent -> {
                if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                    textArea.setText(skill.getDescription());
                }
            });
            /*if (belongsToActivePlayer && unit.getPlace() != null) {
                button.setOnAction(actionEvent -> GameModel.MODEL.setAction(skill));
            }*/
            button.setOnAction(actionEvent -> {
                skill.setActor(unit);
                new ActionSelectionEvent(skill).process();
            });
            skillsBox.getChildren().add(button);
        }

        final Image image = ImageHelper.getImage(unitType);
        final ImageView imageView = new ImageView(image);
        imageView.setFitWidth(120);
        imageView.setFitHeight(180);
        imageView.setPreserveRatio(true);

        add(imageView, 0, 0, 1, 5);
        add(new Label("Name: "), 1, 0);
        add(new Label(unitType.getTypeName()), 2, 0);
        add(new Label("HP:"), 1, 1);
        hpLabel = new Label(String.valueOf(unitType.getMaxHp()));
        add(hpLabel, 2, 1);
        add(new Label("MP:"), 1, 2);
        mpLabel = new Label(String.valueOf(unitType.getMaxMp()));
        add(mpLabel, 2, 2);
        add(skillsBox, 1, 3, REMAINING, 1);
        add(modListView, 1, 4, REMAINING, 1);
        add(textArea, 0, 5, REMAINING, 1);

    }

    public Image getSkillImage(Skill skill) {
        final String imagePath = String.format("file:res/img/skills/%s.jpg", skill.getClass().getSimpleName().toLowerCase());
        Image image = new Image(imagePath);
        if (image.getPixelReader() == null) {
            image = new Image("file:res/img/skills/unknown.jpg");
        }
        return image;
    }

    public void setUnit(GUnit unit) {
        setUnitLocal(unit);
        setObjType(unit.getType());
        hpLabel.setText(String.format("%s/%s", unit.getHp(), unit.getMaxHp()));
        mpLabel.setText(String.format("%s/%s", unit.getMp(), unit.getMaxMp()));
    }

    public void setUnitLocal(GUnit unitLocal) {
        this.unit = unitLocal;
    }
}
