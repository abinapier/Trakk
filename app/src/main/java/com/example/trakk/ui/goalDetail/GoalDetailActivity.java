package com.example.trakk.ui.goalDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trakk.R;
import com.example.trakk.ui.addGoal.AddGoalActivity;
import com.example.trakk.ui.addSubtask.AddSubtaskActivity;
import com.example.trakk.ui.main.MainActivity;

public class GoalDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_detail);

        String savedExtra = getIntent().getStringExtra("animal");
        TextView myText = (TextView) findViewById(R.id.textID);
        myText.setText(savedExtra);
    }

    public void updateProgress(){

    }

    public void viewAddSubtask(View view){
        Intent intent = new Intent(GoalDetailActivity.this, AddGoalActivity.class);
        startActivity(intent);
    }
}
