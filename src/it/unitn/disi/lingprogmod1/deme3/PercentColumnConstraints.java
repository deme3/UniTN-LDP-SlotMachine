package it.unitn.disi.lingprogmod1.deme3;

import javafx.scene.layout.ColumnConstraints;

public class PercentColumnConstraints extends ColumnConstraints {
    public PercentColumnConstraints(double percentage) {
        super();
        setPercentWidth(percentage);
    }
}
