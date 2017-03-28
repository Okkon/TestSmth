package com.company.myLinker;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class Joint extends Circle {
    double angle;
    private Line limb;

    public Joint(double centerX, double centerY, double radius, double angle) {
        super(centerX, centerY, radius);
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setControlledLimb(Limb limb) {
        this.limb = limb;
        limb.setControllingJoint(this);
    }

    public void changeAngle(double v) {
        angle += v;
    }

    public Line getLimb() {
        return limb;
    }
}
