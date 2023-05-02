package model;

public class Tag {
    private int id;
    private String name = null;
    
    public Tag(int id, String name){
        this.name = name;
        this.id = id;
        //SQL QUERY
    }

    public String getName() {
        return this.name;
    }
    public int getId() {
        return this.id;
    }
}
