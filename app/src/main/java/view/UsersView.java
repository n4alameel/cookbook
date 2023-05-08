package view;

import controller.UsersViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class UsersView {
  private Parent root;

  public UsersView() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/UsersWindow.fxml"));
      root = (Parent) loader.load();
      UsersViewController usersViewController = (UsersViewController) loader.getController();
      usersViewController.LoadUsers();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Parent getRoot() {
    return this.root;
  }
}