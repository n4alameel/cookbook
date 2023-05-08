package view;

import controller.RecipeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.Recipe;

import java.io.IOException;

public class RecipeView {

  private Parent root;

  public RecipeView(Recipe selectedRecipe) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeWindow.fxml"));
      root = loader.load();
      RecipeController recipeController = loader.getController();
      recipeController.updatePage(selectedRecipe);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Parent getRoot() {
    return this.root;
  }
}
