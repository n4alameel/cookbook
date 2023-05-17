package view;

import controller.HomeViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import java.io.IOException;

public class HomeView {
  private Parent root;

  public HomeView() throws IOException {
    // setting the scene
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeWindow.fxml"));
    this.root = (Parent) loader.load();
    HomeViewController homeViewController = loader.getController();
    homeViewController.showFavourites();
    homeViewController.showRecommondations();

  }

  public Parent getRoot() {
    return this.root;
  }
}