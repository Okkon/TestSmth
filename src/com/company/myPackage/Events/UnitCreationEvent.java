package com.company.myPackage.Events;

import com.company.myPackage.*;
import javafx.scene.paint.Color;

/**
 * Created by olko1016 on 11/22/2016.
 */
public class UnitCreationEvent extends AbstractGEvent {
    private XY xy;
    private Color color;

    public Color getColor() {
        return color;
    }

    public XY getXy() {
        return xy;
    }

    public UnitCreationEvent setXy(XY xy) {
        this.xy = xy;
        return this;
    }

    @Override
    protected void performAction() {
        GObject gObject = new GObject(xy);
        gObject.setVisualizer(new CircleVisualizer(xy));
        GameModel.getInstance().addObject(gObject);
    }

    public UnitCreationEvent setColor(Color color) {
        this.color = color;
        return this;
    }
}
