package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginWindowController {
    Controller controller = new Controller();
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    private String username;
    private String password;

    public void loginEvent(ActionEvent event) throws IOException {
        try {
            //fast login for now
            username = usernameField.getText();
            password = passwordField.getText();
        } catch (Exception e) {
            usernameField.setText("error");
        }
        if (controller.login(username, password)) {
            Parent root = FXMLLoader.load(getClass().getResource("/HomeWindow.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Error");
        }
    }

    public void enterPressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            try {
                //fast login for now
                username = usernameField.getText();
                password = passwordField.getText();
            } catch (Exception e) {
                usernameField.setText("error");
            }
            if (controller.login(username, password)) {
                Parent root = FXMLLoader.load(getClass().getResource("/AddRecipe.fxml"));
                Stage stage = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("Error");
            }
        }
    }
}