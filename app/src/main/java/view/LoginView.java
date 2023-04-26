package view;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.w3c.dom.Text;

import java.awt.event.ActionEvent;
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

    public void submit(ActionEvent event) {

    }

    public LoginView(Controller controller, ActiveView activeView){
      //setting the scene
      try {
        this.root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      //controller checks whether username and password match
      if (controller.login(usernameField.getText(), passwordField.getText())) {
            activeView.displayMainView(); // If credentials are ok, then we display the main menu
        } else {

        }
    }

    public Parent getRoot() {
        return this.root;
    }
}

