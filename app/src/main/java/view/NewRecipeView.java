package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class NewRecipeView {
  private Parent root;

  public NewRecipeView() {
    try {
      root = FXMLLoader.load(getClass().getResource("/NewRecipe.fxml"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Parent getRoot() {
    return this.root;
  }
}
