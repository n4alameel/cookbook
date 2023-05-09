package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Query;
import model.Recipe;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeViewController {

    @FXML
    ComboBox categoryComboBox;

    @FXML
    TextField searchField;

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

    public void seeFavourites() throws IOException {
        controller.displayFavouriteView();
    }

    public void openSearchPage() throws IOException {
        String query = searchField.getText().toString();
        Query queryModel = new Query();
        System.out.println(query);
        queryModel.setQuery(query);
        System.out.println(queryModel.getQuery());
        controller.displaySearchView();
    }

    public void onSearchEnter(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            openSearchPage();
        }
    }
}
