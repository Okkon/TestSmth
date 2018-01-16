package logic;

public class VacantCellFilter<T extends GameCell> extends AbstractFilter<T> {
    private static final VacantCellFilter INSTANCE = new VacantCellFilter();

    private VacantCellFilter() {
    }

    public static VacantCellFilter getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isOk(T cell) {
        return cell.getObj() == null;
    }
}
