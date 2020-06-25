package com.example.trakk.model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
    private static final String TAG = "FileHelper";
    public static String filePath = "goals.json";


    public static User ReadFile(String fileDir) throws IOException {
        Log.d(TAG, "ReadFile: "+fileDir);
        Gson json = new Gson();
        File file = new File(fileDir, filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null){
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        String jsonStr = stringBuilder.toString();
        User userClass= json.fromJson(jsonStr, User.class);
        Log.d(TAG, "userClass: "+userClass.toString());
        return userClass;

    }

    public static void WriteFile(User usrObj, String fileDir) throws IOException {
        Log.d(TAG, "WriteFile: "+fileDir);
        Gson json = new Gson();
        String jsonString = json.toJson(usrObj);
        Log.d(TAG, "JSON: "+jsonString);
        File file = new File(fileDir ,filePath);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(jsonString);
        bufferedWriter.close();

    }

    public static boolean fileExists(String fileDir)
    {
        Log.d(TAG, "FileExists: "+fileDir);

        File file = new File(fileDir ,filePath);
        return file.exists();

    }

}
