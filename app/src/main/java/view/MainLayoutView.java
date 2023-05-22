package view;

import controller.MainLayoutController;
import controller.RecipeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import java.io.IOException;

public class MainLayoutView {
  private FXMLLoader loader;
  private Parent root;

  public MainLayoutView() {
    try {
      this.loader = new FXMLLoader(getClass().getResource("/MainLayout.fxml"));
      this.root = loader.load();
      MainLayoutController mainLayoutController = this.loader.getController();
      mainLayoutController.updateLayout();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Parent getRoot() {
    return this.root;
  }

  public void LoadContent(Parent mainPageCenterContent, boolean initialLoad) throws IOException {
    MainLayoutController mainLayoutController = this.loader.getController();
    if (!initialLoad) {
      mainLayoutController.getController().addRootToHistory(mainLayoutController.getRootLayout().getCenter());
    }
    mainLayoutController.getRootLayout().setCenter(mainPageCenterContent);
    mainLayoutController.updateArrows();

  }
}
