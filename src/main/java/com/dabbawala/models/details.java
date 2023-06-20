package com.dabbawala.models;

import lombok.Data;

import java.io.Serializable;

public class details implements Serializable {
    private String name ;


    public details(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

