package com.example.trakk.ui.addGoal;

import com.example.trakk.model.Goals;

public class AddGoalPresenter {

    private Goals goal;
    private View view;

    public AddGoalPresenter(View view) {
        this.goal = new Goals();
        this.view = view;
    }

    public void updateDescription(String description) { goal.setDescription(description); }

    public void updateName(String name) { goal.setGoalName(name); }

    public void markComplete(boolean complete) { goal.setComplete(complete); }

    public interface View {
    }
}