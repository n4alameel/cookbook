package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewRecipeController implements Initializable {
  @FXML
  private TextField nameField;
  @FXML
  private TextField shortDiscriptionField;
  @FXML
  private TextArea longDescriptionArea;
  @FXML
  private TextArea tags;
  @FXML
  private ChoiceBox<String> tagSelection;
  @FXML
  private TableColumn ammountColumn;
  @FXML
  private TableColumn unitColumn;
  @FXML
  private TableColumn ingredientColumn;

  private String name;
  private String shortDiscription;
  private String longDescription;
  private String addedtags;
  private String[] tagsArray = { "one", "two", "three" };
  private String getAddedtags;

  // saveRecipe button
  public void saveRecipe(ActionEvent event) {
    try {
      name = nameField.getText();
      shortDiscription = shortDiscriptionField.getText();
      longDescription = longDescriptionArea.getText();
      getAddedtags = tags.getText();
      System.out.println(name + shortDiscription + longDescription + getAddedtags);
    } catch (Exception e) {
      System.out.println("mehr eingeben");
    }
  }

  // overriding the selectbox
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    tagSelection.getItems().addAll(tagsArray);
    tagSelection.setOnAction(this::setTags);
  }

  // adds text to the textArea with tags
  public void setTags(ActionEvent event) {
    addedtags = tagSelection.getValue();
    tags.setText(tags.getText() + " " + addedtags);
  }
}
