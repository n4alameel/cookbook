package controller;

import java.io.IOException;

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

  public void displaySearch() throws IOException {

  }

}
