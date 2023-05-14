package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Recipe;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeViewController {

  @FXML
  private GridPane favouriteHome;
  @FXML
  private GridPane recommendationsHome;
  Controller controller = Controller.getInstance();

  public void openAllRecipes() throws IOException {
    controller.displayBrowserView();
  }

  public void goToHomePage() throws IOException {
    controller.displayHomeView();
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

  public void openSearchPage() throws IOException {
    controller.displaySearchView();
  }

  public void openHelpPage() throws  IOException{
    controller.displayHelpPage();
  }

  /**
   * Shows a part of the favourite recipes on the main page
   */
  public void showFavourites() {

    ArrayList<Recipe> favouriteList = controller.getActiveUser().getFavouriteList();
    int favNum = favouriteList.size();

    // Clear the actual grid
    favouriteHome.getChildren().clear();

    int currentIndex = 0;
    int maxNum = 3;
    int col = 0;
    int row = 0;
    // While the recipe list is not empty or we have not added 3 cards
    while (currentIndex < favNum && currentIndex < maxNum) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeCard.fxml"));
        Pane root = loader.load();
        RecipeCardController cardController = loader.getController();
        cardController.setRecipe(favouriteList.get(currentIndex).getId());
        cardController.updateCard();
        favouriteHome.add(root, col % 3, row);
        col++;
        currentIndex++;
      } catch (IOException e) {
        e.printStackTrace(System.out);
      }
    }
  }

  /**
   * Shows recommendations on the main page
   */
  public void showRecommondations() {

    ArrayList<Recipe> recommendationList = controller.getRecipeList();
    int recipeNum = recommendationList.size();


    // Clear the actual grid
    recommendationsHome.getChildren().clear();

    int currentIndex = 0;
    int maxNum = 3;
    int col = 0;
    int row = 0;
    // While the recipe list is not empty or we have not added 3 cards
    while (currentIndex < recipeNum && currentIndex < maxNum) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeCard.fxml"));
        Pane root = loader.load();
        RecipeCardController cardController = loader.getController();
        cardController.setRecipe(recommendationList.get(currentIndex).getId());
        cardController.updateCard();
        recommendationsHome.add(root, col % 3, row);
        col++;
        currentIndex++;
      } catch (IOException e) {
        e.printStackTrace(System.out);
      }
    }
  }
}

