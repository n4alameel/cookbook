package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Recipe;

import java.io.IOException;


public class FavouriteRecipeController {

    private int pageNumber = -1;
    @FXML
    private Pagination favouritePages;
    @FXML
    private GridPane favouriteGrid;

    Controller controller = Controller.getInstance();

    public FavouriteRecipeController() {
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

    /**
     * Initialize the pagination system
     */
    public void initPagination() {
        ObservableList<Recipe> favouriteList = controller.getFavourites();
        int maxPageNum = (favouriteList.size() / 9) + 1;
        favouritePages.setPageCount(maxPageNum);
        favouritePages.setPageFactory((pageIndex) -> {
            updatePage(pageIndex);
            return new Pane();
        });
        updatePage(0);
    }

    /**
     * Update the shown page with the corresponding recipe cards
     *
     * @param pageIndex The page index got from the factory
     */
    public void updatePage(int pageIndex) {
        // If you click on the page you are, nothing happens
        if (pageIndex == this.pageNumber) {
            return;
        }
        this.pageNumber = pageIndex;
        ObservableList<Recipe> favouriteList = controller.getFavourites();
        int recipeNum = favouriteList.size();

        // Clear the actual grid
        favouriteGrid.getChildren().clear();

        int currentIndex = 9 * pageIndex;
        int lastRecipeIndex = 9 * (pageIndex + 1);
        int col = 0;
        int row = 0;
        // While the recipe list is not empty or we have not added 9 cards
        while (currentIndex < recipeNum && currentIndex < lastRecipeIndex) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeCard.fxml"));
                Pane root = loader.load();
                RecipeCardController cardController = loader.getController();
                cardController.updateCard(favouriteList.get(currentIndex));
                favouriteGrid.add(root, col % 3, row);
                col++;
                if (col % 3 == 0) {
                    row++;
                }
                currentIndex++;
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }

        }
    }

}
