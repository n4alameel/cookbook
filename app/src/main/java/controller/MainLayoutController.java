package controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Query;
import model.Recipe;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.InputSource;

public class MainLayoutController {

  Controller controller = Controller.getInstance();
  @FXML
  private Button activeUserName;
  @FXML
  private ImageView activeUserPhoto;
  @FXML
  private Button adminPanel;
  @FXML
  private TextField searchField;

  @FXML
  private ComboBox<String> categoryComboBox;

  public MainLayoutController() {
  }

  public void updateLayout() {
    activeUserName.setText(this.controller.getActiveUser().getUsername());
    Image imageObject = new Image(this.controller.getActiveUser().getImageURL());
    activeUserPhoto.setImage(imageObject);
    if (this.controller.getActiveUser().isAdmin()) {
      adminPanel.setVisible(true);
    }
    // Calculate the aspect ratio of the image
    // double imageAspectRatio = activeUserPhoto.getImage().getWidth() /
    // activeUserPhoto.getImage().getHeight();
    //
    // // Calculate the aspect ratio of the target view
    // double targetAspectRatio = imageObject.getWidth() / imageObject.getHeight();
    //
    // // Calculate the viewport dimensions
    // double viewportWidth;
    // double viewportHeight;
    //
    // if (imageAspectRatio > targetAspectRatio) {
    // // Image is wider than the target view
    // viewportWidth = activeUserPhoto.getImage().getHeight() * targetAspectRatio;
    // viewportHeight = activeUserPhoto.getImage().getHeight();
    // } else {
    // // Image is taller or equal to the target view
    // viewportWidth = activeUserPhoto.getImage().getWidth();
    // viewportHeight = activeUserPhoto.getImage().getWidth() / targetAspectRatio;
    // }
    // Circle clip = new Circle();
    // // Calculate the viewport position to center the image
    // double viewportX = (activeUserPhoto.getImage().getWidth() - viewportWidth) /
    // 2;
    // double viewportY = (activeUserPhoto.getImage().getHeight() - viewportHeight)
    // / 2;
    // activeUserPhoto.setViewport(new javafx.geometry.Rectangle2D(viewportX,
    // viewportY, viewportWidth, viewportHeight));
    //
    // clip.radiusProperty().bind(new
    // SimpleDoubleProperty(Math.min((activeUserPhoto.getFitWidth()/2.0),
    // (activeUserPhoto.getFitHeight()/2.0))));
    // clip.centerXProperty().bind(activeUserPhoto.fitWidthProperty().divide(2));
    // clip.centerYProperty().bind(activeUserPhoto.fitHeightProperty().divide(2));
    //
    // activeUserPhoto.setClip(clip);
  }

  @FXML
  private BorderPane rootLayout;

  public BorderPane getRootLayout() {
    return rootLayout;
  }

  public void setRootLayout(BorderPane rootLayout) {
    this.rootLayout = rootLayout;
  }

  public ScrollPane getContent() {
    return content;
  }

  public void setContent(ScrollPane content) {
    this.content = content;
  }

  @FXML
  private ScrollPane content;

  public void openAllRecipesView(ActionEvent actionEvent) throws IOException {
    this.controller.displayBrowserView();
  }

  public void openWeeklyPlanListView(ActionEvent actionEvent) throws IOException {
    this.controller.displayWeeklyPlanListView();
  }

  public void openShoppingListView(ActionEvent actionEvent) {

  }

  public void openChatView(ActionEvent actionEvent) throws IOException {
    this.controller.displayMessageView();
  }

  public void openHomeView(MouseEvent mouseEvent) throws IOException {
    this.controller.displayHomeView();
  }

  public void openFavouriteView(ActionEvent actionEvent) throws IOException {
    this.controller.displayFavouriteView();
  }

  public void openNewRecipeView(ActionEvent actionEvent) throws IOException {
    this.controller.displayNewRecipeView();
  }

  public void goToUsersView() throws IOException {
    this.controller.displayUsersView();
  }

  public void showHelpEvent() throws IOException {
    this.controller.displayHelpPage();
  }

  public void logoutEvent() {
    controller.displayLoginScene();
  }

  public void loadPage2(ActionEvent actionEvent) {

  }

  public void openHomeView(ActionEvent actionEvent) {

  }

  public void openSearchPage() throws IOException {

    Query search = new Query();
    search.setQuery(searchField.getText());
    String selectedOption = (String) categoryComboBox.getValue();
    this.controller.displaySearchView(search, selectedOption);
  }

  public void onSearchEnter(KeyEvent keyEvent) throws IOException {
    if (keyEvent.getCode() == KeyCode.ENTER) {
      openSearchPage();
    }
  }

}
