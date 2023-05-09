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
    private ListView<String> tagView;
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
    private ObservableList<Tag> tagsArray = controller.generateTag();
    private ObservableList<Integer> selectBoxTagInts = FXCollections.observableArrayList();

    private String ammount;
    private String unit;
    private String ingredientItem;
    private ObservableList<Unit> unitArray = controller.generateUnit();
    private ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList();
    private String addUnit;
    private Integer unitID;
    private Integer tagID;
    private int unit_id;

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

            for (String tagName : tagView.getItems()) {
                for (Tag tag : tagsArray) {
                    if (tagName == (tag.getName())) {
                        selectBoxTagInts.add(tag.getId());
                    }
                }
            }
            for(int b : selectBoxTagInts){
                System.out.println("Inside: " + b);
            }
            //selectBoxTagInts for loop for reading the elements out of the List
            String name = nameField.getText();
            String shortDiscription = shortDiscriptionField.getText();
            String longDescription = longDescriptionArea.getText();
            controller.newIngredient(ingredientList);
            System.out.println("before");
            controller.newRecipe(name, longDescription, shortDiscription, selectBoxTagInts, ingredientList);
            System.out.println("here");
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
        tagView.getItems().add(addedTag);
        for (Tag tag : tagsArray) {
            if (tag.getName().equals(addedTag)) {
                tagID = tag.getId();
            }
        }
        //selectBoxTagInts.add(tagID);
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
            }
        }
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
        IngredientMock ingredientMock = new IngredientMock(ingredientItem, ammount, unit);
        //System.out.println("ammount " + ammount + "unit" + unit + "unitID" + unitID + " tagID" + tagID);
        ingredientTable.getItems().add(ingredientMock);
    }

    /**
     * double click to delete a row
     * @param mouseEvent
     */
    public void deleteClickedRowIngredients(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2 && !ingredientTable.getSelectionModel().isEmpty()){
            ingredientTable.getItems().remove(ingredientTable.getSelectionModel().getSelectedItem());
        }
    }
    public void deleteClickedRowTags(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && !tagView.getSelectionModel().isEmpty()) {
            tagView.getItems().remove(tagView.getSelectionModel().getSelectedItem());
        }
    }
    //TODO: when adding a Tag that is not in the dropbox via a Textline it has to be added directly to the Database when, cause when saving the recipe it checks the database for the according ints to the name of the tags
}
