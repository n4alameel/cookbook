package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.NewSearch;

import java.io.IOException;

public class HomeViewController {
  Controller controller = Controller.getInstance();

  @FXML
  private TextField search;

  public void searchEnter(KeyEvent keyEvent) throws IOException {
    if (keyEvent.getCode() == KeyCode.ENTER) {
      searchBtnClicked();
    }
  }

  public void searchBtnClicked() throws IOException {
    String query = search.getText();
    System.out.println("Search query: " + query);
  }

  public void openAllRecipes() throws IOException {
    controller.displayBrowserView();
  }

  public void goToHomePage() throws IOException {
    controller.displayHomeView();
  }

  public void openNewRecipe() throws IOException {
    controller.displayNewRecipeView();
  }

}
