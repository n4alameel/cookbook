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

import java.net.IDN;
import java.net.URL;
import java.util.ArrayList;
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
    private ObservableList<Integer> selectBoxTagInts = FXCollections.observableArrayList();

    private String getAddedtags;
    private String ammount;
    //should be int
    private String unit;
    //should be unit_id
    private String ingredientItem;
    //impoer from database
    private ObservableList<Unit> unitArray = controller.generateUnit();
    private ObservableList<String> selectBoxUnits = FXCollections.observableArrayList();
    private ObservableList<Integer> selectBoxUnitInts = FXCollections.observableArrayList();
    private ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList();

    private String addUnit;
    private Integer i = 0;

    //saveRecipe button
    public void saveRecipe(ActionEvent event) {
        try{
            name = nameField.getText();
            shortDiscription = shortDiscriptionField.getText();
            longDescription = longDescriptionArea.getText();
            //getAddedtags = tags.getText();
            //System.out.println(name + shortDiscription + longDescription + getAddedtags);
            //TODO: implement ingredientList
            controller.newIngredient(ingredientList);
            controller.newRecipe(name, longDescription, shortDiscription, selectBoxUnitInts, selectBoxTagInts);
        }
        catch (Exception e){
            System.out.println(e);
        }
        //mocking for printing the ingredients to read them
    }
    //overriding the selectbox for tags
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        //tagselection
        //tagSelection.getItems().addAll(tagsArray);
        for (Tag tag : tagsArray) {
            tagSelection.getItems().addAll(tag.getName());
        }
        tagSelection.setOnAction(this::setTags);
        //TODO: another list where the Integer for the combination of both units and tags is put in.
        //Unitselection
        for (Unit unit : unitArray) {
            unitSelection.getItems().addAll(unit.getName());

        }
        unitSelection.setOnAction(this::setUnit);
        //TableView
        ammountColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit_id"));
        ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
    //adds text to the textArea with tags
    public void setTags(ActionEvent event){
        String addedTag = tagSelection.getValue();
        addedtags = tagSelection.getValue();
        tags.setText(tags.getText() + " " + addedtags);
        //increase the number by one because AutoIncrement in SQL starts at
        for(Tag tag : tagsArray) {
            if (tag.getName().equals(addedTag)) {
                i = tag.getId();
                System.out.println(i);
            }
        }
        selectBoxTagInts.add(tagsArray.get(i).getId());
        System.out.println(selectBoxTagInts);
        }

    public void setUnit(ActionEvent event){
        addUnit = unitSelection.getValue();
        //increase the number by one because AutoIncrement in SQL starts at 1
        selectBoxUnitInts.add(unitSelection.getSelectionModel().getSelectedIndex() + 1);
        i = unitSelection.getSelectionModel().getSelectedIndex() + 1;
        System.out.println(selectBoxUnitInts);
    }
    public void addIngredientButton(ActionEvent event) {
        ammount = addAmmount.getText();
        unit = addUnit;
        ingredientItem = addIngredient.getText();
        IngredientMock ingredientMock = new IngredientMock(1, ingredientItem, ammount, unit);
        Ingredient ingredient = new Ingredient(ingredientItem, Integer.parseInt(ammount), selectBoxUnitInts.get(i));
        ingredientList.add(ingredient);
        ingredientTable.getItems().add(ingredientMock);

    }
}
