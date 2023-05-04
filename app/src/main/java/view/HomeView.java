package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class HomeView {
  private Parent root;

  public HomeView() {
    // setting the scene
    try {
      root = FXMLLoader.load(getClass().getResource("/HomeWindow.fxml"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Parent getRoot() {
    return this.root;
  }

}