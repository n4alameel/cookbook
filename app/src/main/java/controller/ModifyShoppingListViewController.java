package controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import model.Ingredient;
import model.ShoppingList;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import javax.swing.text.html.ImageView;
import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;

public class ModifyShoppingListViewController {
  Controller controller = Controller.getInstance();
  private ArrayList<Ingredient> ingredientsList;
  @FXML
  private AnchorPane anchorPane;
  @FXML
  private Label modifyLabel;
  private VBox vBox = new VBox();

  public void loadShoppingList(ShoppingList shoppingList) {
    ingredientsList = shoppingList.getIngredientsList();
    vBox.getChildren().addAll(modifyLabel);
    for (Ingredient ingredient : ingredientsList) {
      Label name = new Label(ingredient.getName());
      int quantity = ingredient.getQuantity();
      int step;
      if (quantity > 100)
        step = 100;
      else
        step = 1;
      Spinner spinner = new Spinner(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, quantity, step));
      Label unit = new Label(ingredient.getUnitName());
      Button buttonDelete = new Button("Remove");
      AnchorPane pane = new AnchorPane(name, spinner, unit, buttonDelete);
      pane.setId(ingredient.getName());
      name.setLayoutX(250);
      spinner.setLayoutX(600);
      unit.setLayoutX(765);
      buttonDelete.setLayoutX(900);
      buttonDelete.setOnAction(event -> {
        deleteIngredient(ingredient);
      });
      vBox.getChildren().addAll(pane);
    }
    Button buttonPdf = new Button("Print");
    vBox.getChildren().addAll(buttonPdf);
    buttonPdf.setOnAction(event -> {
      createFile();
    });
    anchorPane.getChildren().addAll(vBox);
  }

  public void saveQuantities() {
    VBox v = (VBox) anchorPane.getChildren().get(0);
    int i = 1;
    for (Ingredient ingredient : ingredientsList) {
      AnchorPane pane = (AnchorPane) v.getChildren().get(i);
      Spinner s = (Spinner) pane.getChildren().get(1);
      ingredient.setQuantity(Integer.valueOf(s.getValueFactory().getValue().toString()));
      i++;
    }
  }

  public void deleteIngredient(Ingredient ingredient) {
    saveQuantities();
    ingredientsList.remove(ingredient);
    vBox.getChildren().clear();
    ShoppingList shoppingList = new ShoppingList();
    for (Ingredient i : ingredientsList) {
      shoppingList.addIngredients(i);
    }
    loadShoppingList(shoppingList);
  }

  public void createFile() {
    saveQuantities();
    String path = "ShoppingList.pdf";
    try {
      Document document = new Document();
      Files.deleteIfExists(Path.of(path));
      OutputStream outputStream = new FileOutputStream(new File(path));
      PdfWriter.getInstance(document, outputStream);
      document.open();
      Font font = FontFactory.getFont(FontFactory.TIMES, 16, BaseColor.BLACK);
      Paragraph shopList = new Paragraph("Shopping list", font);
      shopList.setAlignment(Element.ALIGN_CENTER);
      document.add(shopList);

      PdfPTable table = new PdfPTable(new float[] {3, 1});
      table.getDefaultCell().setBorder(0);
      table.addCell(" ");
      table.addCell(" ");
      for (Ingredient ingredient : ingredientsList) {
        table.addCell(ingredient.getName());
        table.addCell(Integer.toString(ingredient.getQuantity()) + ingredient.getUnitName());
      }
      document.add(table);

      document.close();
      outputStream.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    if (Desktop.isDesktopSupported()) {
      try {
        File myFile = new File(path);
        Desktop.getDesktop().open(myFile);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
