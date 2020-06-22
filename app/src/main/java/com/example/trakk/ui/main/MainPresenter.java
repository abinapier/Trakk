package com.example.trakk.ui.main;

import com.example.trakk.model.FileHelper;
import com.example.trakk.model.User;

public class MainPresenter {
    public User user;
    private MainActivity ui;
    public MainPresenter(MainActivity ui){
        user = new User();
        this.ui = ui;
    }
}
