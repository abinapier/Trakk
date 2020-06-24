package com.example.trakk.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.trakk.R;
import com.example.trakk.ui.addGoal.AddGoalActivity;
import com.example.trakk.ui.addGoal.AddGoalActivity;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(new WeakReference<MainActivity>(this));
        Thread presenterThread = new Thread(presenter);
        presenterThread.start();
    }

    public void viewGoalDetail(View view){

    }
    public void addGoalButton(View view){
        Intent intent = new Intent(MainActivity.this, AddGoalActivity.class);
        startActivity(intent);
    }
}
