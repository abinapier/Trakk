package com.example.trakk.model;

import java.util.ArrayList;
import java.util.List;

public class Goals {
    private List<Subtask> tasks;
    private String goalName;
    private String description;
    private boolean complete;

    public Goals(String name, String description){
        this.goalName = name;
        tasks = new ArrayList<>();
        complete = false;
        this.description = description;
    }

    //optional description constructor
    public Goals(String name){
        this.goalName = name;
        tasks = new ArrayList<>();
        complete = false;
        this.description = "";
    }

    public List<Subtask> getTasks() {
        return tasks;
    }

    public void addTask(Subtask newTask) {
        tasks.add(newTask);
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isComplete() {
        return complete;
    }
}
