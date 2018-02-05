package logic;

import utils.XY;

import java.util.*;
import java.util.stream.Collectors;

public class GBoard {
    private static GBoard INSTANCE = new GBoard();

    private GBoard() {
    }

    public static GBoard getInstance() {
        return INSTANCE;
    }

    private Map<XY, GameCell> board = new HashMap<>();
    private List<GObj> unitList = new ArrayList<>();

    public void init(int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                final XY xy = XY.get(i, j);
                board.put(xy, new GameCell(xy));
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                final XY xy = XY.get(i, j);
                GameCell cell = board.get(xy);
                GameCell rightCell = board.get(xy.changeX(1));
                GameCell bottomCell = board.get(xy.changeY(1));
                GameCell diagonalCell = board.get(xy.changeX(1).changeY(1));
                GameCell diagonalCell2 = board.get(xy.changeX(-1).changeY(1));
                if (rightCell != null) {
                    cell.link(rightCell, XY.straightLength);
                }
                if (bottomCell != null) {
                    cell.link(bottomCell, XY.straightLength);
                }
                if (diagonalCell != null) {
                    cell.link(diagonalCell, XY.diagonalLength);
                }
                if (diagonalCell2 != null) {
                    cell.link(diagonalCell2, XY.diagonalLength);
                }
            }
        }
    }

    public GameCell get(XY xy) {
        return board.get(xy);
    }

    public List<GameCell> getNearCells(GameCell cell) {
        List<GameCell> cells = new ArrayList<>();
        XY xy = cell.getXy();
        for (GameCell gameCell : board.values()) {
            if (XY.isNear(xy, gameCell.getXy())) {
                cells.add(gameCell);
            }
        }
        return cells;
    }

    public Collection<GameCell> getAllCells() {
        return board.values();
    }

    public List<GameCell> getAllCells(GFilter... filters) {
        return board.values().stream().filter(gameCell -> {
            for (GFilter filter : filters) {
                if (!filter.isOk(gameCell)) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
    }

    private <T> Collection<T> filterCollection(List<GFilter> aimFilters, Collection<T> values) {
        final Set<T> result = new HashSet<>(values);
        final Iterator<T> iterator = result.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            for (GFilter filter : aimFilters) {
                if (!filter.isOk(next)) {
                    iterator.remove();
                }
            }
        }
        return result;
    }

    public void addUnit(GObj unit) {
        unitList.add(unit);
    }

    public void removeUnit(GObj unit) {
        unitList.add(unit);
    }

    public List<GObj> getObjList() {
        return unitList;
    }
}
