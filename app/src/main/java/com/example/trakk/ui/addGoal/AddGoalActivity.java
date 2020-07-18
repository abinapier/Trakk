package com.example.trakk.ui.addGoal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trakk.R;
import com.example.trakk.model.FileHelper;
import com.example.trakk.model.Goals;
import com.example.trakk.model.Subtask;
import com.example.trakk.model.User;
import com.example.trakk.model.frequency;
import com.example.trakk.ui.addSubtask.AddSubtaskActivity;
import com.example.trakk.ui.main.MainActivity;

import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;

public class AddGoalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "AddGoalActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private AddGoalPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        Spinner aSpinner = findViewById(R.id.aSpinner);
        aSpinner.setOnItemSelectedListener(this);
        try {
            this.presenter = new AddGoalPresenter(new WeakReference<AddGoalActivity>(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread presenterThread = new Thread(presenter);
        presenterThread.start();

        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddGoalActivity.this, android.R.style.Theme_DeviceDefault_Light,
                        onDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();


            }

        });


        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yy: " + month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);

            }
        };

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView){

    }
    public void addTaskButon(View view){
        Intent intent = new Intent(this, AddSubtaskActivity.class);
        startActivity(intent);
    }
    public void editGoalButton(View view) throws ParseException, IOException {
        TextView NameGoal =  findViewById(R.id.GoalName);
        TextView GoalDescription = findViewById(R.id.GoalDescription);
        TextView startDateText = findViewById(R.id.tvDate);
        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDateText.getText().toString());
        Spinner goalFrequency = findViewById(R.id.aSpinner);
        frequency curFrequency = frequency.valueOf(goalFrequency.getSelectedItem().toString());
        Goals curGoal = new Goals(NameGoal.getText().toString(), GoalDescription.getText().toString(), startDate, curFrequency);
        LinearLayout subtaskLayout = findViewById(R.id.containerTask);
        for (int i = 0; i < subtaskLayout.getChildCount(); i++) {
            ViewGroup v = (ViewGroup) subtaskLayout.getChildAt(i);
            ViewGroup layerOne = (ViewGroup) v.getChildAt(1);
            ViewGroup text = (ViewGroup) layerOne.getChildAt(1);
            TextView subTaskName = (TextView) text.getChildAt(0);
            TextView subTaskDescription = (TextView) text.getChildAt(1);
            Subtask curSubtask = new Subtask(subTaskDescription.getText().toString(), subTaskName.getText().toString());
            curGoal.addTask(curSubtask);
        }

        User fileUser = FileHelper.ReadFile(this.getApplicationContext().getFilesDir().toString());
        fileUser.addGoal(curGoal);
        FileHelper.WriteFile(fileUser, this.getApplicationContext().getFilesDir().toString());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

