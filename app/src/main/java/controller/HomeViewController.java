package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Recipe;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomeViewController {

  @FXML
  private GridPane favouriteHome;
  @FXML
  private GridPane recommendationsHome;
  Controller controller = Controller.getInstance();

  public void openAllRecipes() throws IOException {
    controller.displayBrowserView();
  }

  public void openNewRecipe() throws IOException {
    controller.displayNewRecipeView();
  }

  public void seeFavourites() throws IOException {
    controller.displayFavouriteView();
  }

  public void goToMealPlansView() throws IOException {
    controller.displayWeeklyPlanListView();
  }

  public void openHelpPage() throws IOException {
    controller.displayHelpPage();
  }

  public void showFavourites() {
    ArrayList<Recipe> favouriteList = controller.getActiveUser().getFavouriteList();
    int favNum = favouriteList.size();

    favouriteHome.getChildren().clear();

    int currentIndex = 0;
    int maxNum = 3;
    int col = 0;
    int row = 0;

    while (currentIndex < favNum && currentIndex < maxNum) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeCard.fxml"));
        Pane root = loader.load();
        RecipeCardController cardController = loader.getController();
        cardController.setRecipe(favouriteList.get(currentIndex).getId());
        cardController.updateCard();
        favouriteHome.add(root, col % 3, row);
        favouriteHome.getChildren().get(favouriteHome.getChildren().size() - 1).toBack();
        col++;
        currentIndex++;
      } catch (IOException | SQLException e) {
        e.printStackTrace(System.out);
      }
    }
  }

  /**
   * Shows recommendations on the main page
   */
  public void showRecommondations() {

    ArrayList<Recipe> recipeList = controller.getRecipeList();
    ArrayList<Recipe> recommendationList = new ArrayList<>();
    int index = 0;

    while (index < recipeList.size()) {
      if (controller.getActiveUser().isFavourite(recipeList.get(index)) == false) {
        recommendationList.add(recipeList.get(index));
      }
      index++;
    }

    int recipeNum = recommendationList.size();

    // Clear the actual grid
    recommendationsHome.getChildren().clear();

    int currentIndex = 0;
    int maxNum = 3;
    int col = 0;
    int row = 0;
    // While the recipe list is not empty or we have not added 3 cards
    while (currentIndex < recipeNum && currentIndex < maxNum ) {
        try {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeCard.fxml"));
          Pane root = loader.load();
          RecipeCardController cardController = loader.getController();
            cardController.setRecipe(recommendationList.get(currentIndex).getId());
            cardController.updateCard();
            recommendationsHome.add(root, col % 3, row);
          recommendationsHome.getChildren().get(recommendationsHome.getChildren().size() - 1).toBack();
          col++;
            currentIndex++;
        } catch (IOException e) {
          e.printStackTrace(System.out);
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }

    }
  }

  public void openMessage() throws IOException {
    controller.displayMessageView();
  }

}
