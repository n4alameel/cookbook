package view;

import javafx.fxml.FXMLLoader;
//import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginWindow extends Stage {
    public LoginWindow(){
        //Group -> Scene -> Stage
        //Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LoginWindow.fxml"));
            //Group root = new Group();
            Scene scene = new Scene(root);
            setTitle("Login Page");
            setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
