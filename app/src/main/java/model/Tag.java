package model;

public class Tag {
    private int id;
    private String name = null;

    public int getUser_id() {
        return user_id;
    }

    private int user_id;

    
    public Tag(int id, String name, int user_id){
        this.name = name;
        this.id = id;
        this.user_id = user_id;
    }

    public String getName() {
        return this.name;
    }
    public int getId() {
        return this.id;
    }
}
