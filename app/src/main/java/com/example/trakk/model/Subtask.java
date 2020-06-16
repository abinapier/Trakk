package com.example.trakk.model;

public class Subtask {
    private String description;
    private boolean complete;

    public Subtask(String description){
        this.description = description;
        complete = false;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isComplete() {
        return complete;
    }
}
