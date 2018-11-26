package com.aps_anhembi.aps_anhembi.Model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String Name;

    public Usuario(){

    }
    public Usuario(String Name){
        this.Name=Name;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
