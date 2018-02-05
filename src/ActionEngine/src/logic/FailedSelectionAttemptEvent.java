package logic;


public class FailedSelectionAttemptEvent extends AbstractEvent {
    private final GAction action;
    private final GFilter filter;
    private final Object obj;

    public FailedSelectionAttemptEvent(GAction action, GFilter filter, Object obj) {
        this.action = action;
        this.filter = filter;
        this.obj = obj;
    }

    @Override
    protected void perform() {
        //do nothing, just for information
    }

    @Override
    public String toString() {
        return String.format(
                "%s failed : %s does not match %s",
                action,
                obj,
                filter
        );
    }
}
