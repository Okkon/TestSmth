package logic;

public class IsNearFilter<T extends PlaceHaving> extends AbstractFilter<T> {
    private static final IsNearFilter INSTANCE = new IsNearFilter();
    private PlaceHaving placeHavingObject;

    private IsNearFilter() {
    }

    public static IsNearFilter getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isOk(T aim) {
        return this.placeHavingObject.getPlace().isLinkedWith(aim.getPlace());
    }

    public IsNearFilter<T> setPlaceHavingObject(PlaceHaving placeHavingObject) {
        this.placeHavingObject = placeHavingObject;
        return this;
    }
}
