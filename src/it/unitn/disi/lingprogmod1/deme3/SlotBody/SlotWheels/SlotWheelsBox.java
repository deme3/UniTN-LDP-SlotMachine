package it.unitn.disi.lingprogmod1.deme3.SlotBody.SlotWheels;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Styled {@link HBox} representing a Slot Machine barrel
 */
public class SlotWheelsBox extends HBox {
    public final int MAX_WHEELS = 3;
    private SlotWheel[] wheels;
    public SlotWheelsBox() {
        super();
        wheels = new SlotWheel[MAX_WHEELS];
        for(int x = 0; x < MAX_WHEELS; x++) {
            wheels[x] = new SlotWheel();
            HBox.setHgrow(wheels[x], Priority.ALWAYS);
            this.getChildren().add(wheels[x]);
        }
        setSpacing(8);
        setPadding(new Insets(8));
        setFillHeight(true);
    }

    /**
     * Checks if this wheel has three equal symbols
     * @return true if the wheel has three equal symbols, false otherwise
     */
    public boolean hasTripleSymbol() {
        String lastSymbol = null;
        for(SlotWheel wheel : this.wheels) {
            if(lastSymbol == null) lastSymbol = wheel.getCurrentSymbol();
            else {
                if(!wheel.getCurrentSymbol().equals(lastSymbol))
                    return false;
            }
        }
        return true;
    }

    /**
     * Generic spin of all the wheels in this box<br />
     * Score is managed by game logic
     */
    public void spin() {
        for(SlotWheel wheel : this.wheels) {
            wheel.rollSymbol();
        }
    }

    /**
     * Spins the wheels for resetting the game conditions
     */
    public void spinNoWin() {
        while(hasTripleSymbol()) spin();
    }
}
