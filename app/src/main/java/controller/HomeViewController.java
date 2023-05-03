package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.List;

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

    List<String> data = controller.selectDataFromDatabase();

    int i = 0;
    for (String value : data) {
      if (value.contentEquals(query) || value.equalsIgnoreCase(query)){
        System.out.println(data.get(i));
        // this is when recipe is found in database.
      }
      i++;
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

}
