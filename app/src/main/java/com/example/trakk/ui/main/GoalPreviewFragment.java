package com.example.trakk.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.trakk.R;
import com.example.trakk.model.Goals;
import com.example.trakk.ui.addGoal.AddGoalActivity;
import com.example.trakk.ui.goalDetail.GoalDetailActivity;

public class GoalPreviewFragment extends Fragment {
    public GoalPreviewPresenter presenter;

    //you need to pass a goal in order to create a new fragment
    public GoalPreviewFragment(Goals curGoal){
        presenter = new GoalPreviewPresenter(curGoal);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containerTask,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_goal_preview, containerTask, false);

        return presenter.populateFragment(rootView);
    }


}