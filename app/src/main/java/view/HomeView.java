package view;

import controller.HomeViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class HomeView {
  private Parent root;

  public HomeView() {
    // setting the scene
    try {
      FXMLLoader loader =  new FXMLLoader(getClass().getResource("/HomeWindow.fxml"));
      root = (Parent) loader.load();
      HomeViewController homeViewController = loader.getController();
      homeViewController.showFavourites();
      homeViewController.showRecommondations();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Parent getRoot() {
    return this.root;
  }

}