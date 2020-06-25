package com.example.trakk.ui.addSubtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.trakk.R;
import com.example.trakk.ui.addGoal.AddGoalActivity;


public class AddSubtaskActivity extends AppCompatActivity implements AddSubtaskPresenter.View {

    public static final String EXTRA_MESSAGE = ".com.example.trakk.MESSAGE";
    public static final String EXTRA_MESSAGE2 = ".com.example.trakk.MESSAGE2";

    private AddSubtaskPresenter presenter;
    String name;
    String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subtask);

        presenter = new AddSubtaskPresenter(this);
    }

    /**Called when user taps the Save button**/
    public void saveSubtask(View view) {
        EditText taskName = (EditText) findViewById(R.id.subtaskName);
        name = taskName.getText().toString();
        Log.i(name, "Task name");
        presenter.updateName(name);
        EditText taskDescription = (EditText) findViewById(R.id.subtaskDescription);
        description = taskDescription.getText().toString();
        Log.i(description, "Description");
        presenter.updateDescription(description);


    /**After task is saved, go back to AddGoalActivity**/
        Intent intent = new Intent(this, AddGoalActivity.class);
        intent.putExtra(EXTRA_MESSAGE, name);
        intent.putExtra(EXTRA_MESSAGE2, description);
        startActivity(intent);
    }

}
