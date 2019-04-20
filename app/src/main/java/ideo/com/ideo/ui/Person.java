package ideo.com.ideo.ui;

public class Person {
    private int id;
    private String name;

    public Person(int id, String nombre) {
        this.id = id;
        this.name = nombre;
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
}
