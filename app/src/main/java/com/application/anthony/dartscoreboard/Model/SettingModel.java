package com.application.anthony.dartscoreboard.Model;


/**
 * Created by anthony on 2016. 11. 17..
 */

public class SettingModel{

    private static SettingModel instance;
    private int playersCnt = 0;
    private int goalScore = 0;


    private SettingModel() {}

    public static synchronized SettingModel getInstance (){
        if (instance == null){
            instance = new SettingModel();
        }
        return instance;
    }

    public void setGoalScore(int goalScore) {
        this.goalScore = goalScore;
    }

    public void setPlayersCnt(int playersCnt) {
        this.playersCnt = playersCnt;
    }

    public int getGoalScore() {
        return goalScore;
    }

    public int getPlayersCnt() {
        return playersCnt;
    }
}
