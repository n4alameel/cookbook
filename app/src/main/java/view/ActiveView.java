package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    Scene loginScene = new Scene(loginView.getRoot(), 200, 200);
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

  /**
   * Closes the app.
   */
  public void closeApp() {
    this.stage.close();
    this.controller.dbClose();
  }

}
