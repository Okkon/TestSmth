import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ActionAim<T> {
    private String name;
    private AimType aimType;
    private List<GFilter<T>> filters;
    private T selectedAim;

    public ActionAim(String aimName, AimType aimType, GFilter<T>[] filters) {
        this.name = aimName;
        this.aimType = aimType;
        this.filters = Arrays.asList(filters);
    }

    public T getSelectedAim() {
        return selectedAim;
    }

    public List<GFilter<T>> getFilters() {
        return filters;
    }

    public List<T> findPossibleAims() {
        List<T> possibleAims = new ArrayList<T>();
        List<GFilter> filters = new ArrayList<>(this.filters);
        //TODO : mafe find aims logic
        /*final GBoard board = GBoard.getInstance();
        if (AimType.Cell.equals(aimType)) {
            possibleAims.addAll((Collection<? extends T>) board.getAllCells(filters));
        } else if (AimType.Object.equals(aimType)) {
            possibleAims.addAll((Collection<? extends T>) board.getUnitList(filters));
        } else if (AimType.ObjectsAndCells.equals(aimType)) {
            possibleAims.add((T) board.getAllCells(filters));
            possibleAims.add((T) board.getUnitList(filters));
        }*/

        return possibleAims;
    }

    public void setSelectedAim(Object selectedAim) {
        this.selectedAim = (T) selectedAim;
    }
}
