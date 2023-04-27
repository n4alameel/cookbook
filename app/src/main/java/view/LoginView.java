package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Defines the login scene
 */
public class LoginView {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginButton;
    private String username ="jar_jar_binks";
    private String password = "1234";
    private Parent root;
    private Stage stage;

    public LoginView(Controller controller, ActiveView activeView) {
        //setting the scene
        try {
            root = FXMLLoader.load(getClass().getResource("/LoginWindow.fxml"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(controller.login(username, password)){
            activeView.displayMainView();
        }
        else {
            usernameField.setText("wrong credentials");
        }
    }
    public Parent getRoot() {
        return this.root;
    }
    //need this for the button
}

