package view;

import controller.RecipeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import model.Recipe;

import java.io.IOException;

public class RecipeView {
  public ScrollPane getRoot() {
    return root;
  }

  private ScrollPane root;

  public RecipeView(int selectedRecipeId) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeWindow.fxml"));
      this.root = loader.load();
      RecipeController recipeController = loader.getController();
      recipeController.setRecipe(selectedRecipeId);
      recipeController.updatePage();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
