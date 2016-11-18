package com.application.anthony.dartscoreboard.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.application.anthony.dartscoreboard.Model.SettingModel;
import com.application.anthony.dartscoreboard.R;

public class Main extends AppCompatActivity {
    private SettingModel temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(Main.this, SettingPopUp.class));
        //startActivityForResult(new Intent(Main.this, SettingPopUp.class), REQUEST_GET_SETTING_DATA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Activity.RESULT_OK){
            //temp = data.get("model", 0);
        }
    }
}
