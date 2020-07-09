package com.example.trakk.ui.goalDetail;

import com.example.trakk.model.FileHelper;
import com.example.trakk.model.User;

public class GoalDetailPresenter {

    @override
    protected void onBind(User user){

        User.getGoals().setGoals(goal.toString());
        //I feel i need to take in or use the returned usrString from the User.Java but I'm just
        //not sure, it seems simple but I can't quit get it to do what i think it should. 
    }

    public interface View {
    }
}
