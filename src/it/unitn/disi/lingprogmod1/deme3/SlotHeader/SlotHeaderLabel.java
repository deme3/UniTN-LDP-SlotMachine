package it.unitn.disi.lingprogmod1.deme3.SlotHeader;

import javafx.scene.paint.*;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SlotHeaderLabel extends Text {
    public SlotHeaderLabel(String text) {
        super(text);
        setStyle("-fx-font-weight: bold;");
        Stop[] stops = new Stop[] { new Stop(0, Color.valueOf("#F7B757")), new Stop(1, Color.valueOf("#C51500"))};
        LinearGradient lg = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

        setFill(lg);
        setStroke(Paint.valueOf("#00000025"));
        setStrokeWidth(4);
        setStrokeLineJoin(StrokeLineJoin.ROUND);
        setStrokeType(StrokeType.OUTSIDE);
        setFont(new Font("Georgia", 48));
    }
}
