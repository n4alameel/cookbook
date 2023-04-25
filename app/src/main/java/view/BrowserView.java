package view;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class BrowserView {
  private GridPane root;

  public BrowserView(Controller controller, ActiveView activeView) {
    this.root = new GridPane();

    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(11.5, 12.5, 13.5, 14.4));
    root.setHgap(5.5);
    root.setVgap(5.5);

  }

  public Parent getRoot() {
    return this.root;
  }
}
