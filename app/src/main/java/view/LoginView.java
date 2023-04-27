package view;
import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Defines the login scene
 */
public class LoginView {
    private Parent root;

    public LoginView(Controller controller, ActiveView activeView) {
        //setting the scene
        try {
            root = FXMLLoader.load(getClass().getResource("/LoginWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Parent getRoot() {
        return this.root;
    }
}

