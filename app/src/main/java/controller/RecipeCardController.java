package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Recipe;

public class RecipeCardController {
  private Controller controller = Controller.getInstance();
  @FXML
  private Label recipeName;
  @FXML
  private ImageView recipePic;
  @FXML
  private ImageView favBtn;
  @FXML
  private Label recipeId;

  public void updateCard(Recipe r) {
    recipeName.setText(r.getName());
    recipeId.setText(Integer.toString(r.getId()));
    if (controller.getActiveUser().isFavourite(r)) {
      System.out.println(r.toString());
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

  }

  @FXML
  private void showDescription() {
  }

  @FXML
  private void hideDescription() {
  }
}
