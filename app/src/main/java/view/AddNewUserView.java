package view;

import controller.AddNewUserController;
import controller.UsersViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class AddNewUserView {
    private Parent root;

    public AddNewUserView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddNewUserWindow.fxml"));
            root = (Parent) loader.load();
            AddNewUserController addNewUserController = (AddNewUserController) loader.getController();
            addNewUserController.initializeToggleGroup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AddNewUserView(int id, String username, String password, boolean isAdmin, String imageUrl) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddNewUserWindow.fxml"));
            root = (Parent) loader.load();
            AddNewUserController addNewUserController = (AddNewUserController) loader.getController();
            addNewUserController.initializeUserChange(id, username, password, isAdmin, imageUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Parent getRoot() {
        return this.root;
    }
}