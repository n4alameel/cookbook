package controller;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddRecipeController implements Initializable {
    Controller controller = Controller.getInstance();
    @FXML
    private TextField nameField;
    @FXML
    private TextField shortDescriptionField;
    @FXML
    private TextField longDescriptionField;
    @FXML
    private ListView<String> tagView;
    @FXML
    private ChoiceBox<String> tagSelection;
    @FXML
    private TableView<IngredientMock> ingredientTable;
    @FXML
    private TableColumn<Ingredient, Integer> amountColumn;
    @FXML
    private TableColumn<Ingredient, String> unitColumn;
    @FXML
    private TableColumn<Ingredient, String> ingredientColumn;
    @FXML
    private TextField addAmount;
    @FXML
    private ChoiceBox<String> unitSelection;
    @FXML
    private TextField addIngredient;
    @FXML
    private TextField newTag;
    @FXML
    private TextField imageField;
    @FXML
    private Label nameError;
    @FXML
    private Label shortDescriptionError;
    @FXML
    private Label longDescriptionError;
    @FXML
    private Label tagError;
    @FXML
    private Label ingredientError;
    @FXML
    private Label amountError;
    @FXML
    private Label ingredientNameError;

    private ObservableList<Tag> tagsArray = controller.generateTag();
    private ObservableList<Unit> unitArray = controller.generateUnit();
    private String addUnit;
    private ArrayList<Recipe> recipes = controller.getRecipeList();
    private ObservableList<Ingredient> ingredients = controller.generateIngredient();
    //listener for changes in the name
    ChangeListener<String> changeListener = (observable, oldValue, newValue) -> {
        for (Recipe recipe : recipes) {
            if (newValue.equalsIgnoreCase(recipe.getName())) {
                nameError.setText("The name exists already!");
                break;
            } else {
                nameError.setText("");
            }
        }
    };

    //saveRecipe button
    //TODO: minimum requirement for recipe?
    //TODO: delete all event listeners on closing the window
    public void saveRecipe(ActionEvent event) {
        ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList();
        ObservableList<Integer> selectBoxTagInts = FXCollections.observableArrayList();
        //5 is the empty string
         int unit_id = 5;
        try {
            if(!(nameError.getText().isEmpty())){
                alert("Name exists already, please change it!");
                return;
            }
            if((nameField.getText().isEmpty())){
                alert("Please select a name!");
                return;
            }

            String name = nameField.getText();
            for(Recipe recipe : recipes) {
                if (recipe.getName().contains(name)) {
                    nameError.setText("Name exists already, please change it!");
                    alert("Name exists already, please change it!");
                    return;
                }
            }
                String shortDescription = shortDescriptionField.getText();
            String longDescription = longDescriptionField.getText();
            String imageURL = imageField.getText();
            //if there are no ingredients stop.
            if (ingredientTable.getItems().isEmpty()) {
                ingredientError.setText("Please add at least on ingredient to the Table!");
                return;
            }
            ObservableList<IngredientMock> ingredientMock = ingredientTable.getItems();
            for (IngredientMock ingredientMock1 : ingredientMock) {
                String ingredientMock1Name = ingredientMock1.getName();
                int quantity = (ingredientMock1.getQuantity());
                for (Unit unit : unitArray) {
                    if (unit.getName().equals(ingredientMock1.getUnit_id())) {
                        unit_id = unit.getId();
                        break;
                    }
                }
                ingredientList.add(new Ingredient(ingredientMock1Name, quantity, unit_id));
            }

            for (String tagName : tagView.getItems()) {
                for (Tag tag : tagsArray) {
                    if (tagName == (tag.getName())) {
                        selectBoxTagInts.add(tag.getId());
                        break;
                    }
                }
            }
                //selectBoxTagInts for loop for reading the elements out of the List
                controller.newIngredient(ingredientList);
                controller.newRecipe(name, longDescription, shortDescription, selectBoxTagInts, ingredientList, imageURL);
                alert("Recipe has been saved");
                recipes.add(new Recipe(name));
                Controller.getInstance().getStage().close();

        } catch (Exception e) {
            System.out.println(e);
            alert("there is already a recipe with the same Name and first ingredients");
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //tagselection get the tags out of the List and put them into the choicebox

        for (Tag tag : tagsArray) {
            if (tag.getUser_id() == -1 || tag.getUser_id() == controller.getActiveUser().getId())
                tagSelection.getItems().addAll(tag.getName());
        }
        tagSelection.setOnAction(this::setTags);

        //unitselection, get the units out of the List and put them into the choicebox
        for (Unit unit : unitArray) {
            unitSelection.getItems().addAll(unit.getName());
        }
        unitSelection.setOnAction(this::setUnit);

        // TableView
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit_id"));
        ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        nameField.textProperty().addListener(changeListener);
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
            tagError.setText("");
        }
        if (!unique) {
            tagError.setText("Tag has already been selected");
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
        String unit = addUnit;
        String ingredientItem = addIngredient.getText();
        Integer amount;
        try {
            amount = Integer.valueOf(addAmount.getText());
        } catch (Exception e) {
            amountError.setText("Please set the amount of the ingredient!");
            return;
        }
        if(ingredientItem.isBlank()) {
            ingredientNameError.setText("Please set the name of the ingredient!");
            return;
        }
        IngredientMock ingredientMock = new IngredientMock(ingredientItem, amount, unit);
        for (IngredientMock ingredientMock1 : ingredientTable.getItems()) {
            if (ingredientMock1.getName().equals(ingredientMock.getName())) {
                unique = false;
                break;
            }
        }
        if (unique) {
            ingredientTable.getItems().add(ingredientMock);
        }
        if (!unique) {
            ingredientError.setText("Ingredient exists already, please choose a new one!");
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
                    unique = false;
                    break;
                }
            }
            if (unique && viewUnique) {
                controller.newTag(addTag);
                tagView.getItems().add(addTag);
                tagError.setText("");
            }
            if (!viewUnique) {
                //alert(addTag, "Tag");
                tagError.setText("Tag has already been selected");
            }
            tagSelection.getItems().add(addTag);
        }
    }

    private void alert(String information) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(information);
        alert.show();
    }
}
