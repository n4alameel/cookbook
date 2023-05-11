package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Query;
import model.Recipe;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeViewController {

    @FXML
    private GridPane searchGrid;

    @FXML
    private Pane homePane;

    @FXML
    private Pane searchPane;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox categoryComboBox;

    private SearchViewController searchController = new SearchViewController();

    Controller controller = Controller.getInstance();

    public void initialize() {
        categoryComboBox.setValue("Recipe");
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

    public void seeFavourites() throws IOException {
        controller.displayFavouriteView();
    }

    public void openSearchPage() throws IOException {
        Query search = new Query();
        search.setQuery(searchField.getText());

        homePane.setVisible(false);
        searchPane.setVisible(true);
        System.out.println(search.getQuery());

        String selectedOption = (String) categoryComboBox.getValue();

        switch (selectedOption) {
            case "Recipe": {
                searchController.searchRecipe(search, searchGrid);
                break;
            } case "Ingredients": {
                System.out.println("Ingredients selected");
                break;
            } case "Tags": {
                System.out.println("Tags selected");
                break;
            }
        }

    }

    public void onSearchEnter(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            openSearchPage();
        }
    }
}
