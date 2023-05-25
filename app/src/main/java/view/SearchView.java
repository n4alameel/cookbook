package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import model.Query;

import java.io.IOException;

import controller.SearchViewController;

public class SearchView {

  public Parent getRoot() {
    return root;
  }

  public Parent root;

  public SearchView(Query search, String selectedOption) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchView.fxml"));
      this.root = (Parent) loader.load();
      SearchViewController searchController = (SearchViewController) loader.getController();

      if (selectedOption == null) {
        selectedOption = "Recipe";
      }

      switch (selectedOption) {
        case "Ingredients": {
          searchController.searchIngredients(search);
          break;
        }
        case "Tags": {
          searchController.searchTags(search);
          break;
        }
        case "Recipe": {
          searchController.searchRecipe(search);
          break;
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
