package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.WeeklyList;

public class WeeklyPlanRowController {
  private Controller controller = Controller.getInstance();
  private WeeklyList weeklyList;

  @FXML
  private Label weekNum;
  @FXML
  private Label startDay;
  @FXML
  private Label endDay;
  @FXML
  private Label daysWithMeals;
  @FXML
  private Label mealsAmount;
  @FXML
  private Label creationDate;
  @FXML
  private Pane erasePane;

  @FXML
  private void eraseList() throws IOException {
    erasePane.setVisible(true);
  }

  @FXML
  private void confirmErase() throws IOException {
    controller.deleteWeeklyList(this.weeklyList.getId());
    controller.displayWeeklyPlanListView();
  }

  @FXML
  private void cancelErase() throws IOException {
    erasePane.setVisible(false);
  }

  @FXML
  private void viewList() throws IOException {
    controller.displayWeeklyPlanView(weeklyList);
  }

  public void setWeeklyList(WeeklyList weeklyList) {
    this.weeklyList = weeklyList;
  }

  public void updateRow() {
    weekNum.setText(Integer.toString(weeklyList.getWeekNumber()));
    startDay.setText(weeklyList.getStartDate().toString());
    endDay.setText(weeklyList.getEndDate().toString());
    creationDate.setText(weeklyList.getCreationDate().toString());
    mealsAmount.setText(Integer.toString(weeklyList.computeTotalMeal()));
    daysWithMeals.setText(Integer.toString(weeklyList.computeDayswithMeals()));
  }

}
