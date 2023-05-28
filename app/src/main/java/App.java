import controller.Controller;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.LoaderView;

public class App extends Application {

  private LoaderView loaderView = new LoaderView();

  @Override
  public void start(Stage primaryStage) throws Exception {
    Image iconImage = new Image(App.class.getResourceAsStream("img/pictogram.png"));

    primaryStage.getIcons().add(iconImage);

    loaderView.start(new Stage());


    Controller controller = Controller.getInstance();
    controller.setStage(primaryStage);
    PauseTransition delay = new PauseTransition(Duration.seconds(2));

    delay.setOnFinished(event -> {
      loaderView.stage.hide();
      primaryStage.setTitle("Galactic Goodness");
      controller.displayLoginScene();
    });
    delay.play();
  }

  public static void main(String[] args) {
    launch(args);
    Controller.getInstance().dbClose();
  }
}
