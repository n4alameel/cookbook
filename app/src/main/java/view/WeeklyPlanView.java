package view;

import java.io.IOException;

import controller.WeeklyPlanViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import model.WeeklyList;

public class WeeklyPlanView {
  private ScrollPane root;

  public WeeklyPlanView(WeeklyList weeklyList) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/WeeklyPlanView.fxml"));
      root = (ScrollPane) loader.load();
      WeeklyPlanViewController weeklyPlanListController = (WeeklyPlanViewController) loader.getController();
      weeklyPlanListController.setWeeklyList(weeklyList);
      weeklyPlanListController.updateWindow();
    } catch (IOException e) {
      e.printStackTrace(System.out);
      throw new RuntimeException(e);
    }
  }

  public ScrollPane getRoot() {
    return this.root;
  }
}
