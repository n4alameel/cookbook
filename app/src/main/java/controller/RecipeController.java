package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Recipe;
import model.Tag;
import view.RecipeView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeController implements Initializable{
    @FXML
    private Text recipeName;
    @FXML
    private Text recipeDetail;
    @FXML
    private Text recipeDescription;
    @FXML
    private List<String> recipeIngredients;
    @FXML
    private List<String> tags = new ArrayList<>();

    @FXML
    private Spinner<Integer> portions;
    @FXML
    private VBox ingredientBox;

    @FXML
    private HBox tagBox;
    //TODO  Receive Recipe trough the ResourceBundle
    //      Create an action that get the recipe by id
    //      put the object to ResourceBundle

    private void mapRecipe2View(Recipe recipe) {
        recipeName.setText(recipe.getName());
        recipeDetail.setText(recipe.getDetail());
        recipeDescription.setText(recipe.getDescription());
        recipe.getIngredientList()
                .forEach(ingredient -> {
                    Text textNode = new Text(ingredient.getName());
                    ingredientBox.getChildren().add(textNode);
                });

        recipe.getTagList()
                .forEach(tag -> {
                    Text textNode = new Text(tag.getName());
                    tagBox.getChildren().add(textNode);
                });
        portions.setValueFactory(new  SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, recipe.getPortions()));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Recipe recipe = (Recipe) resources.getObject("recipe");
        mapRecipe2View(recipe);
    }

//    public void setTags(ActionEvent event) {
//        addedtags = tagSelection.getValue();
//        tags.setText(tags.getText() + " " + addedtags);
//    }
}
