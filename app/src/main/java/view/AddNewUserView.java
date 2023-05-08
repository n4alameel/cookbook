package view;

import controller.AddNewUserController;
import controller.UsersViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class AddNewUserView {
    private Parent root;

    public AddNewUserView() {
        try {
            root = FXMLLoader.load(getClass().getResource("/AddNewUserWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Parent getRoot() {
        return this.root;
    }
}