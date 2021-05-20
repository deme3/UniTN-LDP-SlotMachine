package it.unitn.disi.lingprogmod1.deme3.SlotBody;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Represents score enclosed in a black Text Field with thick white borders and fixed-width font
 */
public class ScoreTextField extends StackPane {
    private double score;
    private int scorePadding = 6;
    private Text scoreText;

    /**
     * {@link ScoreTextField} Default constructor
     * @param score Initial score
     */
    public ScoreTextField(int score) {
        super();
        setBackground(new Background(new BackgroundFill(Paint.valueOf("#000000"), null, null)));
        setBorder(new Border(new BorderStroke(Paint.valueOf("#ffffff"), BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(3))));
        setPadding(new Insets(4));

        this.score = score;
        scoreText = new Text(this.getScorePadded(score));
        scoreText.setFont(new Font("Courier New", 40));
        scoreText.setFill(Paint.valueOf("#ff0000"));
        scoreText.maxWidth(200);

        getChildren().add(scoreText);
    }

    /**
     * Generates leading 0's for score fields
     * @param score Score field value to pad
     * @return Padded score (e.g. 128 -> 000128)
     */
    private String getScorePadded(double score) {
        return this.getScorePadded(score, this.scorePadding);
    }

    /**
     * Generates <b>{@code padding}</b> leading 0's for score fields
     * @param score     Score field value to pad
     * @param padding   Padding for the number
     * @return Padded score<br/> (e.g. 128 -> 000128 with {@code padding = 6})
     */
    private String getScorePadded(double score, int padding) {
        return String.format("%" + padding + "s", score).replace(' ', '0');
    }

    /**
     * Setter for {@link ScoreTextField#score}<br/>
     * Updates graphical representation of the score
     * @param score New field value
     */
    public void setScore(double score) {
        this.score = score;
        this.scoreText.setText(this.getScorePadded(score));
    }

    /**
     * Setter for {@link ScoreTextField#scorePadding}<br />
     * Updates graphical representation of the score with the new padding
     * @param scorePadding  New padding value
     */
    public void setScorePadding(int scorePadding) {
        this.scorePadding = scorePadding;
        this.scoreText.setText(this.getScorePadded(score));
    }

    /**
     * Getter for {@link ScoreTextField#scorePadding} for consistency
     * @return {@link ScoreTextField#scorePadding}
     */
    public int getScorePadding() {
        return this.scorePadding;
    }
}
