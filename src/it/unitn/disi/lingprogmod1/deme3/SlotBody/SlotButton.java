package it.unitn.disi.lingprogmod1.deme3.SlotBody;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Styled button with gradient background and thick borders
 */
public class SlotButton extends Button implements EventHandler<MouseEvent> {
    private static final int BORDER_RADIUS = 8;
    public Color gradientPrimaryStop = Color.valueOf("#A2A2A2");
    public Color gradientSecondaryStop = Color.valueOf("#555555");
    public SlotButton(String text) {
        super(text);

        this.updateBackground();
        setBorder(new Border(new BorderStroke(Paint.valueOf("#000000"), BorderStrokeStyle.SOLID, new CornerRadii(BORDER_RADIUS), new BorderWidths(2))));
        setFont(new Font("Impact", 16));
        setTextFill(Paint.valueOf("#212121"));
        setMinWidth(64);
        setMaxWidth(64);
        setMinHeight(64);
        setMaxHeight(150);
        setWrapText(true);
        setTextAlignment(TextAlignment.CENTER);
        setOnMousePressed(this);
        setOnMouseReleased(this);
    }

    /**
     * Set primary and secondary color for the background {@link LinearGradient}
     * @param primary   Primary color
     * @param secondary Secondary color
     */
    public void setGradientColors(Color primary, Color secondary) {
        this.gradientPrimaryStop = primary;
        this.gradientSecondaryStop = secondary;
        this.updateBackground();
    }

    /**
     * Updates the Button background according to the newest {@link SlotButton#gradientPrimaryStop} and {@link SlotButton#gradientSecondaryStop}
     */
    private void updateBackground() {
        Stop[] stops = new Stop[] { new Stop(0, gradientPrimaryStop), new Stop(1, gradientSecondaryStop) };
        LinearGradient lg = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

        setBackground(new Background(new BackgroundFill(lg, new CornerRadii(BORDER_RADIUS), null)));
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if((mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED
        || mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED)
            && this.isDisabled()) return;

        if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
            Stop[] stops = new Stop[]{ new Stop(0, gradientSecondaryStop), new Stop(1, gradientPrimaryStop) };
            LinearGradient lg = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

            setBackground(new Background(new BackgroundFill(lg, new CornerRadii(BORDER_RADIUS), null)));
        } else if(mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
            Stop[] stops = new Stop[] { new Stop(0, gradientPrimaryStop), new Stop(1, gradientSecondaryStop) };
            LinearGradient lg = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

            setBackground(new Background(new BackgroundFill(lg, new CornerRadii(BORDER_RADIUS), null)));
        }
    }
}
