package view;

import java.io.IOException;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class BrowserView {
  private Parent root;

  public BrowserView(Controller controller) {
    // setting the scene
    try {
      root = FXMLLoader.load(getClass().getResource("/AllRecipesWindow.fxml"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Parent getRoot() {
    return this.root;
  }
}
