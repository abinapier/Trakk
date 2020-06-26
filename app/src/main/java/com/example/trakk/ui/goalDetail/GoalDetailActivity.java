package com.example.trakk.ui.goalDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.trakk.R;

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
}
