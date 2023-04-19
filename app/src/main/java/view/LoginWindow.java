package view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginWindow extends Stage {
    public LoginWindow(){
        //Group -> Scene -> Stage
        //Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);

        setTitle("Login Page");

        setScene(scene);
    }
}
