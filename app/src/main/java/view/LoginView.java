package view;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Defines the login scene
 */
public class LoginView {

  private GridPane root;

  public LoginView(Controller controller, ActiveView activeView) {
    this.root = new GridPane();

    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(11.5, 12.5, 13.5, 14.4));
    root.setHgap(5.5);
    root.setVgap(5.5);

    root.add(new Label("User name:"), 0, 0);
    final TextField username = new TextField();
    root.add(username, 1, 0);

    root.add(new Label("Password:"), 0, 1);
    final PasswordField password = new PasswordField();
    root.add(password, 1, 1);

    final Label result = new Label();
    root.add(result, 0, 2);

    Button testLogin = new Button("Login");
    root.add(testLogin, 1, 2);
    GridPane.setHalignment(testLogin, HPos.RIGHT);
    testLogin.setOnAction(e -> {
      if (controller.login(username.getText(), password.getText())) {
        activeView.displayMainView(); // If credentials are ok, then we display the main menu
      } else {
        result.setText("Error");
      }
    });

  }

  public Parent getRoot() {
    return this.root;
  }

}
