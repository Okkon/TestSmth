public interface GAction<T> {
    void cancel();

    void aimSelectionStep();

    void perform();

    void tryToSelect(Object obj);
}
