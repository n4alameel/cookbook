import controller.Controller;
import view.LoaderView;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {

  private LoaderView loaderView = new LoaderView();

  @Override
  public void start(Stage primaryStage) throws Exception {
    loaderView.start(new Stage());

    Controller controller = Controller.getInstance();
    controller.setStage(primaryStage);
    PauseTransition delay = new PauseTransition(Duration.seconds(2));

    delay.setOnFinished(event -> {
      loaderView.stage.hide();
      primaryStage.setTitle("Galactic Goodness");
      controller.displayUsersView();
    });
    delay.play();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
