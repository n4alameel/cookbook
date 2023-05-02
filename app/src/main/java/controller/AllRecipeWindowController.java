package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AllRecipeWindowController {
  private Controller controller = Controller.getInstance();

  public void openHomepage(ActionEvent event) throws IOException {

  }

  public void goToHomePage(MouseEvent event) throws IOException {
    controller.displayHomeView();
  }

}
