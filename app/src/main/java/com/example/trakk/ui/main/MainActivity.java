package com.example.trakk.ui.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
            presenter = new MainPresenter(new WeakReference<>(this));
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
        Intent myIntent = new Intent(this, GoalDetailActivity.class);
        ViewGroup myView = (ViewGroup) view;
        ViewGroup clickedFragment = (ViewGroup) myView.getChildAt(1);
        TextView goalName = (TextView) clickedFragment.getChildAt(0);
        Log.d(TAG, "viewGoalDetail: "+goalName.getText());
        myIntent.putExtra("goalName",goalName.getText());
        startActivity(myIntent);
    }

}

