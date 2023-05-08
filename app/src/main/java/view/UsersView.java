package view;

import controller.UsersViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class UsersView {
    private Parent root;

    public UsersView() {
        try {
            root = FXMLLoader.load(getClass().getResource("/UsersWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UsersViewController usersViewController = new UsersViewController();
        usersViewController.LoadUsers();
    }

    public Parent getRoot() {
        return this.root;
    }
}