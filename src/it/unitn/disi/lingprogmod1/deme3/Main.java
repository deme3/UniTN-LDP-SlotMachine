package it.unitn.disi.lingprogmod1.deme3;
import it.unitn.disi.lingprogmod1.deme3.SlotBody.SlotBody;
import it.unitn.disi.lingprogmod1.deme3.SlotHeader.SlotHeader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static final GameSession gameSession = new GameSession(0, 0);
    @Override
    public void start(Stage primaryStage) {
        SlotHeader slotHeader = new SlotHeader();
        SlotGrid root = new SlotGrid();
        root.setUpHeader(slotHeader);

        SlotBody body = new SlotBody();
        root.setUpBody(body);
        root.setUpFooter();
        root.setStyle("-fx-background-color: #AAA");

        primaryStage.setTitle("Slot Machine");
        primaryStage.setScene(new Scene(root, 800, 640));
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(660);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}