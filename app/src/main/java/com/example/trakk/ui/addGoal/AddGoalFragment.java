package com.example.trakk.ui.addGoal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trakk.R;
import com.example.trakk.model.Subtask;


public class AddGoalFragment extends Fragment {
    public AddGoalFragmentPresenter presenter;

    public AddGoalFragment (Subtask curSubtask) { presenter = new AddGoalFragmentPresenter(curSubtask);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containerTask,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_goal, containerTask, false);
        return presenter.subtaskFragment(rootView);
    }


}