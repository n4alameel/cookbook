package controller;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Recipe;

public class RecipeCardController {
  private Controller controller = Controller.getInstance();
  private Recipe recipe;

  // Delay used when hovering a card to not show the short description panel
  // directly
  private PauseTransition delay = new PauseTransition(Duration.seconds(0.5));

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
  @FXML
  private AnchorPane shortDescPane;
  @FXML
  private Label shortDesc;


  /**
   * Updates the informations of the card.
   * It will add the name, the tags and the picture of the given recipe.
   * It also updates the state of the favourite button.
   *
   * @param selectedRecipe The recipe to display on this card.
   */
  public void setRecipe(Recipe selectedRecipe){
    recipe = selectedRecipe;
  }
  public void updateCard() {
    recipeName.setText(this.recipe.getName());
    recipeId.setText(Integer.toString(this.recipe.getId()));
    if (controller.getActiveUser().isFavourite(this.recipe)) {
      favBtn.setImage(new Image("img/HeartFull.png"));
    }
    shortDesc.setText(this.recipe.getDescription());
  }


  @FXML
  private void sendRecipeEvent() {

  }

  /**
   * Toggle the favourite status of the recipe. It sends the id hidden on the card
   * to the controller and then changes the look of the favourite button depending
   * of the new status.
   */
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

  /**
   * Shows the short description panel after a small delay.
   */
  @FXML
  private void showDescription() {
    delay.setOnFinished(event -> {
      shortDescPane.setVisible(true);
    });
    delay.playFromStart();
  }

  /**
   * Hides the short description panel.
   * It also interrupts any delay started by {@code showDescription()} so that if
   * you remove the ursor from the card before the end of the delay, the panel
   * will not show.
   */
  @FXML
  private void hideDescription() {
    delay.stop();
    shortDescPane.setVisible(false);
  }
}
