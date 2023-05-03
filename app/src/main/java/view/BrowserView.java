package view;

import java.io.IOException;
import java.util.ArrayList;

import controller.AllRecipeWindowController;
import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.Recipe;

public class BrowserView {
  private Parent root;

  public BrowserView() {
    // setting the scene
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/AllRecipesWindow.fxml"));
      root = (Parent) loader.load();
      // root = FXMLLoader.load(getClass().getResource("/AllRecipesWindow.fxml"));
      System.out.println("va");
      AllRecipeWindowController browserController = (AllRecipeWindowController) loader.getController();
      browserController.updatePage();
      System.out.println("cc");

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Parent getRoot() {
    return this.root;
  }
}
