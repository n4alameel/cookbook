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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Ingredient;
import model.IngredientMock;
import model.Tag;
import model.Unit;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddRecipeController implements Initializable {
    Controller controller = new Controller();
    @FXML
    private TextField nameField;
    @FXML
    private TextField shortDiscriptionField;
    @FXML
    private TextField longDescriptionArea;
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
    @FXML
    private TextField newTag;
    private ObservableList<Tag> tagsArray = controller.generateTag();
    private ObservableList<Integer> selectBoxTagInts = FXCollections.observableArrayList();
    private Integer ammount;
    private String unit;
    private String ingredientItem;
    private ObservableList<Unit> unitArray = controller.generateUnit();
    private ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList();
    private String addUnit;
    private int unit_id;

    //saveRecipe button
    public void saveRecipe(ActionEvent event) {
        try {
            ObservableList<IngredientMock> ingredientMock = ingredientTable.getItems();
            //adds Ingredients coresponding to the unit_id that exists in the database, TODO: if the tableview has the same element twice i need to filter it.
            for (IngredientMock ingredientMock1 : ingredientMock) {
                String name = ingredientMock1.getName();
                int quantity = (ingredientMock1.getQuantity());
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
            //selectBoxTagInts for loop for reading the elements out of the List
            String name = nameField.getText();
            String shortDiscription = shortDiscriptionField.getText();
            String longDescription = longDescriptionArea.getText();
            controller.newIngredient(ingredientList);
            controller.newRecipe(name, longDescription, shortDiscription, selectBoxTagInts, ingredientList);
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
        boolean unique = true;
        String addedTag = tagSelection.getValue();
        for (String tag : tagView.getItems()) {
            if (tag.equals(addedTag)) {
                unique = false;
                break;
            }
        }
        if (unique) {
            tagView.getItems().add(addedTag);
        }
    }

    /**
     * is called when the dropbox for setting the Unit is called
     *
     * @param event
     */
    public void setUnit(ActionEvent event) {
        addUnit = unitSelection.getValue();
    }

    /**
     * is called when the AddIngredientsButton is used
     *
     * @param event
     */
    //TODO: error handling
    public void addIngredientButton(ActionEvent event) {
        boolean unique = true;
        try {
            ammount = Integer.valueOf(addAmmount.getText());
        } catch (Exception e) {
            System.out.println("Please select a Number as amount.");
            System.out.println(e);
        }
        unit = addUnit;
        ingredientItem = addIngredient.getText();
        IngredientMock ingredientMock = new IngredientMock(ingredientItem, ammount, unit);
        for (IngredientMock ingredientMock1 : ingredientTable.getItems()) {
            if (ingredientMock1.getName().equals(ingredientMock.getName())) {
                System.out.println("exists already please change give in another ingredient");
                unique = false;
                break;
            }
        }
        if (unique) {
            ingredientTable.getItems().add(ingredientMock);
            System.out.println("added: " + ingredientMock.getName());
        }
    }

    /**
     * double click to delete a row
     *
     * @param mouseEvent
     */
    public void deleteClickedRowIngredients(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && !ingredientTable.getSelectionModel().isEmpty()) {
            ingredientTable.getItems().remove(ingredientTable.getSelectionModel().getSelectedItem());
        }
    }

    public void deleteClickedRowTags(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && !tagView.getSelectionModel().isEmpty()) {
            tagView.getItems().remove(tagView.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * generates a new Tag in the Database on enter press, then generates a new Taglist to check if the next entry is already part of the database
     *
     * @param keyEvent
     * @throws SQLException
     */
    public void enterPressed(KeyEvent keyEvent) throws SQLException {
        boolean unique = true;
        boolean viewUnique = true;
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String addTag = newTag.getText();
            for (String name : tagView.getItems()) {
                if (name.equals(addTag)) {
                    viewUnique = false;
                    unique = false;
                    break;
                }
            }
            for (Tag tag : tagsArray) {
                if (tag.getName().equals(addTag) && viewUnique) {
                    tagView.getItems().add(addTag);
                    System.out.println("tag exists already");
                    unique = false;
                    break;
                }
            }
            if (unique && viewUnique) {
                controller.newTag(addTag);
                tagView.getItems().add(addTag);
            }
        }
        tagsArray = controller.generateTag();
    }
}
