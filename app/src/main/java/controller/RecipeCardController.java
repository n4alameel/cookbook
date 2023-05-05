package controller;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Recipe;

public class RecipeCardController {
  private Controller controller = Controller.getInstance();
  private PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
  @FXML
  private Label recipeName;
  @FXML
  private ImageView recipePic;
  @FXML
  private ImageView favBtn;
  @FXML
  private Label recipeId;
  @FXML
  private AnchorPane shortDescPane;
  @FXML
  private Label shortDesc;

  /**
   * Updates the informations of the card.
   * It will add the name, the tags and the picture of the given recipe.
   * It also updates the state of the favourite button.
   * 
   * @param r The recipe to display on this card.
   */
  public void updateCard(Recipe r) {
    recipeName.setText(r.getName());
    recipeId.setText(Integer.toString(r.getId()));
    if (controller.getActiveUser().isFavourite(r)) {
      favBtn.setImage(new Image("img/HeartFull.png"));
    }
    shortDesc.setText(r.getDescription());
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
    delay.setOnFinished(event -> {
      shortDescPane.setVisible(true);
    });
    delay.playFromStart();
  }

  @FXML
  private void hideDescription() {
    delay.stop();
    shortDescPane.setVisible(false);
  }
}
