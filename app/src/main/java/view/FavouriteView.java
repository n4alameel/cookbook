package view;

import controller.FavouriteRecipeController;
import javafx.fxml.FXMLLoader;
import controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class FavouriteView {

  private ScrollPane root;

  public FavouriteView() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/FavouriteWindow.fxml"));
      root = (ScrollPane) loader.load();
      FavouriteRecipeController favouriteRecipeController = loader.getController();
      favouriteRecipeController.initPagination();
    } catch (IOException e) {
      e.printStackTrace(System.out);
      throw new RuntimeException(e);
    }

  }

  public ScrollPane getRoot() {
    return this.root;
  }
}
