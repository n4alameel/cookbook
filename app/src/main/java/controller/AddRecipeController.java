package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Ingredient;

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
    private TableView<Ingredient> ingredientTable;
    @FXML
    private TableColumn<Ingredient, Integer> ammountColumn;
    @FXML
    private TableColumn<Ingredient, Integer> unitColumn;
    @FXML
    private TableColumn<Ingredient, String> ingredientColumn;
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
    //should be int
    private String unit;
    private String ingredientItem;
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
        //TableView
        ammountColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit_id"));
        ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        //ingredientTable.setItems(observableList);

    }
    /**
    ObservableList<Ingredient> observableList = FXCollections.observableArrayList(
            new Ingredient(1, "potato", 2, 3)
    );
     */
    //adds text to the textArea with tags
    public void setTags(ActionEvent event){
        addedtags = tagSelection.getValue();
        tags.setText(tags.getText() + " " + addedtags);
    }
    public String setUnit(ActionEvent event){
        addUnit = unitSelection.getValue();
        //need to implement the set and get for the tableView
        System.out.println(addUnit);
        return addUnit;
    }

    public void addIngredientButton(ActionEvent event) {
        ammount = addAmmount.getText();
        unit = addUnit;
        ingredientItem = addIngredient.getText();
        Ingredient ingredient = new Ingredient(1, ingredientItem, ammount, unit);
        ingredientTable.getItems().add(ingredient);
    }
}
