import controller.Controller;
import controller.Loader;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.ActiveView;

public class App extends Application {

  private Loader loader = new Loader();

  @Override
  public void start(Stage primaryStage) throws Exception {
    loader.start(new Stage());
    Controller controller = new Controller();

    PauseTransition delay = new PauseTransition(Duration.seconds(2));

    delay.setOnFinished(event -> {
      loader.stage.hide();
      ActiveView activeView = new ActiveView(controller, primaryStage);
      primaryStage.setTitle("Galactic Goodness");
      activeView.displayLoginScene();
    });

    delay.play();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
