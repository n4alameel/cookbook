package model;

import model.Recipe;

public class Message {
    private int id;
    private String text; // Maybe private Recipe
    private int isRead;
    private int senderId;
    private int receiverId;
    private int recipeId;

    private String username;
    private String recipeName;

    public Message(int id, String text, int isRead, int senderId, int receiverId, int recipeId){
        this.id = id;
        this.text = text;
        this.isRead = isRead;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.recipeId = recipeId;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setRecipeName(String recipeName){
        this.recipeName = recipeName;
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

    public String getUsername() {
        return username;
    }

    public String getRecipeName() {
        return recipeName;
    }
}
