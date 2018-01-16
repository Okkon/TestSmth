package tools;

import logic.ClassFilter;
import logic.GObj;
import logic.GameCell;
import logic.VacantCellFilter;

public class PEConst {
    public static final ClassFilter OBJ_FILTER = ClassFilter.getInstance(GObj.class);
    public static final ClassFilter CELL_FILTER = ClassFilter.getInstance(GameCell.class);
    public static final VacantCellFilter VACANT_CELL_FILTER = VacantCellFilter.getInstance();
}
