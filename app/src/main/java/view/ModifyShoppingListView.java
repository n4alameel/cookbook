package view;

import controller.ModifyShoppingListViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.ShoppingList;

import java.io.IOException;

public class ModifyShoppingListView {
    private Parent root;

    public ModifyShoppingListView(ShoppingList shoppingList) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifyShoppingListWindow.fxml"));
            root = (Parent) loader.load();
            ModifyShoppingListViewController modifyShoppingListViewController = (ModifyShoppingListViewController) loader.getController();
            modifyShoppingListViewController.loadShoppingList(shoppingList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Parent getRoot() {
        return this.root;
    }
}