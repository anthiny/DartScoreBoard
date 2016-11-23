package com.application.anthony.dartscoreboard.Activities;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.application.anthony.dartscoreboard.Adapter.LazyAdapter;
import com.application.anthony.dartscoreboard.ListViewItem;
import com.application.anthony.dartscoreboard.Model.SettingModel;
import com.application.anthony.dartscoreboard.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main extends AppCompatActivity{

    private int goalScore = 0;
    private int playersCnt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        popUpDialog();
    }

    public void popUpDialog(){
        Dialog dialog = new Dialog(Main.this);
        dialog.setTitle(getResources().getString(R.string.setting_dialog_title));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.setting_dialog);
        settingSpinner(dialog);
        settingButton(dialog);
        settingSeekBar(dialog);
        dialog.show();
    }

    public void settingSpinner(Dialog dialog){
        Spinner s = (Spinner)dialog.findViewById(R.id.goalSpinner);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
                goalScore = Integer.parseInt(parent.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void settingButton(final Dialog dialog){
        Button b = (Button)dialog.findViewById(R.id.close);

        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SettingModel.getInstance().setGoalScore(goalScore);
                SettingModel.getInstance().setPlayersCnt(playersCnt);
                dialog.cancel();
                makeList();
            }
        });
    }

    public void settingSeekBar(final Dialog dialog){
        int defaultStep = 0;

        SeekBar seekBar = (SeekBar)dialog.findViewById(R.id.playerSeekBar);
        seekBar.setProgress(defaultStep);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                showSelectedPlayersCnt(dialog, progress);
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

    public void showSelectedPlayersCnt(Dialog dialog, int progress){
        int currentSetPlayers = progress + 1;
        TextView showPlayersCnt = (TextView)dialog.findViewById(R.id.playerCount);
        showPlayersCnt.setText(String.valueOf(currentSetPlayers));
    }

    public void makeList(){
        ArrayList<ListViewItem> listViewItems = new ArrayList<>();
        LazyAdapter lazyAdapter = new LazyAdapter(this, R.layout.list_row,listViewItems);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(lazyAdapter);
        ArrayList<String> nickNameList = new ArrayList<>();
        nickNameList.addAll(Arrays.asList(getResources().getStringArray(R.array.nickNames)));

        Random random = new Random();
        int maxIdx = nickNameList.size() - 1;
        for (int i=0; i<SettingModel.getInstance().getPlayersCnt();i++){
            int rIdx = 0;
            if (i<4){
                rIdx = random.nextInt(maxIdx - i);
            }
            lazyAdapter.addItem(null, nickNameList.get(rIdx), String.valueOf(SettingModel.getInstance().getGoalScore()));
            nickNameList.remove(rIdx);
        }

    }
}
