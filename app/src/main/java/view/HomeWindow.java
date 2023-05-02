package view;

import controller.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class HomeWindow {


    Stage stage;
    public void openAllRecipes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AllRecipesWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }


}
