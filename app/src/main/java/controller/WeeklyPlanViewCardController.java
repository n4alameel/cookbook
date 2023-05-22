package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import model.Recipe;
import model.WeeklyList;
import model.WeeklyList.WeekDay;

public class WeeklyPlanViewCardController {
  private Controller controller = Controller.getInstance();
  private WeeklyList weeklyList;
  private Recipe recipe;
  private WeekDay day;

  @FXML
  private Label recipeName;
  @FXML
  private Spinner<Integer> portions;
  @FXML
  private Pane erasePane;

  public Recipe getRecipe() {
    return recipe;
  }

  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }

  public WeeklyList getWeeklyList() {
    return weeklyList;
  }

  public void setWeeklyList(WeeklyList weeklyList) {
    this.weeklyList = weeklyList;
  }

  public WeekDay getDay() {
    return day;
  }

  public void setDay(WeekDay day) {
    this.day = day;
  }

  @FXML
  private void toggleErasePane() throws IOException {
    erasePane.setVisible(true);
  }

  @FXML
  private void confirmErase() throws IOException {
    controller.deleteRecipeFromWeeklyList(weeklyList.getId(), day, recipe);
    controller.displayWeeklyPlanView(weeklyList);
  }

  @FXML
  private void cancelErase() throws IOException {
    erasePane.setVisible(false);
  }

  @FXML
  private void viewRecipe() throws IOException {
    controller.displayRecipeView(recipe.getId());
  }

  public void updateCard() {
    recipeName.setText(recipe.getName());
    portions.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 2048, 2, 2));
  }

}
