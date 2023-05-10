package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import model.Time;
import model.WeeklyList;

public class WeeklyPlanListController {
  Controller controller = Controller.getInstance();

  private int weekNum;
  private int year;
  private LocalDate startDay;
  private LocalDate endDay;

  @FXML
  private VBox listBox;
  @FXML
  private Line separator;
  @FXML
  private VBox noListPane;
  @FXML
  private Pane newWeeklyPane;
  @FXML
  private DatePicker daySelector;
  @FXML
  private Pane selectionPane;
  @FXML
  private Label weekSelected;
  @FXML
  private Label dayRange;

  @FXML
  private void eventCreateWeeklyList() throws IOException {
    if (selectionPane.isVisible()) {
      if (controller.createEmptyWeeklyList(weekNum, year)) {
        System.out.println("New Weekly list created for week n° " + weekNum);
        toggleNewWeeklyPane();
        updateWindow();
      } else {
        System.out.println("Weekly list creation failed");
      }
    }

  }

  @FXML
  private void toggleNewWeeklyPane() throws IOException {
    if (newWeeklyPane.isVisible()) {
      newWeeklyPane.setVisible(false);
      selectionPane.setVisible(false);
      daySelector.setValue(null);
    } else {
      newWeeklyPane.setVisible(true);
    }

  }

  @FXML
  private void updateSelection() throws IOException {
    selectionPane.setVisible(true);
    LocalDate selectedDate = daySelector.getValue();
    Time time = new Time();
    weekNum = time.getWeekNumberFromDate(selectedDate);
    year = selectedDate.getYear();
    startDay = time.getMondayFromWeekNumber(weekNum, year);
    endDay = time.getSundayFromWeekNumber(weekNum, year);
    weekSelected.setText("Week n° " + weekNum);
    dayRange.setText("From " + startDay.toString() + " to " + endDay.toString());
  }

  @FXML
  private void goToMainMenu() throws IOException {
    controller.displayHomeView();
  }

  public void updateWindow() {
    ArrayList<WeeklyList> planslist = controller.getActiveUser().getWeeklyPlanList();
    for (WeeklyList list : planslist) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WeeklyPlanRow.fxml"));
        HBox root = (HBox) loader.load();
        WeeklyPlanRowController weeklyPlanRowController = (WeeklyPlanRowController) loader.getController();
        weeklyPlanRowController.setWeeklyList(list);
        weeklyPlanRowController.updateRow();
        Line newSeparator = new Line();
        newSeparator.setStartX(-100);
        newSeparator.setEndX(940);
        listBox.getChildren().addAll(root, newSeparator);
      } catch (IOException e) {
        e.printStackTrace(System.out);
        throw new RuntimeException(e);
      }
    }

  }
}
