package it.unitn.disi.lingprogmod1.deme3.SlotBody;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class ScoreField extends VBox {
    public double score;
    private Text labelText;
    private ScoreTextField scoreTextField;

    /**
     *
     * @param label Text to show above the score
     * @param score Score number
     */
    public ScoreField(String label, int score) {
        this.score = score;

        labelText = new Text(label);
        labelText.setFill(Paint.valueOf("#FFFFFF"));
        labelText.setStyle("-fx-font-weight: bold; -fx-font-size: 16");
        scoreTextField = new ScoreTextField(score);
        getChildren().addAll(labelText, scoreTextField);
        setAlignment(Pos.CENTER);
    }

    /**
     * Setter for field label text
     * @param label New text
     */
    public void setLabelText(String label) {
        this.labelText.setText(label);
    }

    /**
     * Setter for {@link ScoreField#score}<br/>
     * Updates score graphical text field
     * @param score
     */
    public void setScore(double score) {
        this.score = score;
        this.scoreTextField.setScore(score);
    }
}
