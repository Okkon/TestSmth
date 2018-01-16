package logic;

import java.util.Arrays;
import java.util.List;


public class ActionAim<T> {
    private String name;
    private List<GFilter<T>> filters;
    private T selectedAim;

    public ActionAim(String aimName, GFilter<T>[] filters) {
        this.name = aimName;
        this.filters = Arrays.asList(filters);
    }

    public T getSelectedAim() {
        return selectedAim;
    }

    public List<GFilter<T>> getFilters() {
        return filters;
    }

    public void setSelectedAim(Object selectedAim) {
        this.selectedAim = (T) selectedAim;
    }
}
