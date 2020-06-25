package com.example.trakk.ui.addSubtask;

import android.view.View;

import com.example.trakk.model.Subtask;

public class AddSubtaskPresenter {
    private Subtask subtask;
    private View view;

    public AddSubtaskPresenter(View view) {
        this.subtask = new Subtask();
        this.view = view;
    }

    public void updateDescription(String description) {
        subtask.setDescription(description);
    }

    public void updateName(String name) {
        subtask.setName(name);
    }

    public void markComplete(boolean complete) {
        subtask.setComplete(complete);
    }

    public interface View {
    }
}
