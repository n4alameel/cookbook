package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ActiveView {

  private Controller controller;
  private Stage stage;

  public ActiveView(Controller controller, Stage primaryStage) {
    this.controller = controller;
    this.stage = primaryStage;
  }

  public void displayLoginScene() {
    LoginView loginView = new LoginView(this.controller, this);
    Scene loginScene = new Scene(loginView.getRoot(), 300, 300);
    stage.setScene(loginScene);
    stage.show();
  }

  public void displayMainView() {
    MainView mainView = new MainView(this.controller, this);
    Scene mainScene = new Scene(mainView.getRoot(), 500, 500);
    stage.setScene(mainScene);
    stage.show();
  }

  public void closeApp() {
    this.stage.close();
  }

}
