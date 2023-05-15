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

  public void openMessage() throws IOException {
    controller.displayMessageView();
  }

  public void sendRecipe() throws IOException {
    controller.displaySendMessageView();
  }

}
