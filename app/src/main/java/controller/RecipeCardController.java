package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

  public void updateCard(Recipe r) {
    recipeName.setText(r.getName());
  }
}
