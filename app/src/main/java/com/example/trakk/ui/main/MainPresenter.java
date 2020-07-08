package com.example.trakk.ui.main;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainPresenter implements Runnable{

    private static final String TAG = "Main Presenter:";
    public User user;
    private WeakReference<MainActivity> ui;
    private static int notificationId = 1;
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
                            int goalcount = 0;
                            for (Goals goal: user.getGoals()) {
                                goalcount++;
                                String tag = "goalNum"+goalcount;
                                Log.d(TAG, "run: Goal Found");
                                FragmentManager fm = ui.get().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                GoalPreviewFragment newFragment = new GoalPreviewFragment(goal);
                                fragmentTransaction.add(R.id.goalPreviewContainer, newFragment, tag );
                                fragmentTransaction.commit();
                                Calendar endDate = new GregorianCalendar();
                                endDate.setTime(goal.getEndDate());
                                Calendar today = new GregorianCalendar();
                                int response = endDate.compareTo(today);
                                int daysbetween = daysBetween(today, endDate);
                                if(response<0){
                                    //goal end date has passed or is the same
                                    if(daysbetween==0){
                                        showNotification(goal,goal.getGoalName()+" is due today!");
                                    }else{
                                        Log.d(TAG, "run: date has passed");
                                        showNotification(goal,goal.getGoalName()+" is past due!");
                                    }

                                }else{
                                    //goal is due in the future
                                    Log.d(TAG, "run: date is in future"+endDate.get(Calendar.MONTH) + today.get(Calendar.MONTH));
                                    switch (daysbetween){
                                        case 30:
                                            showNotification(goal,goal.getGoalName()+" is due in 30 days!");
                                            break;
                                        case 7:
                                            showNotification(goal,goal.getGoalName()+" is due in a week!");
                                            break;
                                        case 5:
                                            showNotification(goal,goal.getGoalName()+" is due in 5 days!");
                                            break;
                                        case 3:
                                            showNotification(goal,goal.getGoalName()+" is due in 3 days!");
                                            break;
                                        case 1:
                                        case 0:
                                            showNotification(goal,goal.getGoalName()+" is due tomorrow!");
                                            break;
                                    }

                                }
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
        Date dateOne = new GregorianCalendar(2020, 7, 15).getTime();
        Date dateTwo = new Date();
        Goals goalOne = new Goals("Test 1", "A goal for testing out file creation and fragments.", dateOne);
        Goals goalTwo = new Goals("Test 2", "Another goal for testing out file creation and fragments.", dateTwo);

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

    public void showNotification(Goals goal, String message) {
        NotificationManager notificationManager =
                (NotificationManager) ui.get().getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "my_channel_id";
        CharSequence channelName = "My Channel";


        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        String title = goal.getGoalName();

        NotificationChannel notificationChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{1000, 2000});
            notificationManager.createNotificationChannel(notificationChannel);
        }


        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(ui.get(), channelId)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_notify)
                    .build();
        } else{
            notification = new Notification.Builder(ui.get())
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_notify)
                    .build();
        }

        notificationManager.notify(notificationId, notification);
        notificationId++;
    }

    public int daysBetween(Calendar d1, Calendar d2){
        return (int)( (d2.getTime().getTime() - d1.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }
}


