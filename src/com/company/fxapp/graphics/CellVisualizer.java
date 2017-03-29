package com.company.fxapp.graphics;

import com.company.fxapp.core.AbstractAction;
import com.company.fxapp.core.GameCell;
import com.company.fxapp.core.GameCore;
import com.company.fxapp.core.PlaceHaving;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellVisualizer extends Rectangle implements Visualizer {
    public CellVisualizer(int x, int y, int width, int height, GameCell cell) {
        super(x, y, width, height);
        setFill(Color.DARKGRAY);
        setStroke(Color.GRAY);
        setOnMouseClicked(
                event -> GameCore.getInstance().press(cell)
        );
    }

    @Override
    public void showSelectionPossibility(AbstractAction<? extends PlaceHaving> action) {
        if (action != null) {
            //place animation
        } else {
            //stop animation
        }

    }
}
