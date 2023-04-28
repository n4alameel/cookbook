package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRecipeController implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private TextField shortDiscriptionField;
    @FXML
    private TextArea longDescriptionArea;
    @FXML
    private TextArea tagsArea;
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
    private String tags;
    private String[] tagsArray = {"one", "two", "three"};

    public void saveRecipe(ActionEvent event) {

    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        tagSelection.getItems().addAll(tagsArray);
        tagSelection.setOnAction(this::setTags);
    }
    public void setTags(ActionEvent event){
        tags = tagSelection.getValue();
        tagsArea.setText(tagsArea+";"+"tags");
    }
}
