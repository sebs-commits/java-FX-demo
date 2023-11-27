package com.example.comp228_db_javafx_demo;

public class Student {
    String name;
    int id;

    public Student(int id,String name) {
        this.name = name;
        this.id = id;
    }

    public Student(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
