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
import model.IngredientMock;
import model.Tag;
import model.Unit;

import java.net.URL;
import java.util.ResourceBundle;

public class AddRecipeController implements Initializable {
    Controller controller = new Controller();
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
    private TableView<IngredientMock> ingredientTable;
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
    private ObservableList<Tag> tagsArray = controller.generateTag();
    private ObservableList<String> selectBoxTags = FXCollections.observableArrayList();
    private String getAddedtags;
    private String ammount;
    //should be int
    private String unit;
    //should be unit_id
    private String ingredientItem;
    //impoer from database
    private ObservableList<Unit> unitArray = controller.generateUnit();
    private ObservableList<String> selectBoxUnits = FXCollections.observableArrayList();
    private String addUnit;

    //saveRecipe button
    public void saveRecipe(ActionEvent event) {
        try{
            name = nameField.getText();
            shortDiscription = shortDiscriptionField.getText();
            longDescription = longDescriptionArea.getText();
            getAddedtags = tags.getText();
            System.out.println(name + shortDiscription + longDescription + getAddedtags);
            //TODO: implement ingredientList
            //controller.newRecipe(name, longDescription, shortDiscription, ingredientList);
        }
        catch (Exception e){
            System.out.println(tagsArray);
        }
        //mocking for printing the ingredients to read them
    }
    //overriding the selectbox for tags
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        //tagselection
        //tagSelection.getItems().addAll(tagsArray);
        for (Tag tag : tagsArray) {
            String name = tag.getName();
            selectBoxTags.add(name);
        }
        tagSelection.getItems().addAll(selectBoxTags);
        tagSelection.setOnAction(this::setTags);
        //TODO: another list where the Integer for the combination of both units and tags is put in.
        //Unitselection
        for (Unit unit : unitArray) {
            String name = unit.getName();
            selectBoxUnits.add(name);
        }
        unitSelection.getItems().addAll(selectBoxUnits);
        unitSelection.setOnAction(this::setUnit);
        //TableView
        ammountColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit_id"));
        ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    }
    //adds text to the textArea with tags
    public void setTags(ActionEvent event){
        addedtags = tagSelection.getValue();
        tags.setText(tags.getText() + " " + addedtags);
    }
    public void setUnit(ActionEvent event){
        addUnit = unitSelection.getValue();
        //need to implement the set and get for the tableView
        //
        }

    public void addIngredientButton(ActionEvent event) {
        ammount = addAmmount.getText();
        unit = addUnit;
        ingredientItem = addIngredient.getText();
        IngredientMock ingredientMock = new IngredientMock(1, ingredientItem, ammount, unit);
        ingredientTable.getItems().add(ingredientMock);
    }
}
