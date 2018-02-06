package logic;

public abstract class ModeActivationEvent extends AbstractEvent {
    private final GMod mod;

    public ModeActivationEvent(GMod mod) {
        this.mod = mod;
    }

    public GMod getMod() {
        return mod;
    }

    @Override
    public String toString() {
        return "Mode activated: " + mod;
    }
}
