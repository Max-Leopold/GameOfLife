import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class SetSizeController {

    @FXML GridPane gridPane;

    @FXML TextField heightText;

    @FXML TextField lengthText;

    private Cell[] cellArray;

    Button submit = new Button("submit");

    Stage primaryStage;

    public void initialize(){

        gridPane.add(submit, 3, 1);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
