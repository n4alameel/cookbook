import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.CookbookView;

public class App extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    Controller controller = new Controller();
    CookbookView loginView = new CookbookView(controller);

    Scene loginScene = new Scene(loginView.getRoot());

    primaryStage.setTitle("Galactic Goodness");
    primaryStage.setScene(loginScene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
