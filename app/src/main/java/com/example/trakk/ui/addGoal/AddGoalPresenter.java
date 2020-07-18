package com.example.trakk.ui.addGoal;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.trakk.R;
import com.example.trakk.model.FileHelper;
import com.example.trakk.model.Goals;
import com.example.trakk.model.Subtask;
import com.example.trakk.model.User;
import com.example.trakk.model.frequency;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.GregorianCalendar;


public class AddGoalPresenter implements Runnable {
    private static final String TAG = "AddGoal Presenter:";
    public User user;
    public Subtask subtaskTestOne;
    public Subtask subtaskTestTwo;
    private WeakReference<AddGoalActivity> ui;
    public AddGoalPresenter(final WeakReference<AddGoalActivity> ui) throws IOException {
        this.ui = ui;
        subtaskTestOne = new Subtask("descriptionOne", "nameOne");
        subtaskTestTwo = new Subtask("descriptionTwo", "nameTwo");
    }


    @Override
    public void run(){

        if(ui.get() != null) {
            ui.get().runOnUiThread(new Runnable() {
                public void run() {
                    Context context = ui.get().getApplicationContext();
                    LinearLayout layout = ui.get().findViewById(R.id.containerTask);
                    String tag = "subtaskNumOne";
                    Log.d(TAG, "run: subtask Found");
                    FragmentManager fm = ui.get().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    AddGoalFragment newFragment = new AddGoalFragment(subtaskTestOne);
                    AddGoalFragment newFragmento = new AddGoalFragment(subtaskTestTwo);
                    fragmentTransaction.add(R.id.containerTask, newFragment, tag);
                    fragmentTransaction.add(R.id.containerTask, newFragmento, tag);
                    fragmentTransaction.commit();
                }
            });
        }
    }

}