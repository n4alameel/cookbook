package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainView {
  private VBox root;

  public MainView(Controller controller, ActiveView activeView) {
    this.root = new VBox();

    this.root.setPadding(new Insets(5));
    this.root.setSpacing(5);

    Label hello = new Label("Hello " + controller.getActiveUser().getUsername() + " !");
    Button quit = new Button("Quit");
    Button search = new Button("Search");

    quit.setOnAction(e -> {
      activeView.closeApp();
    });

    search.setOnAction(e -> {
      activeView.displaySearchView();
    });

    this.root.getChildren().addAll(hello, search, quit);
  }

  public Parent getRoot() {
    return this.root;
  }
}
