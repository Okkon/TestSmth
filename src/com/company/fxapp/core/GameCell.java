package com.company.fxapp.core;

import com.company.fxapp.utils.XY;

import java.util.ArrayList;

public class GameCell implements PlaceHaving {
    private final ArrayList<CellLink> cellLinks = new ArrayList<>();
    private XY xy;
    private PlaceHaving obj;

    GameCell(XY xy) {
        this.xy = xy;
    }

    public PlaceHaving getObj() {
        return obj;
    }

    public void setObj(PlaceHaving obj) {
        this.obj = obj;
    }

    public XY getXy() {
        return xy;
    }

    @Override
    public String toString() {
        return xy.getX() + ":" + xy.getY();
    }

    public boolean isNotEmpty() {
        return getObj() != null;
    }

    public void link(GameCell cell, int length) {
        cellLinks.add(new CellLink(this, cell, length));
    }

    @Override
    public GameCell getPlace() {
        return this;
    }

    public static GameCell create(int i, int j) {
        return new GameCell(XY.get(i, j));
    }
}