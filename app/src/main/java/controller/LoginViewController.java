package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class LoginViewController {
  Controller controller = Controller.getInstance();
  @FXML
  private TextField usernameField;
  @FXML
  private PasswordField passwordField;
  private String username;
  private String password;

  public void loginEvent() throws IOException {
    try {
      // fast login for now
      username = usernameField.getText();
      password = passwordField.getText();
    } catch (Exception e) {
      usernameField.setText("error");
    }
    if (controller.login(username, password)) {
      controller.displayHomeView();
    } else {
      System.out.println("Error");
    }
  }

  public void enterPressed(KeyEvent keyEvent) throws IOException {
    if (keyEvent.getCode() == KeyCode.ENTER) {
      loginEvent();
    }
  }
}