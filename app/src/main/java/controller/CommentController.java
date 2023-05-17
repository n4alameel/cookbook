package controller;

import javafx.css.converter.FontConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Comment;

import java.io.IOException;
import java.util.function.Function;

public class CommentController {
    Controller controller = Controller.getInstance();
    @FXML
    private Text commentText;
    @FXML
    private Text commentatorName;
    private Comment comment;
    @FXML
    private Button editBtn;
    @FXML
    private Button removeBtn;
    @FXML
    private VBox processingComment;
    @FXML
    private ImageView commentatorAva;
    private int currentUserId;
    public void setComments(Comment commentByRecipe){
        this.comment = commentByRecipe;
    }
    public void updateComments(){
        this.currentUserId = controller.getActiveUser().getId();
        commentText.setText(this.comment.getText());
        commentText.setFont(Font.font("System", FontWeight.THIN, FontPosture.ITALIC, 12));
        commentatorName.setFont(Font.font("System", FontWeight.BOLD, 14));
        commentatorName.setText(this.comment.getCommentatorName());
        Image imageObject = new Image(this.comment.getImgUrl());
        commentatorAva.setImage(imageObject);
        if (this.currentUserId == this.comment.getUserId()){
            editBtn.setStyle("visibility: true");
            removeBtn.setStyle("visibility: true");
        }
        else{
            editBtn.setStyle("visibility: false");
            removeBtn.setStyle("visibility: false");
        }
    }
    public void editCommentAction(ActionEvent event){
        try {
            commentText.setStyle("visibility: false");
            editBtn.setStyle("visibility: false");
            removeBtn.setStyle("visibility: false");
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CommentEdition.fxml"));
                Pane root = loader.load();
                processingComment.getChildren().add(1, root);
                CommentEditionController commentEditionController = loader.getController();
                commentEditionController.showFieldToChange(this.comment);
            }
            catch(IOException e){}
        } catch (Exception e) {
            System.out.println("Comment was not deleted");
        }
    }
    public void removeCommentAction(ActionEvent event){
        try {
            this.controller.deleteCommentById(this.comment.getId());
            this.controller.displayRecipeView(this.comment.getRecipeId());
        } catch (Exception e) {
            System.out.println("Comment was not deleted");
        }
    }


}
