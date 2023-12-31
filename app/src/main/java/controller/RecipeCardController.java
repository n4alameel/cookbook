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

import java.io.IOException;
import java.sql.SQLException;

import java.io.InputStream;
import java.sql.SQLException;

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
    private AnchorPane shortDescPane;
    @FXML
    private Label shortDesc;

    /**
     * Updates the informations of the card.
     * It will add the name, the tags and the picture of the given recipe.
     * It also updates the state of the favourite button.
     *
     * @param recipe The recipe to display on this card
     */

    public void setRecipe(int selectedRecipeId) {
        Recipe updatedRecipe = this.controller.getRecipeById(selectedRecipeId);
        this.recipe = updatedRecipe;
    }

    public void updateCard() throws SQLException {
        recipeName.setText(this.recipe.getName());
        if (controller.getActiveUser().isFavourite(this.recipe)) {
            favBtn.setImage(new Image("img/HeartFull.png"));
        }
        shortDesc.setText(this.recipe.getDescription());
        showBlob();
    }

    @FXML
    private void sendRecipeEvent() throws IOException {
        controller.displaySendMessageView(recipe);
    }

    /**
     * Toggle the favourite status of the recipe. It sends the id hidden on the card
     * to the controller and then changes the look of the favourite button depending
     * of the new status.
     */
    @FXML
    private void toggleFavourite() {
        if (controller.toggleFavourite(this.recipe)) {
            favBtn.setImage(new Image("img/HeartFull.png"));
        } else {
            favBtn.setImage(new Image("img/Heart.png"));
        }
    }

  @FXML
  private void seeRecipe() throws IOException {
    this.controller.displayRecipeView(this.recipe.getId());
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

    public void showBlob() throws SQLException {
      Image image = (new Image("img/Default.png"));
            double w = 0;
            double h = 0;

            double ratioX = recipePic.getFitWidth() / image.getWidth();
            double ratioY = recipePic.getFitHeight() / image.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = image.getWidth() * reducCoeff;
            h = image.getHeight() * reducCoeff;

            recipePic.setX((recipePic.getFitWidth() - w) / 2);
            recipePic.setY((recipePic.getFitHeight() - h) / 2);
        try {
            if(recipe.getBlob() != null)
                image = new Image(recipe.getBlob());
          recipePic.setImage(image);

        } catch (Exception e) {
          System.out.println(e);
        }
    }
}
