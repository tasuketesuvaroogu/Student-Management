package model;

public class Menu {
    private int id;
    private String name;
    private String description;
    private int index;
    private int type;

    public Menu(int id, String name, String description, int index, int type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.index = index;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
}