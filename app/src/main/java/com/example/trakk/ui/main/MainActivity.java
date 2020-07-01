package com.example.trakk.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trakk.CustomListAdapter;
import com.example.trakk.R;
import com.example.trakk.ui.addGoal.AddGoalActivity;
import com.example.trakk.ui.goalDetail.GoalDetailActivity;

import java.io.IOException;
import java.lang.ref.WeakReference;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private MainPresenter presenter;
    ListView listView;
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

        CustomListAdapter goalDetail = new CustomListAdapter(this, nameArray, infoArray, imageArray);
        listView = (ListView) findViewById(R.id.listviewID);
        listView.setAdapter(goalDetail);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                String message = nameArray[position];
                intent.putExtra("animal", message);
                startActivity(intent);

            }
        });
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
