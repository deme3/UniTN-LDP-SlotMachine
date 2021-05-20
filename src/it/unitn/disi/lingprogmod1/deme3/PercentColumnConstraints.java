package it.unitn.disi.lingprogmod1.deme3;

import javafx.scene.layout.ColumnConstraints;

/**
 * Shorthand for ColumnConstraints with relative value
 */
public class PercentColumnConstraints extends ColumnConstraints {
    public PercentColumnConstraints(double percentage) {
        super();
        setPercentWidth(percentage);
    }
}
