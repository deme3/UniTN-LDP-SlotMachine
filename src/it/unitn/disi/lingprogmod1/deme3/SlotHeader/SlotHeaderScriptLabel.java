package it.unitn.disi.lingprogmod1.deme3.SlotHeader;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Stylish Header Label with script bold font, comes before {@link SlotHeaderLabel}
 */
public class SlotHeaderScriptLabel extends Text {
    public SlotHeaderScriptLabel(String text) {
        super(text);
        setFill(Paint.valueOf("#F4A929"));
        setFont(new Font("Script MT Bold", 24));
        setStroke(Paint.valueOf("#00000025"));
        setStrokeWidth(2);
        setStyle("-fx-font-weight: bold;");
    }
}
