package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class HelpController {
  Controller controller = Controller.getInstance();

  public void backToHome() throws IOException {
    controller.displayHomeView(false);
  }

}
