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
    private Integer unitID;
    //saveRecipe button
    public void saveRecipe(ActionEvent event) {
        try{
            name = nameField.getText();
            shortDiscription = shortDiscriptionField.getText();
            longDescription = longDescriptionArea.getText();
            controller.newIngredient(ingredientList);
            controller.newRecipe(name, longDescription, shortDiscription, selectBoxUnitInts, selectBoxTagInts);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    //overriding the selectbox on class initialization
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        //tagselection get the tags out of the List and put them into the choicebox
        for (Tag tag : tagsArray) {
            tagSelection.getItems().addAll(tag.getName());
        }
        tagSelection.setOnAction(this::setTags);

        //Unitselection, get the units out of the List and put them into the choicebox
        for (Unit unit : unitArray) {
            unitSelection.getItems().addAll(unit.getName());
        }
        unitSelection.setOnAction(this::setUnit);

        // TableView
        ammountColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit_id"));
        ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    /**
     * is called when the dropbox for setting Tags is used
     * @param
     */
    public void setTags(ActionEvent event){
        Integer i = 0;
        String addedTag = tagSelection.getValue();
        addedtags = tagSelection.getValue();
        tags.setText(tags.getText() + " " + addedtags);
        for(Tag tag : tagsArray) {
            if (tag.getName().equals(addedTag)) {
               i = tag.getId();
                System.out.println(i);
            }
        }
        selectBoxTagInts.add(i);
        System.out.println(selectBoxTagInts);
        }

    /**
     * is called when the dropbox for setting the Unit is called
     * @param event
     */
    public void setUnit(ActionEvent event){
        addUnit = unitSelection.getValue();
        for(Unit unit : unitArray) {
            if (unit.getName().equals(addUnit)) {
                unitID = unit.getId();
                System.out.println(unitID);
            }
        }
        selectBoxUnitInts.add(unitID);
        System.out.println(selectBoxUnitInts);
    }

    /**
     * is called when the AddIngredientsButton is used
     * @param event
     */
    public void addIngredientButton(ActionEvent event) {
        ammount = addAmmount.getText();
        unit = addUnit;
        ingredientItem = addIngredient.getText();
        IngredientMock ingredientMock = new IngredientMock(ingredientItem, ammount, unit);
        Ingredient ingredient = new Ingredient(ingredientItem, Integer.parseInt(ammount), unitID);
        ingredientList.add(ingredient);
        ingredientTable.getItems().add(ingredientMock);
    }
}
