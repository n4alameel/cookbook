package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Recipe;

public class AllRecipeWindowController {
  private Controller controller = Controller.getInstance();
  private int pageNumber = -1;
  @FXML
  private Pagination recipePages;
  @FXML
  private GridPane recipeGrid;

  @FXML
  public void openHomepage() throws IOException {
    controller.displayHomeView();
  }

  @FXML
  public void goToHomePage() throws IOException {
    controller.displayHomeView();
  }

  @FXML
  public void openNewRecipe() throws IOException {
    controller.displayNewRecipeView();
  }

  /**
   * Initialize the pagination system
   */
  public void initPagination() {
    ArrayList<Recipe> recipeList = controller.getRecipeList();
    int maxPageNum = (recipeList.size() / 9) + 1;
    recipePages.setPageCount(maxPageNum);
    recipePages.setPageFactory((pageIndex) -> {
      updatePage(pageIndex);
      return new Pane();
    });
    updatePage(0);
  }

  /**
   * Update the shown page with the corresponding recipe cards
   * 
   * @param pageIndex The page index got from the factory
   */
  public void updatePage(int pageIndex) {
    // If you click on the page you are, nothing happens
    if (pageIndex == this.pageNumber) {
      return;
    }
    this.pageNumber = pageIndex;
    ArrayList<Recipe> recipeList = controller.getRecipeList();
    int recipeNum = recipeList.size();

    // Clear the actual grid
    recipeGrid.getChildren().clear();

    int currentIndex = 9 * pageIndex;
    int lastRecipeIndex = 9 * (pageIndex + 1);
    int col = 0;
    int row = 0;
    // While the recipe list is not empty or we have not added 9 cards
    while (currentIndex < recipeNum && currentIndex < lastRecipeIndex) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeCard.fxml"));
        Pane root = loader.load();
        RecipeCardController cardController = loader.getController();
        cardController.updateCard(recipeList.get(currentIndex));
        recipeGrid.add(root, col % 3, row);
        recipeGrid.getChildren().get(recipeGrid.getChildren().size() - 1).toBack();
        col++;
        if (col % 3 == 0) {
          row++;
        }
        currentIndex++;
      } catch (IOException e) {
        e.printStackTrace(System.out);
      }

    }
  }
}
