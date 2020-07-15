package com.example.trakk.ui.goalDetail;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trakk.R;
import com.example.trakk.model.FileHelper;
import com.example.trakk.model.Goals;
import com.example.trakk.model.User;

import java.io.IOException;
import java.lang.ref.WeakReference;

import static android.content.ContentValues.TAG;

public class GoalDetailPresenter implements Runnable {
    private final String goalName;
    private User user;
    private WeakReference<GoalDetailActivity> ui;

    GoalDetailPresenter(String goalName) {

        //I want to use Filehelper to load the user class.
        //I want to say if goalName is found then load goal name
        //when GoalDetail is pulled up

    }


    @Override
    public void run(){

        if(ui.get() != null) {


            ui.get().runOnUiThread(new Runnable() {
                public void run() {
                    Context context = ui.get().getApplicationContext();
                    LinearLayout layout = ui.get().findViewById(R.id.goalPreviewContainer);
                    if (FileHelper.fileExists(context.getFilesDir().toString())) {
                        //file exists, load file
                        Log.d(TAG, "GoalDetailPresenter: File Exists");
                        try {
                            //get user object from file
                            user = FileHelper.ReadFile(context.getFilesDir().toString());

                            for (int i = 0; i < goalNum; i++) {

                                String goalName = user.getGoals();
                                goalName.setText(to goal name);

                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    public interface View {

    }
    }


