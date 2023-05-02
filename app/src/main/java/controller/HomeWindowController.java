package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class HomeWindowController {
    public void openAllRecipes(javafx.event.ActionEvent event) throws IOException {
        //TODO: wrong fxml for now need AllRecipe.FXML
        Parent root = FXMLLoader.load(getClass().getResource("/AllRecipesWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
