package com.application.anthony.dartscoreboard.Activities;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.application.anthony.dartscoreboard.Adapter.LazyAdapter;
import com.application.anthony.dartscoreboard.ListViewItem;
import com.application.anthony.dartscoreboard.Model.SettingModel;
import com.application.anthony.dartscoreboard.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

public class Main extends AppCompatActivity{

    private int goalScore = 0;
    private int playersCnt = 1;
    private LazyAdapter lazyAdapter = null;
    private ListView listView = null;
    private Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button)findViewById(R.id.Button);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                for (int i=0; i < SettingModel.getInstance().getPlayersCnt(); i++){
                    getScore(i);
                }
                lazyAdapter.sort();
            }
        });
        button.setVisibility(View.INVISIBLE);
        popUpDialog();
    }


    public void getScore(int index){

        final ListViewItem item = (ListViewItem) lazyAdapter.getItem(index);
        final int currentScore = item.getGoal();
        final Dialog dialog = new Dialog(Main.this);
        dialog.setTitle(getResources().getString(R.string.input_score));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.input_score_dialog);
        final EditText editText = (EditText) dialog.findViewById(R.id.scoreInputText);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER){
                    int score = Integer.parseInt(editText.getText().toString());
                    if (score > 200){
                        Toast.makeText(getBaseContext(),"Impossible Score!",Toast.LENGTH_LONG).show();
                        return  false;
                    }

                    if (currentScore < score){
                        Toast.makeText(getBaseContext(),"Bust!",Toast.LENGTH_LONG).show();
                    }
                    else{
                        item.setGoal(currentScore - score);
                    }
                    dialog.dismiss();
                    return true;
                }
                return false;
            }
        });
        dialog.show();
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
                button.setVisibility(View.VISIBLE);
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
        lazyAdapter = new LazyAdapter(this, R.layout.list_row,listViewItems);

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(lazyAdapter);
        ArrayList<String> nickNameList = new ArrayList<>();
        nickNameList.addAll(Arrays.asList(getResources().getStringArray(R.array.nickNames)));

        Random random = new Random();
        int maxIdx = nickNameList.size() - 1;
        for (int i=0; i<SettingModel.getInstance().getPlayersCnt();i++){
            int rIdx = 0;
            if (i<getResources().getInteger(R.integer.seek_bar_max)){
                rIdx = random.nextInt(maxIdx - i);
            }
            lazyAdapter.addItem(null, nickNameList.get(rIdx), SettingModel.getInstance().getGoalScore());
            nickNameList.remove(rIdx);
        }
    }

}
