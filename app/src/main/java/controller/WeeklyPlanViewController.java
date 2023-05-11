package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
  private void goToMainMenu() throws IOException {
    controller.displayHomeView();
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
  }

}
