package com.example.trakk.ui.main;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.trakk.R;
import com.example.trakk.model.FileHelper;
import com.example.trakk.model.Goals;
import com.example.trakk.model.Subtask;
import com.example.trakk.model.User;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class MainPresenter implements Runnable{

    private static final String TAG = "Main Presenter:";
    public User user;
    private WeakReference<MainActivity> ui;
    public MainPresenter(WeakReference<MainActivity> ui) throws IOException {
        this.ui = ui;
        createTestFile();
    }


    @Override
    public void run(){

        if(ui.get() != null) {


            ui.get().runOnUiThread(new Runnable() {
                public void run() {
                    Context context = ui.get().getApplicationContext();
                    LinearLayout layout = ui.get().findViewById(R.id.goalPreviewContainer);
                    if(FileHelper.fileExists(context.getFilesDir().toString())){
                        //file exists, load file and add goal preview fragments
                        Log.d(TAG, "MainPresenter: File Exists");
                        try {
                            //get user object from file
                            user = FileHelper.ReadFile(context.getFilesDir().toString());
                            //loop through each goal in file
                            for (Goals goal: user.getGoals()) {
                                Log.d(TAG, "run: Goal Found");
                                FragmentManager fm = ui.get().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                GoalPreviewFragment newFragment = new GoalPreviewFragment(goal);
                                fragmentTransaction.add(R.id.goalPreviewContainer, newFragment, "HELLO");
                                fragmentTransaction.commit();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        //file doesn't exist, add message to view
                        Log.d(TAG, "MainPresenter: File Does not Exist");

                        TextView textView = new TextView(ui.get());
                        textView.setTextSize(25);
                        textView.setText("No goals found.");
                        textView.setGravity(Gravity.CENTER);
                        layout.addView(textView);
                        layout.invalidate();
                    }

                }
            });
        }
    }

    public void createTestFile() throws IOException {
        Goals goalOne = new Goals("Test 1", "A goal for testing out file creation and fragments.");
        Goals goalTwo = new Goals("Test 2", "Another goal for testing out file creation and fragments.");

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
