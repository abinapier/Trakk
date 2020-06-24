package com.example.trakk.ui.main;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trakk.R;
import com.example.trakk.model.FileHelper;
import com.example.trakk.model.User;

import java.lang.ref.WeakReference;

public class MainPresenter implements Runnable{

    private static final String TAG = "Main Presenter:";
    public User user;
    private WeakReference<MainActivity> ui;
    public MainPresenter(WeakReference<MainActivity> ui){
        user = new User();
        this.ui = ui;

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
}
