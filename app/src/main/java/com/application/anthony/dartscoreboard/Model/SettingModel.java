package com.application.anthony.dartscoreboard.Model;

/**
 * Created by anthony on 2016. 11. 17..
 */

public class SettingModel {
    private int playersCnt = 0;
    private int goalScore = 0;


    public SettingModel(int playersCnt, int goalScore) {
        this.playersCnt = playersCnt;
        this.goalScore = goalScore;
    }

    public int getGoalScore() {
        return goalScore;
    }

    public int getPlayersCnt() {
        return playersCnt;
    }
}
