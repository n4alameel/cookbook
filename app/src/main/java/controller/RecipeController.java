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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static java.awt.SystemColor.control;

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
    @FXML
    private Text activeCommentatorName;
    private List<Tag> tags = new ArrayList<Tag>();
    private Recipe recipe;
    private ArrayList<Comment> comments = new ArrayList<Comment>();
    private String commentContext;
    private int requestedPortions;
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    @FXML
    private Spinner<Integer> portions;
    @FXML
    private VBox ingredientBox;
    @FXML
    private HBox tagBox;
    @FXML
    private Text activeUserName;
    @FXML
    private javafx.scene.control.TextArea commentTextArea;
    @FXML
    public void openFavouriteView() throws IOException {
        controller.displayFavouriteView();
    }
    @FXML
    public void openHomeView() throws IOException {
        controller.displayHomeView();
    }
    @FXML
    public void openWeeklyPlanListView() throws IOException {
        controller.displayWeeklyPlanListView();
    }
    @FXML
    public void openChatView() throws IOException {
    }
    @FXML
    public void openShoppingListView() throws IOException {
    }
    public void setRecipe(int currentRecipeId){
        Recipe updatedRecipe = this.controller.getRecipeById(currentRecipeId);
        this.recipe = updatedRecipe;
    }

    public void updatePage() {
        recipeName.setText(this.recipe.getName());
        recipeShortDescription.setText(this.recipe.getShortDescription());
        recipeDescription.setText(this.recipe.getDescription());
        activeUserName.setText(this.controller.getActiveUser().getUsername());
        this.tags = this.recipe.getTagList();
        this.tags.forEach(tag -> {
            Label labelNode = new Label();
            labelNode.setText("#"+tag.getName());
            labelNode.setStyle("-fx-opaque-insets: 0; -fx-padding: 5 10;");
            tagBox.getChildren().add(labelNode);
        });
        this.ingredients = this.recipe.getIngredientList();
        this.requestedPortions = this.recipe.getPortions();
        this.setIngredients();
        portions.setValueFactory(new  SpinnerValueFactory.IntegerSpinnerValueFactory(this.recipe.getPortions(), 100*this.recipe.getPortions(), this.recipe.getPortions(), this.recipe.getPortions()));
        portions.valueProperty().addListener((obs, oldValue, newValue) ->
        {
            System.out.println("New value: " + newValue);
            this.requestedPortions = newValue;
            this.setIngredients();
        });
        activeCommentatorName.setFont(Font.font("System", FontWeight.BOLD, 14));
        activeCommentatorName.setText(activeUserName.getText());
        commentBox.getChildren().clear();
        this.comments = this.recipe.getCommentList();
        Collections.reverse(this.comments);
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

    private void setIngredients(){
        ingredientBox.getChildren().clear();
        this.ingredients.forEach(ingredient -> {
            Text textNode = new Text(ingredient.getQuantity() * ( this.requestedPortions/this.recipe.getPortions()) + " " + ingredient.getUnitName() + " of " + ingredient.getName());
            ingredientBox.getChildren().add(textNode);
        });
    }

    public void addComment(ActionEvent event) {
        try {
            this.commentContext = commentTextArea.getText();
            System.out.println(commentContext);
            if (this.commentContext.trim() != ""){
                this.controller.postComment(this.commentContext, this.recipe.getId());
                commentTextArea.setText("");
                this.setRecipe(this.recipe.getId());
                this.updatePage();

            }
            else{
                System.out.println("Comment can not be empty");
            }
        } catch (Exception e) {
            System.out.println("mehr eingeben");
        }
    }


}

