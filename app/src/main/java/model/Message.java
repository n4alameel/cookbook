package model;

import model.Recipe;

public class Message {
    private int id;
    private String text; // Maybe private Recipe
    private int isRead;
    private int senderId;
    private int receiverId;
    private int recipeId;

    private String senderIdString;
    private String recipeIdString;

    public Message(int id, String text, int isRead, int senderId, int receiverId, int recipeId){
        this.id = id;
        this.text = text;
        this.isRead = isRead;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.recipeId = recipeId;
    }

    public String getText(){
        return this.text;
    }

    public int getSender(){
        return this.senderId;
    }

    public int getRecipe(){
        return this.recipeId;
    }

    public int getReceiver(){
        return this.receiverId;
    }

    public String getSenderIdString() {
        return String.valueOf(senderId);
    }

    public String getRecipeIdString() {
        return String.valueOf(recipeId);
    }
}
