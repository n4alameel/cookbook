package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class RecipeView{
    @FXML
    private Text recipeName;

    public RecipeView(Controller controller, ActiveView activeView, model.Recipe recipe) {
        try {
            root = FXMLLoader.load(getClass().getResource("/RecipeView.fxml"));
            initialize(recipe.getName());
//            password = passwordField.getText();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize(String name){
        recipeName.setText(name);
    }
    private Parent root;
    private Stage stage;
    public Parent getRoot() {
        return this.root;
    }

    public RecipeView(ActionEvent event)throws IOException {
        //Group -> Scene -> Stage
        root = FXMLLoader.load(getClass().getResource("RecipeWindow.fxml"));
//            Scene scene = new Scene(root);
//            setTitle("Recipe Page");
//            setScene(scene);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

    }
}