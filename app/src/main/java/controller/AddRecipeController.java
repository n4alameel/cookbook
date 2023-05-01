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
    private TextArea tags;
    @FXML
    private ChoiceBox<String> tagSelection;
    @FXML
    private TableColumn ammountColumn;
    @FXML
    private TableColumn unitColumn;
    @FXML
    private TableColumn ingredientColumn;
    @FXML
    private TextField addAmmount;
    @FXML
    private ChoiceBox<String> unitSelection;
    @FXML
    private TextField addIngredient;
    private String name;
    private String shortDiscription;
    private String longDescription;
    private String addedtags;
    //import from Database
    private String[] tagsArray = {"one", "two", "three"};
    private String getAddedtags;
    private String ammount;
    private String unit;
    private String ingredient;
    //impoer from database
    private String[] unitArray = {"drop", "gramm", "milliliter"};
    private String addUnit;



    //saveRecipe button
    public void saveRecipe(ActionEvent event) {
        try{
            name = nameField.getText();
            shortDiscription = shortDiscriptionField.getText();
            longDescription = longDescriptionArea.getText();
            getAddedtags = tags.getText();
            System.out.println(name + shortDiscription + longDescription + getAddedtags);
        }
        catch (Exception e){
            System.out.println("mehr eingeben");
        }
    }
    //overriding the selectbox for tags
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        //tagselection
        tagSelection.getItems().addAll(tagsArray);
        tagSelection.setOnAction(this::setTags);
        //Unitselection
        unitSelection.getItems().addAll(unitArray);
        unitSelection.setOnAction(this::setUnit);

    }
    //adds text to the textArea with tags
    public void setTags(ActionEvent event){
        addedtags = tagSelection.getValue();
        tags.setText(tags.getText() + " " + addedtags);
    }
    public void setUnit(ActionEvent event){
        addUnit = unitSelection.getValue();
        //need to implement the set and get for the tableView
        System.out.println(addUnit);
    }
}
