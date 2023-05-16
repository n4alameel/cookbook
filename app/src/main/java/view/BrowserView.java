package view;

import java.io.IOException;

import controller.AllRecipeWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;

public class BrowserView {
  public ScrollPane root;

  public BrowserView() throws IOException {
    // setting the scene
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/AllRecipesWindow.fxml"));
    this.root = loader.load();
    // Get the controller as an object to be able to call the method
    // initPagination()
    AllRecipeWindowController browserController = (AllRecipeWindowController) loader.getController();
    browserController.initPagination();

  }

  public ScrollPane getRoot() {
    return this.root;
  }
}
