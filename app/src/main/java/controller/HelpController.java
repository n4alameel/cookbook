package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;

public class HelpController {
    Controller controller = Controller.getInstance();

    @FXML
    TextField tf;

    @FXML
    private TabPane tabPane;


    @FXML
    public void search() {
        String search = tf.getText().toLowerCase();

        for (Tab tab : tabPane.getTabs()) {
            tab.getStyleClass().remove("mark-tabs");
        }
        if (search.length() > 2) {
            for (Tab tab : tabPane.getTabs()) {

                Label label = (Label) tab.getContent().lookup(".label");
                TextArea textArea = (TextArea) tab.getContent().lookup(".text-area");

                if (textArea != null && label != null && textArea.getText().toLowerCase().contains(search) || label.getText().toLowerCase().contains(search)) {
                    tab.getStyleClass().add("mark-tabs");
                } else {
                    tab.getStyleClass().remove("mark-tabs");
                }

            }
        }
    }

    public void initialize() {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            search();
        });


    }
    public void backToHome() throws IOException {
        controller.displayHomeView();
    }


}
