package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class MainLayoutController {



    @FXML
    private Button activeUserName;
    public MainLayoutController() {
    }

    public void updateLayout(){
        this.activeUserName.setText(this.controller.getActiveUser().getUsername());
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

    @FXML private ScrollPane content;

    Controller controller = Controller.getInstance();
    public void openAllRecipesView(ActionEvent actionEvent) throws IOException {
        this.controller.displayBrowserView();
    }

    public void openWeeklyPlanListView(ActionEvent actionEvent) {
        this.controller.displayWeeklyPlanListView();
    }

    public void openShoppingListView(ActionEvent actionEvent) {

    }

    public void openChatView(ActionEvent actionEvent) {
    }

    public void openHomeView(MouseEvent mouseEvent) throws IOException {
        this.controller.displayHomeView();
    }

    public void openFavouriteView(ActionEvent actionEvent) {
        this.controller.displayFavouriteView();
    }

    public void openNewRecipeView(ActionEvent actionEvent) {
        this.controller.displayNewRecipeView();
    }

    public void loadPage2(ActionEvent actionEvent) {

    }

    public void openHomeView(ActionEvent actionEvent) {

    }
}
