package com.example.trakk.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Goals> goals;

    public User(){
        goals = new ArrayList<>();
    }


    public void addGoal(Goals newGoal){
        goals.add(newGoal);
    }

    public List<Goals> getGoals() {
        return goals;
    }
}
