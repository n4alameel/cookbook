package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Recipe;
import model.WeeklyList;
import model.WeeklyList.WeekDay;

public class WeeklyPlanViewController {
  private Controller controller = Controller.getInstance();
  private WeeklyList weeklyList;

  @FXML
  private Label weekNum;
  @FXML
  private Label year;
  @FXML
  private VBox monCol;
  @FXML
  private VBox tueCol;
  @FXML
  private VBox wedCol;
  @FXML
  private VBox thuCol;
  @FXML
  private VBox friCol;
  @FXML
  private VBox satCol;
  @FXML
  private VBox sunCol;

  @FXML
  private void eventShoppingList() throws IOException {

  }

  public WeeklyList getWeeklyList() {
    return weeklyList;
  }

  public void setWeeklyList(WeeklyList weeklyList) {
    this.weeklyList = weeklyList;
  }

  public void updateWindow() {
    weekNum.setText(Integer.toString(weeklyList.getWeekNumber()));
    year.setText(Integer.toString(weeklyList.getYear()));
    EnumMap<WeekDay, ArrayList<Recipe>> list = weeklyList.getList();
    VBox[] columns = { monCol, tueCol, wedCol, thuCol, friCol, satCol, sunCol };
    WeekDay[] days = WeekDay.values();
    int i = 0;

    for (VBox col : columns) {
      col.getChildren().clear();
      ArrayList<Recipe> plan = list.get(days[i]);
      for (Recipe r : plan) {
        try {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/WeeklyPlanViewCard.fxml"));
          AnchorPane root = (AnchorPane) loader.load();
          WeeklyPlanViewCardController weeklyPlanviewCardController = (WeeklyPlanViewCardController) loader
              .getController();
          weeklyPlanviewCardController.setRecipe(r);
          weeklyPlanviewCardController.setWeeklyList(weeklyList);
          weeklyPlanviewCardController.setDay(days[i]);
          weeklyPlanviewCardController.updateCard();
          Separator sep = new Separator(Orientation.HORIZONTAL);
          sep.setPrefHeight(10);
          col.getChildren().addAll(root, sep);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      i++;
    }
  }

}
