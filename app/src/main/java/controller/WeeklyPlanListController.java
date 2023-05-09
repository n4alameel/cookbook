package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import model.WeeklyList;

public class WeeklyPlanListController {
  Controller controller = Controller.getInstance();

  @FXML
  private VBox listBox;
  @FXML
  private Line separator;
  @FXML
  private VBox noListPane;
  @FXML
  private Pane newWeeklyPane;
  @FXML
  private DatePicker dateSelector;
  @FXML
  private Pane selectionPane;
  @FXML
  private Label weekSelected;
  @FXML
  private Label dayRange;

  @FXML
  private void eventCreateWeeklyList() throws IOException {
    int weekNumber = Integer.parseInt(weekSelected.getText());
    int year = 2023;
    controller.createEmptyWeeklyList(weekNumber, year);
    // To add : error output
    updateWindow();
  }

  @FXML
  private void toggleNewWeeklyPane() throws IOException {

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
