package view;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    private Parent root;

    public LoginView(Controller controller, ActiveView activeView) {
        //setting the scene
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/LoginWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //controller checks whether username and password match
        if (controller.login(usernameField.getText(), passwordField.getText())) {
            activeView.displayMainView(); // If credentials are ok, then we display the main menu
        } else {
            usernameField.setText("Falsche Daten");
        }
    }
  public Parent getRoot() {
    return root;
  }
}
