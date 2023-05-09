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
import javafx.scene.input.MouseEvent;
import model.Ingredient;
import model.IngredientMock;
import model.Tag;
import model.Unit;

import javax.swing.*;
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
    private TableColumn<Ingredient, String> unitColumn;
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
    private Integer tagID;
    private int unit_id;
    ObservableList<IngredientMock> ingredientMocks = FXCollections.observableArrayList();

    //saveRecipe button
    public void saveRecipe(ActionEvent event) {
        try {
            ObservableList<IngredientMock> ingredientMock = ingredientTable.getItems();
            for (IngredientMock ingredientMock1 : ingredientMock) {
                String name = ingredientMock1.getName();
                int quantity = Integer.parseInt(ingredientMock1.getQuantity());
                for (Unit unit : unitArray) {
                    if (unit.getName().equals(ingredientMock1.getUnit_id())) {
                        unit_id = unit.getId();
                    }
                }
                ingredientList.add(new Ingredient(name, quantity, unit_id));
            }
            name = nameField.getText();
            shortDiscription = shortDiscriptionField.getText();
            longDescription = longDescriptionArea.getText();
            controller.newIngredient(ingredientList);
            controller.newRecipe(name, longDescription, shortDiscription, selectBoxUnitInts, selectBoxTagInts, ingredientList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //overriding the selectbox on class initialization
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
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
     *
     * @param
     */
    public void setTags(ActionEvent event) {
        String addedTag = tagSelection.getValue();
        addedtags = tagSelection.getValue();
        tags.setText(tags.getText() + " " + addedtags);
        for (Tag tag : tagsArray) {
            if (tag.getName().equals(addedTag)) {
                tagID = tag.getId();
                //System.out.println(tagID);
            }
        }
        selectBoxTagInts.add(tagID);
        //System.out.println(selectBoxTagInts);
    }

    /**
     * is called when the dropbox for setting the Unit is called
     *
     * @param event
     */
    public void setUnit(ActionEvent event) {
        addUnit = unitSelection.getValue();
        for (Unit unit : unitArray) {
            if (unit.getName().equals(addUnit)) {
                unitID = unit.getId();
                //System.out.println(unitID);
            }
        }
        //System.out.println(selectBoxUnitInts);

    }

    /**
     * is called when the AddIngredientsButton is used
     *
     * @param event
     */
    //TODO: error handling
    public void addIngredientButton(ActionEvent event) {
        ammount = addAmmount.getText();
        unit = addUnit;
        ingredientItem = addIngredient.getText();
        selectBoxUnitInts.add(unitID);
        IngredientMock ingredientMock = new IngredientMock(ingredientItem, ammount, unit);
        //Ingredient ingredient = new Ingredient(ingredientItem, Integer.parseInt(ammount), unitID);
        System.out.println("ammount " + ammount + "unit" + unit + "unitID" + unitID + " tagID" + tagID);
        //ingredientList.add(ingredient);
        ingredientTable.getItems().add(ingredientMock);
    }

    /**
     * double click to delete a row
     * @param mouseEvent
     */
    public void deleteClickedRow(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2 && !ingredientTable.getSelectionModel().isEmpty()){
            ingredientTable.getItems().remove(ingredientTable.getSelectionModel().getSelectedItem());
        }
    }

}
