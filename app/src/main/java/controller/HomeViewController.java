package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import model.Recipe;
import javafx.scene.input.KeyEvent;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.ArrayList;
import model.User;

import model.Query;

public class HomeViewController {

    @FXML
    private GridPane favouriteHome;
    @FXML
    private GridPane recommendationsHome;
    Controller controller = Controller.getInstance();
    @FXML
    private Hyperlink adminPanel;
    @FXML
    private Hyperlink activeUser;
    @FXML
    private ImageView activeUserPicture;

    @FXML
    private GridPane searchGrid;

    @FXML
    private Pane homeContentPane;

    @FXML
    private Pane searchPane;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox categoryComboBox;

    private SearchViewController searchController = new SearchViewController();

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

    public void goToMealPlansView() throws IOException {
        controller.displayWeeklyPlanListView();
    }

    public void goToUsersView() throws IOException {
        controller.displayUsersView();
    }

    public void logoutEvent() {
        controller.displayLoginScene();
    }

    public void openHelpPage() throws  IOException{
        controller.displayHelpPage();
    }

    public void showFavourites() {
        ArrayList<Recipe> favouriteList = controller.getActiveUser().getFavouriteList();
        int favNum = favouriteList.size();

        favouriteHome.getChildren().clear();

        int currentIndex = 0;
        int maxNum = 3;
        int col = 0;
        int row = 0;

        while (currentIndex < favNum && currentIndex < maxNum) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeCard.fxml"));
                Pane root = loader.load();
                RecipeCardController cardController = loader.getController();
                cardController.setRecipe(favouriteList.get(currentIndex).getId());
                cardController.updateCard();
                favouriteHome.add(root, col % 3, row);
                col++;
                currentIndex++;
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    /**
     * Shows recommendations on the main page
     */
    public void showRecommondations() {

        ArrayList<Recipe> recipeList = controller.getRecipeList();
        ArrayList<Recipe> recommendationList = new ArrayList<>();
        int index = 0;

        while (index < recipeList.size()) {
            if (controller.getActiveUser().isFavourite(recipeList.get(index)) == false){
                recommendationList.add(recipeList.get(index));
            }
            index++;
        }

        int recipeNum = recommendationList.size();

        // Clear the actual grid
        recommendationsHome.getChildren().clear();

        int currentIndex = 0;
        int maxNum = 3;
        int col = 0;
        int row = 0;
        // While the recipe list is not empty or we have not added 3 cards
        while (currentIndex < recipeNum && currentIndex < maxNum ) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeCard.fxml"));
                Pane root = loader.load();
                RecipeCardController cardController = loader.getController();
                cardController.setRecipe(recommendationList.get(currentIndex).getId());
                cardController.updateCard();
                recommendationsHome.add(root, col % 3, row);
                col++;
                currentIndex++;
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }

        }
    }

    public void showAdminPanel() {
        User user = controller.getActiveUser();
        if(user.isAdmin()==true) adminPanel.setVisible(true);
    }

    public void showActiveUser() {
        User user = controller.getActiveUser();
        activeUser.setText(user.getUsername());
        activeUserPicture.setImage(new Image(user.getImageUrl()));
        Circle circle = new Circle(25, 25, 25);
        activeUserPicture.setClip(circle);
    }

    public void openMessage() throws IOException {
        controller.displayMessageView();
    }

    public void openSearchPage() throws IOException {
        Query search = new Query();
        search.setQuery(searchField.getText());

        homeContentPane.setVisible(false);
        searchPane.setVisible(true);

        String selectedOption = (String) categoryComboBox.getValue();

        switch (selectedOption) {
           case "Ingredients": {
                searchController.searchIngredients(search, searchGrid);
                break;
            } case "Tags": {
                System.out.println("Tags selected");
                break;
            } default: {
                searchController.searchRecipe(search, searchGrid);
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

