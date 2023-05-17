package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class SearchView {

  public Parent getRoot() {
    return root;
  }

  public Parent root;

  public SearchView() {
    try {
      this.root = FXMLLoader.load(getClass().getResource("/SearchWindow.fxml"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
