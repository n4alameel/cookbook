package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
  @FXML
  private Label recipeId;

  public void setRecipe(Recipe selectedRecipe){
    recipe = selectedRecipe;
  }
  public void updateCard() {
    recipeName.setText(this.recipe.getName());
    recipeId.setText(Integer.toString(this.recipe.getId()));
    if (controller.getActiveUser().isFavourite(recipe)) {
      favBtn.setImage(new Image("img/HeartFull.png"));
    }
  }


  @FXML
  private void sendRecipeEvent() {

  }

  @FXML
  private void toggleFavourite() {
    if (controller.toggleFavourite(Integer.parseInt(recipeId.getText()))) {
      favBtn.setImage(new Image("img/HeartFull.png"));
    } else {
      favBtn.setImage(new Image("img/Heart.png"));
    }
  }

  @FXML
  private void seeRecipe() {
    controller.displayRecipeView(recipe);
  }

  @FXML
  private void showDescription() {
  }

  @FXML
  private void hideDescription() {
  }
}
