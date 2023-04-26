package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RecipeWindow extends Stage {

    public RecipeWindow(){
        //Group -> Scene -> Stage
        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("RecipeWindow.fxml"));
            Scene scene = new Scene(root);
            setTitle("Recipe Page");
            setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}