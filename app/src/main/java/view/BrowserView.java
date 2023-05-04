package view;

import java.io.IOException;
<<<<<<< HEAD

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
=======
import java.util.ArrayList;

import controller.AllRecipeWindowController;
import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.Recipe;
>>>>>>> 7f5a3b9a8f619903abfdc5609500763208bfe25d

public class BrowserView {
  private Parent root;

  public BrowserView() {
    // setting the scene
    try {
<<<<<<< HEAD
      root = FXMLLoader.load(getClass().getResource("/AllRecipesWindow.fxml"));
    } catch (IOException e) {
=======
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/AllRecipesWindow.fxml"));
      root = (Parent) loader.load();
      // Get the controller as an object to be able to call the method
      // initPagination()
      AllRecipeWindowController browserController = (AllRecipeWindowController) loader.getController();
      browserController.initPagination();

    } catch (IOException e) {
      System.out.println("no");
      e.printStackTrace(System.out);
>>>>>>> 7f5a3b9a8f619903abfdc5609500763208bfe25d
      throw new RuntimeException(e);
    }
  }

  public Parent getRoot() {
    return this.root;
  }
}
