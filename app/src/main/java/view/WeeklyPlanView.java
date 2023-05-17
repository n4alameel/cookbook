package view;

import java.io.IOException;

import controller.WeeklyPlanViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import model.WeeklyList;

public class WeeklyPlanView {
  private Parent root;

  public WeeklyPlanView(WeeklyList weeklyList) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/WeeklyPlanView.fxml"));
      root = (Parent) loader.load();
      WeeklyPlanViewController weeklyPlanListController = (WeeklyPlanViewController) loader.getController();
      weeklyPlanListController.setWeeklyList(weeklyList);
      weeklyPlanListController.updateWindow();
    } catch (IOException e) {
      e.printStackTrace(System.out);
      throw new RuntimeException(e);
    }
  }

  public Parent getRoot() {
    return this.root;
  }
}
