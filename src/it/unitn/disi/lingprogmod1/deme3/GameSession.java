package it.unitn.disi.lingprogmod1.deme3;

import it.unitn.disi.lingprogmod1.deme3.SlotBody.SlotBody;
import it.unitn.disi.lingprogmod1.deme3.SlotBody.SlotButton;
import it.unitn.disi.lingprogmod1.deme3.SlotBody.SlotWheels.SlotWheelsBox;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class GameSession {
    public static SlotButton payButton;
    public static SlotButton newGameButton;
    public static SlotButton spinButton;
    public static SlotWheelsBox wheelsBox;
    private static boolean buttonsSet = false;
    private static boolean wheelsBoxSet = false;

    public final int EURO_CREDITS_RATE = 100;
    public final int GAME_CREDITS_COST = 100;
    public final int GAME_STARTING_SCORE = 128;

    private double  startingCredit,
                    startingScore,
                    credits,
                    score;
    public ArrayList<CoinButton> coins;

    public GameSession(double startingCredit, double startingScore) {
        this.startingCredit = this.credits = startingCredit;
        this.startingScore = this.score = startingScore;
        this.coins = new ArrayList<CoinButton>();
    }

    /**
     * Makes arrangements for a coin button to interact with the game logic.
     * That is, setting an event handler linked to the session itself
     * @param coinButton The coin to initialise
     */
    public void registerCoin(CoinButton coinButton) {
        this.coins.add(coinButton);
        coinButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                mouseEvent -> setCredits(credits + EURO_CREDITS_RATE));
    }

    /**
     * Shows back all coins that have been already used
     */
    public void resetAllCoins() {
        for(CoinButton coin : this.coins) {
            coin.setVisible(true);
        }
    }

    /**
     * If conditions for winning the game are met ({@link SlotWheelsBox#hasTripleSymbol()})
     * show an alert and reset the attempt
     */
    public void checkWin() {
        if(wheelsBoxSet && wheelsBox.hasTripleSymbol()) {
            new Alert(Alert.AlertType.INFORMATION, "Hai vinto!").showAndWait();
            Main.gameSession.setCredits(Main.gameSession.getCredits() + Main.gameSession.getScore() * 100);
            Main.gameSession.setScore(0);
        }
    }

    /**
     * Acknowledges buttons for game operations in static scope.<br/><br/>
     *
     * See also
     * <ul style="margin-left:24px;margin-top:0">
     *     <li>{@link GameSession#payButton}</li>
     *     <li>{@link GameSession#newGameButton}</li>
     *     <li>{@link GameSession#spinButton}</li>
     * </ul>
     * @param payButton     Button for pay operation
     * @param newGameButton Button for starting a new game
     * @param spinButton    Button for spin operation
     */
    public static void initButtons(SlotButton payButton, SlotButton newGameButton, SlotButton spinButton) {
        GameSession.payButton = payButton;
        payButton.setOnMouseClicked((MouseEvent e) -> {
            double prize =  Main.gameSession.getCredits() / 100;
            new Alert(Alert.AlertType.INFORMATION, String.format("Hai vinto %.2f Euro!", prize)).showAndWait();

            Main.gameSession.setCredits(Main.gameSession.startingCredit);
            Main.gameSession.setScore(Main.gameSession.startingScore);
            Main.gameSession.resetAllCoins();
        });

        GameSession.newGameButton = newGameButton;
        newGameButton.setOnMouseClicked((MouseEvent e) -> {
            boolean success = Main.gameSession.newGame();
            if(!success) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Non hai credito sufficiente!");
                alert.showAndWait();
            }
        });

        GameSession.spinButton = spinButton;
        spinButton.setOnMouseClicked((MouseEvent e) -> {
            wheelsBox.spin();
            Main.gameSession.halveScore();
            Main.gameSession.checkWin();
        });

        GameSession.buttonsSet = true;
        Main.gameSession.setCredits(Main.gameSession.getCredits());
        Main.gameSession.setScore(Main.gameSession.getScore());
    }

    /**
     * Acknowledges the Slot Machine barrel for game operations in the static scope<br />
     * See {@link GameSession#wheelsBox}
     * @param box The barrel
     */
    public static void initWheelsBox(SlotWheelsBox box) {
        GameSession.wheelsBox = box;
        GameSession.wheelsBoxSet = true;
    }

    /**
     * Getter for read-only property {@link GameSession}.{@link GameSession#buttonsSet}
     * @return true if {@link GameSession}.{@link GameSession#initButtons} has been called
     */
    public static boolean areButtonsSet() {
        return GameSession.buttonsSet;
    }

    /**
     * Getter for read-only property {@link GameSession}.{@link GameSession#wheelsBoxSet}
     * @return true if {@link GameSession}.{@link GameSession#initWheelsBox} has been called
     */
    public static boolean isWheelsBoxSet() {
        return GameSession.wheelsBoxSet;
    }

    /**
     * Getter for {@link GameSession#score}
     * @return {@link GameSession#score}
     */
    public double getScore() {
        return score;
    }

    /**
     * Setter for {@link GameSession#score}<br/>
     * Disables Spin button ({@link GameSession#spinButton}) if credits equals zero
     * @param newValue New value for {@link GameSession#score}
     */
    public void setScore(double newValue) {
        this.score = newValue;
        SlotBody.score.setScore(this.score);
        if(GameSession.buttonsSet)
            GameSession.spinButton.setDisable(score == 0);
    }

    /**
     * Getter for {@link GameSession#credits}
     * @return {@link GameSession#credits}
     */
    public double getCredits() {
        return credits;
    }

    /**
     * Setter for {@link GameSession#credits}<br/>
     * Disables Pay button ({@link GameSession#payButton}) if credits equals zero
     * @param newValue New value for {@link GameSession#credits}
     */
    public void setCredits(double newValue) {
        this.credits = newValue;
        SlotBody.credits.setScore(this.credits);
        if(GameSession.buttonsSet)
            GameSession.payButton.setDisable(credits == 0);
    }

    /**
     * Sets {@link GameSession#score} to half its value
     */
    public void halveScore() {
        if(this.getScore() <= 1) this.setScore(0);
        else this.setScore(this.getScore() / 2);
    }

    /**
     * <ul>
     *     <li>Resets the game</li>
     *     <li>If credits < {@link GameSession#GAME_CREDITS_COST} cancel operation</li>
     *     <li>Else decrease credits by {@link GameSession#GAME_CREDITS_COST}</li>
     *     <li>Set score to 128</li>
     * </ul>
     * @return false if operation failed, true otherwise
     */
    public boolean newGame() {
        if(this.credits < GAME_CREDITS_COST) {
            return false; // this is supposed to raise an error via UI
        } else {
            wheelsBox.spin();
            this.setCredits(credits - GAME_CREDITS_COST);
            this.setScore(GAME_STARTING_SCORE);
            return true;
        }
    }
}
