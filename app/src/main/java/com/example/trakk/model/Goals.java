package com.example.trakk.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Goals {
    private static final String TAG = "Goals";
    private List<Subtask> tasks;
    private String goalName;
    private String description;
    private boolean complete;
    private Date endDate;

    public Goals(String name, String description, Date endDate){
        this.goalName = name;
        tasks = new ArrayList<>();
        complete = false;
        this.description = description;
        this.endDate = endDate;
    }

    //optional description constructor
    public Goals(String name){
        this.goalName = name;
        tasks = new ArrayList<>();
        complete = false;
        this.description = "";
        endDate = null;
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

    public Date getEndDate(){
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int calculatePercentComplete(){
        double total = 0;
        double complete = 0;
        for (Subtask subtask: tasks) {
            Log.d(TAG, "calculatePercentComplete: Task Found");
            total++;
            if(subtask.isComplete()){
                Log.d(TAG, "calculatePercentComplete: Complete");
                complete++;
            }
        }
        if(total==0){
            //there are no subtasks so the goal is just 0% complete
            Log.d(TAG, "calculatePercentComplete: No tasks found");
            return 0;
        }
        else{
            return (int) (100 * (complete/total));
        }
    }

    @Override
    public String toString() {
        String goalString = "Goals{" +
                ", goalName='" + goalName + '\'' +
                ", description='" + description + '\'' +
                ", complete=" + complete  + '\'' +
                ", endDate=" + endDate;

        for(Subtask task: tasks){
            goalString+=task.toString();
        }
        goalString +="}";

        return goalString;

    }
}
