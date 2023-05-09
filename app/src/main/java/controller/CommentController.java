package controller;

import javafx.css.converter.FontConverter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Comment;
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
        if (this.currentUserId == this.comment.getUserId()){
            editBtn.setStyle("visibility: true");
            removeBtn.setStyle("visibility: true");
        }
        else{
            editBtn.setStyle("visibility: false");
            removeBtn.setStyle("visibility: false");
        }
    }
    public void editCommentAction(){

    }
    public void removeCommentAction(){

    }
}
