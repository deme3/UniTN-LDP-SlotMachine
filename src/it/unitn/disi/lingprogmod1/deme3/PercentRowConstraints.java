package it.unitn.disi.lingprogmod1.deme3;

import javafx.scene.layout.RowConstraints;

public class PercentRowConstraints extends RowConstraints {
    public PercentRowConstraints(double percentage) {
        super();
        setPercentHeight(percentage);
    }
}
