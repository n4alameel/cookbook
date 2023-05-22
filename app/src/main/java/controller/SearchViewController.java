package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Ingredient;
import model.Query;
import model.Recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchViewController {

  Query searchQuery;

  Controller controller = Controller.getInstance();

  private int pageNumber = -1;
  @FXML
  private Pagination recipePages;
  @FXML
  private GridPane searchGrid;

  /*
   * TO DO
   * - When textField is empty "show no results" or give smth like a tag line
   * "Enter something"....
   * - Extend for US-07/08 to search ingredients/tags
   * - Fix other Windows
   * - Give CSS.
   */

  public void searchRecipe(Query queryModel) throws IOException {
    String query = queryModel.getQuery();
    List<String> data = controller.selectRecipeFromDatabase();
    ArrayList<Recipe> recipeList = controller.getRecipeList();
    searchGrid.getChildren().clear();
    int i = 0;
    int col = 0;
    int row = 0;
    for (String value : data) {
      if (value.contains(query) || value.toLowerCase().contains(query.toLowerCase())) {
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

  public void searchIngredients(Query queryModel) {
    String query = queryModel.getQuery();
    ArrayList<Ingredient> data = controller.selectIngredientsFromDatabase();
    ArrayList<Recipe> recipeList = controller.getRecipeList();

    searchGrid.getChildren().clear();
    int col = 0;
    int row = 0;

    List<String> ingredientList = Arrays.asList(query.split(","));
    ArrayList<Integer> currentRecipes = new ArrayList<Integer>();

    for (String searchIngredient : ingredientList) {
      searchIngredient = searchIngredient.trim();
      for (Ingredient ingredient : data) {
        if (ingredient.getIngredientName().contains(searchIngredient)
            || ingredient.getIngredientName().toLowerCase().contains(searchIngredient.toLowerCase())) {
          for (Recipe recipe : recipeList) {
            int recipeId = recipe.getId();
            int ingredientId = ingredient.getRecipeID();

            if (currentRecipes.contains(recipeId)) {
              continue;
            }
            if (recipeId == ingredientId) {
              currentRecipes.add(recipeId);
              try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeCard.fxml"));
                Pane root = loader.load();
                RecipeCardController cardController = loader.getController();
                cardController.setRecipe(recipe.getId());
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
          }
        }
      }
    }
  }
}
