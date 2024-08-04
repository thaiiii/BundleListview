package com.example.bundlelistview;

import java.io.Serializable;

public class Person implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
        super();
    }

    public String toString() {
        return this.id + " - " + this.name;
    }
}
