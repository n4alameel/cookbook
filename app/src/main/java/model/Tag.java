package model;

public class Tag {
    private String name;
    
    public Tag(String tag){
        this.name = tag;
        //SQL QUERY
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
