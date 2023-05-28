package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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

                Set<Node> textAreas =  tab.getContent().lookupAll(".text-area");

                for(Node node: textAreas) {
                    if (node instanceof TextArea) {
                        TextArea textArea = (TextArea) node;
                        String text = textArea.getText().toLowerCase();
                        if (text.contains(search)) {
                            tab.getStyleClass().add("mark-tabs");
                        }
                        else {
                            tab.getStyleClass().remove("mark-tabs");
                        }
                        }

                }

                }
            }
        }



    public void initialize () {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            search();
        });


    }


}
