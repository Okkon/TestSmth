package com.company.myPackage;

import com.company.myPackage.Events.ShiftEvent;

/**
 * Created by olko1016 on 12/14/2016.
 */
public class GoSomewhereActivity extends Activity {
    protected static double speed = 5;
    private XY placeToGo;

    @Override
    public void step() {
        double direction = getOwner().getDirection();
        if (placeToGo == null || XY.getDistance(placeToGo, getOwner().getPlace()) < speed) {
            placeToGo = OkHelper.getRandomXY();
            direction = OkHelper.getAngleBetweenPoints(getOwner().getPlace(), placeToGo);
            getOwner().setDirection(direction);
        }
        XY shiftDirection = new XY(
                Math.cos(direction) * speed,
                Math.sin(direction) * speed
        );
        new ShiftEvent().setOwner(getOwner()).setShiftDirection(shiftDirection).process();
    }


}
