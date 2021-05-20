package it.unitn.disi.lingprogmod1.deme3.SlotHeader;

import javafx.scene.layout.VBox;

/**
 * Stylish Slot Machine Header ({@link SlotHeaderScriptLabel} + {@link SlotHeaderLabel})
 */
public class SlotHeader extends VBox {
    public SlotHeader() {
        super();
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setStyle("-fx-background-color: #790D00");
        //setBackground(new Background(new BackgroundFill(Paint.valueOf("#790D00"), new CornerRadii(0), new Insets(0))));

        SlotHeaderScriptLabel scriptLabel = new SlotHeaderScriptLabel("super");
        SlotHeaderLabel label = new SlotHeaderLabel("SLOTS");

        getChildren().addAll(scriptLabel, label);
    }
}
