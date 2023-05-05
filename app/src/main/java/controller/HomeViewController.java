package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class HomeViewController {
  Controller controller = Controller.getInstance();

  public void openAllRecipes() throws IOException {
    controller.displayBrowserView();
  }

  public void goToHomePage() throws IOException {
    controller.displayHomeView();
  }

  public void openNewRecipe() throws IOException {
    controller.displayNewRecipeView();
  }

  public void openSearchPage() throws  IOException {
    controller.displaySearchView();
  }

  public void searchEnter(KeyEvent keyEvent) throws IOException {
      openSearchPage();
  }
}
