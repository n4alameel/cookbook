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

    private SearchViewController searchController = new SearchViewController();

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
//        String query = searchField.getText().toString();
//        Query queryModel = new Query(query);
//        System.out.println(query);
//        queryModel.setQuery(query);
//        System.out.println(queryModel.getQuery());
//        controller.displaySearchView(queryModel);
//        SearchViewController searchVC = new SearchViewController(queryModel);
        Query search = new Query();
        search.setQuery(searchField.getText());

        homePane.setVisible(false);
        searchPane.setVisible(true);
        System.out.println(search.getQuery());

        // geeft error wanneer die dit uitvoerd
        searchController.searchBtnClicked(search, searchGrid);
    }

    public void onSearchEnter(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            openSearchPage();
//            System.out.println("HOI JA GEKLIKT HOOR");
        }
    }
}
