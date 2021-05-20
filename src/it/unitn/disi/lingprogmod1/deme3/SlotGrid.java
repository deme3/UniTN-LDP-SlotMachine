package it.unitn.disi.lingprogmod1.deme3;

import it.unitn.disi.lingprogmod1.deme3.SlotBody.SlotBody;
import it.unitn.disi.lingprogmod1.deme3.SlotBody.SlotButton;
import it.unitn.disi.lingprogmod1.deme3.SlotHeader.SlotHeader;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

/**
 * Slot Machine root node
 */
public class SlotGrid extends GridPane {
    public SlotGrid() {
        super();
        PercentColumnConstraints col1 = new PercentColumnConstraints(100);
        PercentRowConstraints row1 = new PercentRowConstraints(20);
        RowConstraints row2 = new PercentRowConstraints(100);
        RowConstraints row3 = new PercentRowConstraints(20);

        getRowConstraints().addAll(row1, row2, row3);
        getColumnConstraints().add(col1);
    }

    /**
     * Set up stylish title for our Slot Machine
     * @param slotHeader {@link SlotHeader}
     */
    public void setUpHeader(SlotHeader slotHeader) {
        slotHeader.setAlignment(Pos.CENTER);
        this.add(slotHeader, 0, 0);
    }

    /**
     * Set up the specified {@link SlotBody} as second row in Grid
     * @param body {@link SlotBody}
     */
    public void setUpBody(SlotBody body) {
        body.setAlignment(Pos.CENTER);
        add(body, 0, 1);
    }

    /**
     * Set up game buttons row (last row in Grid)
     */
    public void setUpFooter() {
        SlotButton newGameButton = new SlotButton("NEW GAME");
        SlotButton payButton = new SlotButton("PAY");
        SlotButton spinButton = new SlotButton("SPIN");
        CoinButton coin1 = new CoinButton("1 Euro");
        CoinButton coin2 = new CoinButton("1 Euro");
        CoinButton coin3 = new CoinButton("1 Euro");

        // Styling the GREEN buttons (New Game Button is gray by default)
        spinButton.setGradientColors(Color.valueOf("#6EE06F"), Color.valueOf("#489349"));
        payButton.setGradientColors(Color.valueOf("#6EE06F"), Color.valueOf("#489349"));

        AnchorPane container = new AnchorPane();
        HBox leftButtons = new HBox();
        leftButtons.setSpacing(8.0);
        leftButtons.getChildren().addAll(newGameButton, payButton, coin1, coin2, coin3);

        // add left section and right section to the AnchorPane
        container.getChildren().addAll(leftButtons, spinButton);
        AnchorPane.setLeftAnchor(leftButtons, 8.0);
        AnchorPane.setBottomAnchor(leftButtons, 8.0);

        // spin buttons stays to the left
        AnchorPane.setRightAnchor(spinButton, 8.0);
        AnchorPane.setBottomAnchor(spinButton, 8.0);

        // finally add the AnchorPane to our footer (column 1 row 3)
        add(container, 0, 2);

        // set up session for game logic
        GameSession.initButtons(payButton, newGameButton, spinButton);

        // TODO : Make this iterable with custom N of coins
        Main.gameSession.registerCoin(coin1);
        Main.gameSession.registerCoin(coin2);
        Main.gameSession.registerCoin(coin3);
    }
}
