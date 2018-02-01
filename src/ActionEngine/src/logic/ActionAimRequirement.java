package logic;

import java.util.Arrays;
import java.util.List;


public class ActionAimRequirement {
    private String name;
    private List<GFilter> filters;
    private Object selectedAim;

    @Override
    public String toString() {
        return name + " : " + filters.toString();
    }

    public ActionAimRequirement(String aimName, GFilter[] filters) {
        this.name = aimName;
        this.filters = Arrays.asList(filters);
    }

    public <T> T getSelectedAim() {
        return (T) selectedAim;
    }

    public List<GFilter> getFilters() {
        return filters;
    }

    public void setSelectedAim(Object selectedAim) {
        this.selectedAim = selectedAim;
    }
}
