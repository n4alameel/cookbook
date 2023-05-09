package model;

public class Unit {
    private int id;
    private String name;
    public Unit(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}
