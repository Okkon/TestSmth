package com.company.myPackage;

/**
 * Created by olko1016 on 10/21/2016.
 */
public class GameCell {
    private XY xy;

    private GameCell(int x, int y) {
        xy = XY.get(x, y);
    }

    public static GameCell create(int x, int y) {
        final GameCell gameCell = new GameCell(x, y);
        return gameCell;
    }

    public XY getXY() {
        return xy;
    }
}
