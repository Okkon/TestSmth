package com.company.myPackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by olko1016 on 10/21/2016.
 */
public class Board {
    private final int width;
    private final int height;
    Map<XY, GameCell> cells = new HashMap<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells.put(XY.get(i, j), GameCell.create(i, j));
            }
        }
        performCellBinding();
    }

    private void performCellBinding() {
        for (Map.Entry<XY, GameCell> entry : cells.entrySet()) {
            final GameCell gameCell = entry.getValue();
            findNeighbours(gameCell);

        }
    }

    Collection<GameCell> findNeighbours(GameCell gameCell) {
        Collection<GameCell> result = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                final GameCell cell = getCell(gameCell.getXY().changeX(i).changeY(j));

            }
        }
        return result;
    }

    private GameCell getCell(XY xy) {
        return cells.get(xy);
    }
}
