import java.util.*;

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
                if (board.get(rightCell) != null) {
                    cell.link(rightCell, XY.straightLength);
                } else if (board.get(bottomCell) != null) {
                    cell.link(bottomCell, XY.straightLength);
                } else if (board.get(diagonalCell) != null) {
                    cell.link(diagonalCell, XY.diagonalLength);
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

    public Collection<GameCell> getAllCells(List<GFilter> aimFilters) {
        return filterCollection(aimFilters, board.values());
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

    public Collection<GObj> getUnitList(List<GFilter> aimFilters) {
        return filterCollection(aimFilters, unitList);
    }
}
