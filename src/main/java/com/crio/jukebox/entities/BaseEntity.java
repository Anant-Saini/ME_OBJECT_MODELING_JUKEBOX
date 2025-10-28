package com.crio.jukebox.entities;

public abstract class BaseEntity {
    protected String id;
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
}
