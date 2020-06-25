package com.example.trakk.model;

public class Subtask {
    private String description;
    private String name;
    private boolean complete;

    public Subtask() {

    }

    public Subtask(String description, String name){
        this.name = name;
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

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Subtask{" +
                "description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", complete=" + complete +
                '}';
    }
}
