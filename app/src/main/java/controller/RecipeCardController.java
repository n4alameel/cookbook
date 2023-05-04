package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Recipe;

public class RecipeCardController {
  private Controller controller = Controller.getInstance();
  private Recipe recipe;
  @FXML
  private Label recipeName;
  @FXML
  private ImageView recipePic;
  @FXML
  private ImageView favBtn;
  @FXML
  private Button seeRecipe;
  public void setRecipe(Recipe selectedRecipe){
    recipe = selectedRecipe;
  }
  public void updateCard() {
    recipeName.setText(recipe.getName());
  }

  @FXML
  private void sendRecipeEvent() {

  }

  @FXML
  private void toggleFavorite() {

  }

  @FXML
  private void seeRecipe() {
    controller.displayRecipeView(recipe);
  }

  @FXML
  private void showDescription(){}

  @FXML
  private void hideDescription(){}
}
