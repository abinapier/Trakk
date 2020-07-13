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
        createTestFile();
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
                                fragmentTransaction.add(R.id.containerTask, newFragment, tag);
                                fragmentTransaction.commit();
                            }
            });
        }
    }


    private void createTestFile() throws IOException {
        Date dateOne = new GregorianCalendar(2020, 7, 15).getTime();
        Date dateTwo = new Date();
        Goals goalOne = new Goals("Test 1", "A goal for testing out file creation and fragments.", dateOne, frequency.Daily);
        Goals goalTwo = new Goals("Test 2", "Another goal for testing out file creation and fragments.", dateTwo, frequency.Weekly);

        Subtask subtaskOne = new Subtask("Complete example task.", "One");
        Subtask subtaskTwo = new Subtask("Example task that is completed", "Two");
        subtaskTwo.setComplete(true);
        Subtask subtaskThree = new Subtask("Something else to do", "Three");


        goalOne.addTask(subtaskOne);
        goalOne.addTask(subtaskTwo);

        goalTwo.addTask(subtaskOne);
        goalTwo.addTask(subtaskTwo);
        goalTwo.addTask(subtaskThree);

        User testUser = new User();
        testUser.addGoal(goalOne);
        testUser.addGoal(goalTwo);

        FileHelper.WriteFile(testUser, ui.get().getApplicationContext().getFilesDir().toString());
        Log.d(TAG, "createTestFile: File created");

    }
}