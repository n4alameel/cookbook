package view;

import java.io.IOException;

import controller.WeeklyPlanListController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class WeeklyPlanListView {

  private Parent root;

  public WeeklyPlanListView() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/WeeklyPlanList.fxml"));
      root = (Parent) loader.load();
      WeeklyPlanListController weeklyPlanListController = (WeeklyPlanListController) loader.getController();
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
