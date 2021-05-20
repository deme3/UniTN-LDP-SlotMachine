package it.unitn.disi.lingprogmod1.deme3.SlotBody;

import it.unitn.disi.lingprogmod1.deme3.GameSession;
import it.unitn.disi.lingprogmod1.deme3.PercentColumnConstraints;
import it.unitn.disi.lingprogmod1.deme3.PercentRowConstraints;
import it.unitn.disi.lingprogmod1.deme3.SlotBody.SlotWheels.SlotWheelsBox;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class SlotBody extends GridPane {
    public static ScoreField credits;
    public static ScoreField score;
    public SlotBody() {
        super();
        PercentRowConstraints row1 = new PercentRowConstraints(100);
        PercentRowConstraints row2 = new PercentRowConstraints(50);
        PercentColumnConstraints col1 = new PercentColumnConstraints(100);

        getRowConstraints().addAll(row1, row2);
        getColumnConstraints().add(col1);

        setUpWheels();
        setUpScoreRow();
    }

    public void setUpWheels() {
        SlotWheelsBox box = new SlotWheelsBox();
        add(box, 0, 0);

        // TODO : Evaluate scope of this instruction
        //        Should it be in the main class to enable for multiple instances without overhead and overlapping?
        GameSession.initWheelsBox(box);
    }

    public void setUpScoreRow() {
        HBox h = new HBox();
        SlotBody.credits = new ScoreField("Credits", 0);
        SlotBody.score = new ScoreField("Score", 0);

        HBox.setHgrow(credits, Priority.ALWAYS);
        HBox.setHgrow(score, Priority.ALWAYS);
        h.setSpacing(8);
        h.setPadding(new Insets(8));
        h.getChildren().addAll(credits, score);

        add(h, 0, 1);
    }
}
