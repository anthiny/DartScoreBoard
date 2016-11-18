package com.application.anthony.dartscoreboard.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.application.anthony.dartscoreboard.Model.SettingModel;
import com.application.anthony.dartscoreboard.R;

import org.w3c.dom.Text;

public class Main extends AppCompatActivity {
    SettingModel temp;
    TextView textView;
    int a = 0;
    private static final int REQUEST_GET_SETTING_DATA = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.testMainText);
        Intent intent = new Intent(getApplicationContext(), SettingPopUp.class);
        startActivityForResult(intent, REQUEST_GET_SETTING_DATA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_GET_SETTING_DATA && resultCode == Activity.RESULT_OK){
            temp = data.getParcelableExtra("SettingModel");
            settingDisplay();
        }
    }

    public void settingDisplay(){
        String goalScore = String.valueOf(temp.getGoalScore());
        String playerCnt = String.valueOf(temp.getPlayersCnt());
        Toast.makeText(getApplicationContext(), "Goal: "+goalScore+"  Players: "+playerCnt, Toast.LENGTH_LONG).show();
    }
}
