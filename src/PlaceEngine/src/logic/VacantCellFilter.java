package logic;

import tools.PEConst;

public class VacantCellFilter<T> extends AbstractFilter<T> {
    private static final VacantCellFilter INSTANCE = new VacantCellFilter();

    private VacantCellFilter() {
    }

    public static VacantCellFilter getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isOk(T cell) {
        return PEConst.CELL_FILTER.isOk(cell) && ((GameCell) cell).getObj() == null;
    }
}
