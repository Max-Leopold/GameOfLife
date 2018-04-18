import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    GridPane gridPane;

    public void initialize(){

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell();
            }
        }


    }
}
