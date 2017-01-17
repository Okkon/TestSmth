package com.company.myPackage.Events;

import com.company.myPackage.AbstractGEvent;
import com.company.myPackage.GObject;
import com.company.myPackage.XY;

/**
 * Created by olko1016 on 12/14/2016.
 */
public class ShiftEvent extends AbstractGEvent {

    private GObject owner;
    private XY shiftDirection;

    public ShiftEvent setOwner(GObject owner) {
        this.owner = owner;
        return this;
    }

    public XY getShiftDirection() {
        return shiftDirection;
    }

    public ShiftEvent setShiftDirection(XY shiftDirection) {
        this.shiftDirection = shiftDirection;
        return this;
    }

    @Override
    protected void performAction() {
        getOwner().setPlace(owner.getPlace().changeX(shiftDirection.getX()).changeY(shiftDirection.getY()));
        getOwner().getVisualizer().shift(shiftDirection);
    }


    public GObject getOwner() {
        return owner;
    }
}
