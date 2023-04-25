package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Text;

public class App extends Application {

    private Loader loader = new Loader();


//  @Override
//  public void start(Stage primaryStage) throws Exception {
//    Loader preloader = new Loader();
//    preloader.start(new Stage());
//    preloader.stage.toFront();
//
//    VBox root = new VBox();
//    root.setPadding(new Insets(5));
//
//    Label title = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
//

    //
//    primaryStage.setScene(new Scene(root, 400, 200));
//    primaryStage.setTitle("JavaFX");
//    primaryStage.show();
//    primaryStage.toBack();
//
//
//    PauseTransition delay = new PauseTransition(Duration.seconds(1));
//    delay.setOnFinished(event -> {
//      primaryStage.show(); // Show the primary stage after the delay
//      loader.stage.hide(); // Hide the loader
//    });
//  }
    @Override
    public void start(Stage primaryStage) throws Exception {
        loader.start(new Stage());

        // Scene for Window that needs to open after Loader.
        VBox root = new VBox();
        root.setPadding(new Insets(5));
        Label title = new Label("Lorem ipsum id est laborum.");

        /*Label mysql;
        try {
            Connection conn = DriverManager
            .getConnection("jdbc:mysql://localhost/cookbook?user=root&password=ABC&useSSL=false");
            mysql = new Label("Driver found and connected");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id = 1");
            rs.next();
            System.out.println(rs.getString("username")); //Exemple of a select from the database.
            } catch (SQLException e) {
                mysql = new Label("An error has occurred: " + e.getMessage());
            }
        */

        root.getChildren().addAll(title/*, mysql*/);
        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.setTitle("JavaFX");
        primaryStage.show();
        primaryStage.toBack();

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> {
            loader.stage.hide();
            primaryStage.toFront();
        });
        delay.play();
    }

    public Connection dbconnect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/cookbook?user=root&password=ABC&useSSL=false");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        launch();
    }
}
