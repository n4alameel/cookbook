package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Comment;
import model.Ingredient;
import model.Recipe;
import model.Tag;
import model.WeeklyList;
import model.WeeklyList.WeekDay;
import view.RecipeView;

import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeController {
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

  @FXML
  private ImageView favBtn;

  @FXML
  private void toggleFavourite() {
    if (this.controller.toggleFavourite(this.recipe)) {
      favBtn.setImage(new Image("img/HeartFull.png"));
      System.out.println("favourited");
    } else {
      favBtn.setImage(new Image("img/Heart.png"));
      System.out.println("UNfavourited");
    }
  }

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
  private javafx.scene.control.TextArea commentTextArea;
  @FXML
  private Pane commentatorAva;
  @FXML
  private ComboBox<String> weekSelector;
  @FXML
  private ComboBox<WeekDay> daySelector;
  @FXML
  private Label addMessage;
  @FXML
  private void sendRecipeEvent() {
    controller.displaySendMessageView(this.recipe);
  }

  public void setRecipe(int currentRecipeId) {
    Recipe updatedRecipe = this.controller.getRecipeById(currentRecipeId);
    this.recipe = updatedRecipe;
  }

  public void updatePage() {
    recipeName.setText(this.recipe.getName());
    if (this.controller.getActiveUser().isFavourite(this.recipe)) {
      favBtn.setImage(new Image("img/HeartFull.png"));
    }
    recipeShortDescription.setText(this.recipe.getShortDescription());
    recipeDescription.setText(this.recipe.getDescription());
    this.tags = this.recipe.getTagList();
    this.tags.forEach(tag -> {
      Label labelNode = new Label();
      labelNode.setText("#" + tag.getName());
      labelNode.setStyle("-fx-opaque-insets: 0; -fx-padding: 5 10;");
      tagBox.getChildren().add(labelNode);
    });
    this.ingredients = this.recipe.getIngredientList();
    this.requestedPortions = 2;
    this.setIngredients();
    portions.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2,
        100 * 2, 2, 2));
    portions.valueProperty().addListener((obs, oldValue, newValue) -> {
      System.out.println("New value: " + newValue);
      this.requestedPortions = newValue;
      this.setIngredients();
    });
    activeCommentatorName.setFont(Font.font("System", FontWeight.BOLD, 14));
    activeCommentatorName.setText(this.controller.getActiveUser().getUsername());
    Image imageObject = new Image(this.controller.getActiveUser().getImageURL());
    Circle circle = new Circle(25, 25, 25);
    // Create the ImageView
    ImageView img = new ImageView(imageObject);
    img.setFitWidth(60);
    img.setClip(circle);
    img.setFitHeight(50);
    commentatorAva.getChildren().add(img);
    commentBox.getChildren().clear();
    this.comments = this.recipe.getCommentList();
    Collections.reverse(this.comments);
    this.comments.forEach(comment -> {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Comment.fxml"));
        Pane root = loader.load();
        CommentController commentController = loader.getController();
        commentController.setComments(comment);
        commentController.updateComments();
        commentBox.getChildren().add(root);
      } catch (IOException e) {
      }
    });

    ArrayList<WeeklyList> weeklies = controller.getActiveUser().getWeeklyPlanList();
    ArrayList<String> weeks = new ArrayList<String>();
    for (WeeklyList w : weeklies) {
      weeks.add(Integer.toString(w.getYear()) + " w" + Integer.toString(w.getWeekNumber()));
    }
    weekSelector.setItems(FXCollections.observableArrayList(weeks));
    daySelector.setItems(FXCollections.observableArrayList(WeekDay.values()));
  }

  private void setIngredients() {
    ingredientBox.getChildren().clear();
    this.ingredients.forEach(ingredient -> {
      Text textNode = new Text(ingredient.getQuantity() * (this.requestedPortions / 2) + " " + ingredient.getUnitName()
          + " of " + ingredient.getName());
      ingredientBox.getChildren().add(textNode);
    });
  }

  public void addComment(ActionEvent event) {
    try {
      this.commentContext = commentTextArea.getText();
      System.out.println(commentContext);
      if (this.commentContext.trim() != "") {
        this.controller.postComment(this.commentContext, this.recipe.getId());
        commentTextArea.setText("");
        this.setRecipe(this.recipe.getId());
        this.updatePage();
      } else {
        System.out.println("Comment can not be empty");
      }
    } catch (Exception e) {
      System.out.println("mehr eingeben");
    }
  }

  @FXML
  private void eventAddRecipeToPlan() throws IOException {
    ArrayList<WeeklyList> weeklies = controller.getActiveUser().getWeeklyPlanList();
    String[] week = weekSelector.getValue().split(" w");
    int weekId = 0;
    for (WeeklyList w : weeklies) {
      if (w.getYear() == Integer.parseInt(week[0]) && w.getWeekNumber() == Integer.parseInt(week[1])) {
        weekId = w.getId();
        break;
      }
    }
    controller.addRecipeToWeeklyList(weekId, daySelector.getValue(), this.recipe);
    addMessage.setVisible(true);
  }

}
