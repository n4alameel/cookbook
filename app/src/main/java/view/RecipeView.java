package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class RecipeView {

  private Parent root;

  public RecipeView() {
    try {
      root = FXMLLoader.load(getClass().getResource("/RecipeWindow.fxml"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Parent getRoot() {
    return this.root;
  }
}
