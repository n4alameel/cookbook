package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;


public class HomeWindow {
    private Parent root;
    public HomeWindow(Controller controller, ActiveView activeView) {
        //setting the scene
        try {
            root = FXMLLoader.load(getClass().getResource("/HomeWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Parent getRoot() {
        return this.root;
    }


}