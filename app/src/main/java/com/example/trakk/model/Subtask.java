package com.example.trakk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


    public class Subtask {
        private static final String TAG = "Subtask";
        private List<Subtask> tasks;
        private String subTaskName;
        private String description;
        private boolean complete;


        public Subtask(){}

        public Subtask(String description, String name){
        this.subTaskName = subTaskName;
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

    public void setName(String name) { this.subTaskName = name; }
    public String getName() { return subTaskName; }

    @Override
    public String toString() {
        return "Subtask{" +
                "description='" + description + '\'' +
                ", name='" + subTaskName + '\'' +
                ", complete=" + complete +
                '}';
    }
}
