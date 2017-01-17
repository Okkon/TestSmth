package com.company.myPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by olko1016 on 11/22/2016.
 */
public class GObject {
    private XY place;

    List<Activity> activities = new ArrayList();
    private double direction;
    private ObjectVisualizer visualizer;

    public GObject(XY xy) {
        this.place = xy;
        activities.add(new GoSomewhereActivity());
        direction = getRandomAngle();
    }

    private double getRandomAngle() {
        return new Random().nextDouble() * Math.PI * 2;
    }

    public void step() {
        Activity activity = activities.iterator().next();
        activity.setOwner(this);
        activity.step();
    }

    public double getDirection() {
        return direction;
    }

    public XY getPlace() {
        return place;
    }

    public void setPlace(XY place) {
        this.place = place;
    }

    public ObjectVisualizer getVisualizer() {
        return visualizer;
    }

    public void setVisualizer(ObjectVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public void setDirection(double direction) {
        this.direction = direction;
        visualizer.setDirection(direction);
    }
}
