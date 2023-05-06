package view;


import controller.FavouriteRecipeController;
import javafx.fxml.FXMLLoader;
import controller.Controller;
import javafx.scene.Parent;
import java.io.IOException;


public class FavouriteView {

    private Parent root;

    public FavouriteView() {
    try {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/FavouriteWindow.fxml"));
       root = (Parent) loader.load();
         FavouriteRecipeController favouriteRecipeController = loader.getController();
        favouriteRecipeController.initPagination();
    }
    catch (IOException e) {
        e.printStackTrace(System.out);
        throw new RuntimeException(e);
    }

    }
    public Parent getRoot() {
        return this.root;
    }
}
