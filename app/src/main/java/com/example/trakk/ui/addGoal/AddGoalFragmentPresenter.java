package com.example.trakk.ui.addGoal;

import android.view.View;

import com.example.trakk.model.Goals;
import com.example.trakk.model.Subtask;

public class AddGoalFragmentPresenter {
    private static final String TAG = "Subtask Preview Fragment:";
    public Subtask task;
    public AddGoalFragmentPresenter(Subtask task) {
        this.task = task;
    }

    public View subtaskFragment(View fragmentView) {
        // Inflate the layout for this fragment
        return fragmentView;}
}
