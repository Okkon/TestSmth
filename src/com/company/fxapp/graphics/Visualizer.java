package com.company.fxapp.graphics;

import com.company.fxapp.core.AbstractAction;
import com.company.fxapp.core.PlaceHaving;

public interface Visualizer {
    void showSelectionPossibility(AbstractAction<? extends PlaceHaving> action);
}
