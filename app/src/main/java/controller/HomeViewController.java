package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Recipe;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeViewController {

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

}
