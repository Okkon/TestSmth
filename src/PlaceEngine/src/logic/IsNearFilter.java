package logic;

public class IsNearFilter<T extends PlaceHaving> extends AbstractFilter<T> {
    private static final IsNearFilter INSTANCE = new IsNearFilter();
    private PlaceHaving place;

    private IsNearFilter() {
    }

    public static IsNearFilter getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "Is near to " + place;
    }

    @Override
    public boolean isOk(T aim) {
        return this.place.getPlace().isLinkedWith(aim.getPlace());
    }

    public IsNearFilter<T> setPlace(PlaceHaving place) {
        this.place = place;
        return this;
    }
}
