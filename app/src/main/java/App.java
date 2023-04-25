import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage; 
import view.ActiveView;

public class App extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    Controller controller = new Controller();
    ActiveView activeView = new ActiveView(controller, primaryStage);

    primaryStage.setTitle("Galactic Goodness");
    activeView.displayLoginScene();
  }

  public static void main(String[] args) {
    launch(args); 
  }
}
