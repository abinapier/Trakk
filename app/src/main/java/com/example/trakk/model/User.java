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

    public static List<Goals> getGoals() {
        return goals;
    }

    @Override
    public String toString() {
        String usrString = "User { goals = {";
        for(Goals goal: goals){
            usrString+=goal.toString();
        }
        usrString +="}}";

        return usrString;
    }
}
