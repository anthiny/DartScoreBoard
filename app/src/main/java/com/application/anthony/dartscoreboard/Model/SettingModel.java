package com.application.anthony.dartscoreboard.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anthony on 2016. 11. 17..
 */

public class SettingModel implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.playersCnt);
        dest.writeInt(this.goalScore);
    }

    protected SettingModel(Parcel in) {
        this.playersCnt = in.readInt();
        this.goalScore = in.readInt();
    }

    public static final Parcelable.Creator<SettingModel> CREATOR = new Parcelable.Creator<SettingModel>() {
        @Override
        public SettingModel createFromParcel(Parcel source) {
            return new SettingModel(source);
        }

        @Override
        public SettingModel[] newArray(int size) {
            return new SettingModel[size];
        }
    };
}
