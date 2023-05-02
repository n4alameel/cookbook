package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Recipe;

/**
 * This class creates new scenes and display them.
 */
public class ActiveView {

  private Controller controller;
  private Stage stage;

  public ActiveView(Controller controller, Stage primaryStage) {
    this.controller = controller;
    this.stage = primaryStage;
  }

  /**
   * Creates and display the login scene.
   */
  public void displayLoginScene() {
    LoginView loginView = new LoginView(this.controller, this);
    Scene loginScene = new Scene(loginView.getRoot(), 300, 150);
    stage.setScene(loginScene);
    stage.show();
  }

  /**
   * Creates and display the main menu scene.
   */
  public void displayMainView() {
    MainView mainView = new MainView(this.controller, this);
    Scene mainScene = new Scene(mainView.getRoot(), 500, 500);
    stage.setScene(mainScene);
    stage.show();
  }

  /**
   * Creates and display the main menu scene.
   */
  public void displayBrowserView() {
    BrowserView browserView = new BrowserView(this.controller, this);
    Scene browserScene = new Scene(browserView.getRoot(), 500, 500);
    stage.setScene(browserScene);
    stage.show();
  }

  public void displayRecipeView(Recipe r) {
    RecipeView recipeView = new RecipeView(this.controller, this, r);
    Scene recipeScene = new Scene(recipeView.getRoot(), 500, 500);
    stage.setScene(recipeScene);
    stage.show();
  }

  /**
   * Closes the app.
   */
  public void closeApp() {
    this.stage.close();
    this.controller.dbClose();
  }

}
