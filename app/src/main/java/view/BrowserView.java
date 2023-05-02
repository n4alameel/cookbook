package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Recipe;

public class BrowserView {
  private GridPane root;

  public BrowserView(Controller controller) {
    this.root = new GridPane();

    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(11.5, 12.5, 13.5, 14.4));
    root.setHgap(5.5);
    root.setVgap(5.5);

    int y = 0;
    int x = 0;

    for (Recipe r : controller.getRecipeList()) {
      Button recipLabel = new Button(r.getName());
      recipLabel.setOnAction(e -> {
        controller.displayRecipeView(r);
      });
      root.add(recipLabel, (x % 3), y);
      x++;
      if (x % 3 == 0) {
        y++;
      }
    }

    Button quit = new Button("Quit");
    quit.setOnAction(e -> {
      controller.closeApp();
    });
    root.add(quit, 3, y + 1);
  }

  public Parent getRoot() {
    return this.root;
  }
}
