package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.NewSearch;

import java.io.IOException;

public class HomeViewController {
  Controller controller = Controller.getInstance();

  @FXML
  private static TextField search;

  @FXML
  public static String getQuery(){
    String query = search.getText();
    System.out.println(query);
    return query;
  }

  public static void searchBtnClicked(KeyEvent keyEvent) throws IOException {
    if (keyEvent.getCode() == KeyCode.ENTER) {
      try {
        System.out.println("Search query: " + getQuery());

      } catch (Exception e) {
        System.out.println("Something went wrong.");
      }
    }
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

  public void searchBtnClicked() throws IOException {
    this.searchBtnClicked();
  }

}
