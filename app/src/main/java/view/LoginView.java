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
    private String username;
    private String password;
    private Parent root;
    private Stage stage;


    public LoginView(Controller controller, ActiveView activeView) {
        //setting the scene
        try {
            root = FXMLLoader.load(getClass().getResource("/LoginWindow.fxml"));
            username = usernameField.getText();
            password = passwordField.getText();
            if(controller.login(username, password)){
                activeView.displayMainView();
            }
            else {
                usernameField.setText("wrong credentials");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Parent getRoot() {
        return this.root;
    }

    //need this for the button
    public void LoginView(ActionEvent event) throws IOException {
        //this is needed to for the new fxml scene.
        root = FXMLLoader.load(getClass().getResource("/Recipe.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    }
}

