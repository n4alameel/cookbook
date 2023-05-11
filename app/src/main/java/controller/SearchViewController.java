package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Query;
import model.Recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchViewController {

    Query searchQuery;

    SearchViewController(){}

    Controller controller = Controller.getInstance();

    @FXML
    private GridPane searchGrid;


   /* TO DO
        - When textField is empty "show no results" or give smth like a tag line "Enter something"....
        - Extend for US-07/08 to search ingredients/tags
        - Fix other Windows
        - Give CSS.
    */

    public void searchBtnClicked(Query queryModel, GridPane searchGrid) throws IOException {
        String query = queryModel.getQuery();
        List<String> data = controller.selectDataFromDatabase();
        ArrayList<Recipe> recipeList = controller.getRecipeList();

        //        System.out.println(searchField.getText());
        searchGrid.getChildren().clear();

        int i = 0;
        int col = 0;
        int row = 0;
        for (String value : data) {
            if (value.contains(query) || value.toLowerCase().contains(query)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeCard.fxml"));
                    Pane root = loader.load();
                    RecipeCardController cardController = loader.getController();
                    cardController.setRecipe(recipeList.get(i));
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

}
