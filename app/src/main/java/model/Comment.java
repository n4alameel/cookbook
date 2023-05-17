package model;

public class Comment {
    private int id;
    private String text;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int userId;

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    private int recipeId;
    private String commentatorName;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private String imgUrl;
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public Comment(int id, int userId, int recipeId, String text, String commentatorName, String imgUrl){
        this.id = id;
        this.text = text;
        this.commentatorName = commentatorName;
        this.userId = userId;
        this.recipeId = recipeId;
        this.imgUrl = imgUrl;
    }
    public String getCommentatorName() {
        return commentatorName;
    }

    public void setCommentatorName(String commentatorName) {
        this.commentatorName = commentatorName;
    }

}
