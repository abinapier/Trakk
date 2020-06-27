package com.example.trakk.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.trakk.R;
import com.example.trakk.ui.addGoal.AddGoalActivity;
import com.example.trakk.ui.goalDetail.GoalDetailActivity;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "main activity view";
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            presenter = new MainPresenter(new WeakReference<MainActivity>(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread presenterThread = new Thread(presenter);
        presenterThread.start();
    }


    public void addGoalButton(View view){
        Intent intent = new Intent(MainActivity.this, AddGoalActivity.class);
        startActivity(intent);
    }

    public void viewGoalDetail(View view){
        Intent intent = new Intent(MainActivity.this, GoalDetailActivity.class);
        startActivity(intent);
    }
}
