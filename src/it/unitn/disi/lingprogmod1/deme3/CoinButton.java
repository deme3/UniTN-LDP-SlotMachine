package it.unitn.disi.lingprogmod1.deme3;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;

/**
 * Button styled as a coin
 */
public class CoinButton extends Button implements EventHandler<MouseEvent> {
    private final Color gradientPrimaryStop = Color.valueOf("#64AFFC");
    private final Color gradientSecondaryStop = Color.valueOf("#5595D6");
    private final Paint foregroundColor = Paint.valueOf("#264462");

    /**
     * @param text String the coin will display on its face
     */
    public CoinButton(String text) {
        super(text);
        Stop[] stops = new Stop[] { new Stop(0, gradientPrimaryStop), new Stop(1, gradientSecondaryStop) };
        LinearGradient lg = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

        setStyle("-fx-font-weight: bold; -fx-font-size: 13");
        setCursor(Cursor.HAND);
        setTextFill(foregroundColor);
        setPrefSize(64, 64);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setBackground(new Background(new BackgroundFill(lg, new CornerRadii(1000), null)));
        setBorder(
                new Border(
                        new BorderStroke(
                                gradientSecondaryStop,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(10000),
                                new BorderWidths(2)
                        )));

        addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        this.setVisible(false);
    }
}
