package com.application.anthony.dartscoreboard;

import android.graphics.drawable.Drawable;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by anthony on 2016. 11. 23..
 */

public class ListViewItem {
    private Drawable icon;
    private String nickName;
    private int goal;

    public void setIcon(Drawable icon){
        this.icon = icon;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public void setGoal(int goal){
        this.goal = goal;
    }

    public int getGoal() {
        return goal;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getNickName() {
        return nickName;
    }

    public static Comparator<ListViewItem> SCORE_COMPARATOR = new Comparator<ListViewItem>() {
        private final Collator collator = Collator.getInstance();
        @Override
        public int compare(ListViewItem o1, ListViewItem o2) {
            String score1 = String.valueOf(o1.getGoal());
            String score2 = String.valueOf(o2.getGoal());
            String tmp1 = "";
            String tmp2 = "";

            if(o1.getGoal() < 5){
                tmp1 = tmp1 + "0";
            }
            if(o2.getGoal() < 5){
                tmp2 = tmp2 + "0";
            }
            return collator.compare(score1+tmp1, score2+tmp2);
        }
    };
}
