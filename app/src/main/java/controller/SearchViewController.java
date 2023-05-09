package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchViewController {
    Controller controller = Controller.getInstance();

    @FXML
    private TextField search;

    @FXML
    private GridPane searchGrid;

    public void searchBtnClicked() throws IOException {
        String query = search.getText();

        List<String> data = controller.selectDataFromDatabase();
        ArrayList<Recipe> recipeList = controller.getRecipeList();

        searchGrid.getChildren().clear();

        int i = 0;
        int col = 0;
        int row = 0;
        for (String value : data) {
            if (value.contentEquals(query) || value.equalsIgnoreCase(query)) {
                System.out.println(data.get(i));
                System.out.println(recipeList.get(i));

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeCard.fxml"));
                    Pane root = loader.load();
                    RecipeCardController cardController = loader.getController();
                    cardController.setRecipe(recipeList.get(i).getId());
                    cardController.updateCard();
                    searchGrid.add(root, col % 3, row);
                    col++;
                    if (col % 3 == 0) {
                        row++;
                    }
                } catch (IOException e) {
                    e.printStackTrace(System.out);
                }
            }
            i++;
        }
    }

    public void searchEnter(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            searchBtnClicked();
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
