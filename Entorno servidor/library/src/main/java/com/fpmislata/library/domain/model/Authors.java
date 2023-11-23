package com.fpmislata.library.domain.model;

public class Authors {
    int id;
    String name;

    public Authors(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Authors(){

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
