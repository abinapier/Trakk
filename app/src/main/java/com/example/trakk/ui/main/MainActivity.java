package com.example.trakk.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.trakk.R;

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

    public void addGoal(View view){

    }

    public void viewGoalDetail(View view){

    }
}
