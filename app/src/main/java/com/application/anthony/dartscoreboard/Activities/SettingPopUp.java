package com.application.anthony.dartscoreboard.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.application.anthony.dartscoreboard.Model.SettingModel;
import com.application.anthony.dartscoreboard.R;

import java.io.Serializable;

/**
 * Created by anthony on 2016. 11. 16..
 */

public class SettingPopUp extends Activity{

    private int goalScore = 301;
    private int playersCnt = 1;

    int defaultStep = 0;
    SeekBar seekBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_popup);

        settingButton();
        settingSeekBar();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width, height);
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }

    public void settingButton(){
        Button b = (Button)findViewById(R.id.close);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SettingModel setting = new SettingModel(playersCnt,goalScore);
                Bundle bundle = new Bundle();
                //bundle.putParcelable("settingData", setting);
                //setResult(Activity.RESULT_OK, new Intent().putExtras("settingData", bundle));
                finish();
            }
        });
    }

    public void settingSeekBar(){
        seekBar = (SeekBar) findViewById(R.id.playerSeekBar);
        seekBar.setProgress(defaultStep);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                showSelectedPlayersCnt(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int currentStep = seekBar.getProgress();
                playersCnt = currentStep + 1;
            }
        });
    }

    public void showSelectedPlayersCnt(int progress){
        int currentSetPlayers = progress + 1;
        TextView showPlayersCnt = (TextView) findViewById(R.id.playerCount);
        showPlayersCnt.setText(String.valueOf(currentSetPlayers));
    }
}
