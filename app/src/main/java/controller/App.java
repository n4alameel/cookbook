package controller;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import javafx.application.Application;
//import javafx.stage.Stage;
//import view.ActiveView;
//import view.RecipeView;
//public class App  extends Application {
//
//  @Override
//  public void start(Stage primaryStage) throws Exception {
//    RecipeView recipeView = new RecipeView(Controller controller, ActiveView activeView);
//    recipeView.show();
//  }
//
//  public Connection dbconnect(){
//    Connection conn = null;
//    try {
//      conn = DriverManager
//              .getConnection("jdbc:mysql://localhost/cookbook?user=root&password=Ira.ko03&useSSL=false");
//    } catch (SQLException e) {
//      System.out.println(e.getMessage());
//    }
//    return conn;
//  }
//  public static void main(String[] args) {
//    launch(args);
//  }
//
//}
import controller.Controller;
import model.Ingredient;
import model.Recipe;
import view.LoaderView;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.ActiveView;

import java.util.ArrayList;

public class App extends Application {

  private LoaderView loaderView = new LoaderView();

  @Override
  public void start(Stage primaryStage) throws Exception {
    loaderView.start(new Stage());

    Controller controller = new Controller();
    PauseTransition delay = new PauseTransition(Duration.seconds(2));

    delay.setOnFinished(event -> {
      loaderView.stage.hide();
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

