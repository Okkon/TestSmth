package logic;

import utils.XY;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GameCell implements PlaceHaving {
    //    private final ArrayList<logic.CellLink> cellLinks = new ArrayList<>();
    private Map<GameCell, Integer> linkedCells = new HashMap<>();
    private XY xy;
    private PlaceHaving obj;

    public GameCell(XY xy) {
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
        if (linkedCells.containsKey(cell)) {
            return;
        }
        linkedCells.put(cell, length);
        cell.linkedCells.put(this, length);
    }

    @Override
    public GameCell getPlace() {
        return this;
    }

    public int getDistanceToCell(GameCell toCell) {
        return linkedCells.get(toCell);
    }

    public <T extends GameCell> boolean isLinkedWith(T cell) {
        return linkedCells.keySet().contains(cell);
    }

    public Collection<GameCell> getLinkedCells() {
        return linkedCells.keySet();
    }

    public Map<GameCell, Integer> getLinks() {
        return linkedCells;
    }
}