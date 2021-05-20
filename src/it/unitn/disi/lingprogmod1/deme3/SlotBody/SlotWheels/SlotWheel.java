package it.unitn.disi.lingprogmod1.deme3.SlotBody.SlotWheels;

import it.unitn.disi.lingprogmod1.deme3.Main;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Random;

/**
 * Representation of a single wheel in a Slot Machine barrel
 */
public class SlotWheel extends StackPane implements EventHandler<MouseEvent> {
    public final String[] SYMBOLS = {"🍒", "⚡", "🍆", "🔺", "🐟", "🍑"};
    public final Random rng = new Random();

    private String currentSymbol;

    /**
     * Getter for wheel {@link SlotWheel#currentSymbol}
     * @return
     */
    public String getCurrentSymbol() {
        return currentSymbol;
    }

    /**
     * Private setter for wheel {@link SlotWheel#currentSymbol}
     * @param currentSymbol New symbol
     */
    private void setCurrentSymbol(String currentSymbol) {
        this.currentSymbol = currentSymbol;
        this.symbol.setText(currentSymbol);
    }

    private Text symbol;

    public SlotWheel() {
        super();
        setMinHeight(100);
        setBackground(new Background(new BackgroundFill(Paint.valueOf("#EEEEEE"), null, null)));

        symbol = new Text();
        symbol.setFont(new Font("Segoe UI Emoji", 48));
        rollSymbol();

        getChildren().add(symbol);
        addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    }

    /**
     * Generates a random symbol string
     * @return  Random symbol
     */
    public String randomSymbol() {
        int randIndex = rng.nextInt(5);
        return SYMBOLS[randIndex];
    }

    /**
     * Updates this wheel with a new random symbol
     */
    public void rollSymbol() {
        String newSymbol = this.randomSymbol();
        setCurrentSymbol(newSymbol);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED && Main.gameSession.getScore() != 0) {
            this.rollSymbol();
            Main.gameSession.halveScore();
            Main.gameSession.checkWin();
        }
    }
}
