package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Recipe;

public class AllRecipeWindowController {
  private Controller controller = Controller.getInstance();
  private int pageNumber = 0;
  @FXML
  private Pagination recipePages;
  @FXML
  private GridPane recipeGrid;

  public void openHomepage() throws IOException {
    controller.displayHomeView();
  }

  public void goToHomePage() throws IOException {
    controller.displayHomeView();
  }

  public void updatePage() throws IOException {
    int pageNum = recipePages.getCurrentPageIndex();
    if (pageNum == this.pageNumber) {
      return;
    }
    ArrayList<Recipe> recipeList = controller.getRecipeList();
    int recipeNum = recipeList.size();
    int maxPageNum = recipeNum / 9 + 1;
    recipePages.setPageCount(maxPageNum);

    ObservableList<Node> children = recipeGrid.getChildren();
    for (Node card : children) {
      recipeGrid.getChildren().remove(card);
    }

    int currentIndex = 9 * (pageNum - 1);
    int lastRecipeIndex = (9 * pageNum) - 1;
    int col = 0;
    int row = 0;
    while (currentIndex < recipeNum && currentIndex < lastRecipeIndex) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeCard.fxml"));
        Pane root = loader.load();
        RecipeCardController cardController = loader.getController();
        cardController.updateCard(recipeList.get(currentIndex));
        recipeGrid.add(root, col % 3, row);
        col++;
        if (col % 3 == 0) {
          row++;
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

    }
  }
}
