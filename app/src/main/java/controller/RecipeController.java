package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Comment;
import model.Ingredient;
import model.Recipe;
import model.Tag;
import view.RecipeView;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeController{
    private Controller controller = Controller.getInstance();
    @FXML
    private Text recipeName;
    @FXML
    private Text recipeShortDescription;
    @FXML
    private Text recipeDescription;
    @FXML
    private VBox commentBox;
    private List<Tag> tags = new ArrayList<Tag>();
    private ArrayList<Comment> comments = new ArrayList<Comment>();
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    @FXML
    private Spinner<Integer> portions;
    @FXML
    private VBox ingredientBox;
    @FXML
    private HBox tagBox;
    //TODO  Receive Recipe trough the ResourceBundle
    //      Create an action that get the recipe by id
    //      put the object to ResourceBundle

    public void updatePage(Recipe recipe) {
        recipeName.setText(recipe.getName());
        recipeShortDescription.setText(recipe.getShortDescription());
        recipeDescription.setText(recipe.getDescription());
        this.ingredients = recipe.getIngredientList();
        this.ingredients.forEach(ingredient -> {
                    Text textNode = new Text(ingredient.getName());
                    ingredientBox.getChildren().add(textNode);
                });
        this.tags = recipe.getTagList();
        this.tags.forEach(tag -> {
                    Label labelNode = new Label(tag.getName());
                    labelNode.setStyle("-fx-opaque-insets: 0; -fx-padding: 5 10;");
                    tagBox.getChildren().add(labelNode);
                });
        portions.setValueFactory(new  SpinnerValueFactory.IntegerSpinnerValueFactory(recipe.getPortions(), 100*recipe.getPortions(), recipe.getPortions(), recipe.getPortions()));
        this.comments = recipe.getCommentList();
        this.comments.forEach(comment -> {
            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Comment.fxml"));
            Pane root = loader.load();
            CommentController commentController = loader.getController();
            commentController.setComments(comment);
            commentController.updateComments();
            commentBox.getChildren().add(root);
            }
            catch(IOException e){}
        });
    }


//    public void setTags(ActionEvent event) {
//        addedtags = tagSelection.getValue();
//        tags.setText(tags.getText() + " " + addedtags);
//    }
}
