package com.application.anthony.dartscoreboard;

import android.graphics.drawable.Drawable;

/**
 * Created by anthony on 2016. 11. 23..
 */

public class ListViewItem {
    private Drawable icon;
    private String nickName;
    private String goal;

    public void setIcon(Drawable icon){
        this.icon = icon;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public void setGoal(String goal){
        this.goal = goal;
    }

    public String getGoal() {
        return goal;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getNickName() {
        return nickName;
    }
}
