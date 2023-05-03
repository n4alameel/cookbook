package controller;

import java.io.IOException;

public class AllRecipeWindowController {
  private Controller controller = Controller.getInstance();

  public void openHomepage() throws IOException {
    controller.displayHomeView();
  }

  public void goToHomePage() throws IOException {
    controller.displayHomeView();
  }

}
