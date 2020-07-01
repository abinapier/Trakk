package com.example.trakk.ui.goalDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.trakk.R;
import com.example.trakk.ui.addGoal.AddGoalActivity;
import com.example.trakk.ui.addSubtask.AddSubtaskActivity;
import com.example.trakk.ui.main.MainActivity;

public class GoalDetailActivity extends AppCompatActivity {
    private static final String TAG = "goal detail view";
    private String goalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_detail);
        Intent myIntent = getIntent();
        this.goalName = myIntent.getStringExtra("goalName");
        Log.d(TAG, "onCreate: "+goalName);
    }

    public void updateProgress(){

    }

    public void viewAddSubtask(View view){
        Intent intent = new Intent(GoalDetailActivity.this, AddGoalActivity.class);
        startActivity(intent);
    }
}
