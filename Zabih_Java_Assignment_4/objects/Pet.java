package objects;

public class Pet {
    private String name;
    private String type;
    private int age;
    private String color;
    private String breed;
    private String picture;
    private String owner;
    private boolean isAdopted;

    public Pet(String name, String type,
               int age, String color, String breed) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.color = color;
        this.breed = breed;
        this.picture = "";
        this.owner = "";
        this.isAdopted = false;
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getAge() {
        return this.age;
    }

    public String getColor() {
        return this.color;
    }

    public String getBreed() {
        return this.breed;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getOwner() {
        return this.owner;
    }

    public boolean getIsAdopted() {
        return this.isAdopted;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setIsAdopted() {
        this.isAdopted = true;
    }
}
