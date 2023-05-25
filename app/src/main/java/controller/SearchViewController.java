package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Ingredient;
import model.Query;
import model.Recipe;
import model.Tag;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchViewController {
  Controller controller = Controller.getInstance();
  private int pageNumber = -1;
  @FXML
  private Pagination recipePages;
  @FXML
  private GridPane searchGrid;

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
          searchGrid.getChildren().get(searchGrid.getChildren().size() - 1).toBack();
          col++;
          if (col % 3 == 0) {
            row++;
          }
        } catch (IOException e) {
          e.printStackTrace(System.out);
        } catch (SQLException e) {
          throw new RuntimeException(e);
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
                searchGrid.getChildren().get(searchGrid.getChildren().size() - 1).toBack();
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

  public void searchTags(Query queryModel) throws IOException {
    String query = queryModel.getQuery();
    ArrayList<Tag> data = controller.selectTagsFromDatabase();
    ArrayList<Recipe> recipeList = controller.getRecipeList();

    searchGrid.getChildren().clear();
    int col = 0;
    int row = 0;

    List<String> tagList = Arrays.asList(query.split(","));
    ArrayList<Integer> currentRecipes = new ArrayList<Integer>();

    for (String searchTag : tagList) {
      searchTag = searchTag.trim();
      for (Tag tag : data) {
        if (tag.getName().contains(searchTag)
            || tag.getName().toLowerCase().contains(searchTag.toLowerCase())) {
          for (Recipe recipe : recipeList) {
            int recipeId = recipe.getId();
            int tagId = tag.getUser_id();

            if (currentRecipes.contains(recipeId)) {
              continue;
            }
            if (recipeId == tagId) {
              currentRecipes.add(recipeId);
              try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeCard.fxml"));
                Pane root = loader.load();
                RecipeCardController cardController = loader.getController();
                cardController.setRecipe(recipe.getId());
                cardController.updateCard();
                searchGrid.add(root, col % 3, row);
                searchGrid.getChildren().get(searchGrid.getChildren().size() - 1).toBack();
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
