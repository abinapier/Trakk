package com.example.trakk.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.trakk.R;
import com.example.trakk.model.FileHelper;
import com.example.trakk.model.Goals;
import com.example.trakk.model.User;

import java.io.IOException;
import java.lang.ref.WeakReference;


public class GoalPreviewPresenter{

    private static final String TAG = "Goal Preview Fragment:";
    public Goals goal;
    public GoalPreviewPresenter(Goals goal){
        this.goal = goal;
    }

    public View populateFragment(View fragmentView) {
        // Inflate the layout for this fragment

        TextView name = fragmentView.findViewById(R.id.goal_name);
        name.setText(goal.getGoalName());
        Log.d(TAG, "populateFragment: Name " +goal.getGoalName());
        int percentComplete = goal.calculatePercentComplete();

        Log.d(TAG, "populateFragment: Percent "+percentComplete);
        ProgressBar progressBar = fragmentView.findViewById(R.id.goalProgress);
        progressBar.setProgress(percentComplete);

        TextView percentText = fragmentView.findViewById(R.id.percentComplete);
        percentText.setText(percentComplete+"% complete");
        return fragmentView;
    }
}

