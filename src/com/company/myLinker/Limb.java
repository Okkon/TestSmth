package com.company.myLinker;

import javafx.scene.shape.Line;

/**
 * Created by olko1016 on 02/14/2017.
 */
public class Limb extends Line {
    private Joint controllingJoint;

    public Limb(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY);
    }

    public void setControllingJoint(Joint controllingJoint) {
        this.controllingJoint = controllingJoint;
    }

    public Joint getControllingJoint() {
        return controllingJoint;
    }
}
