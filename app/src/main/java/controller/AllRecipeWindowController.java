package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AllRecipeWindowController {
    public void openHomepage(javafx.event.ActionEvent event) throws IOException {
        //TODO: change windows and all
        Parent root = FXMLLoader.load(getClass().getResource("/HomeWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}