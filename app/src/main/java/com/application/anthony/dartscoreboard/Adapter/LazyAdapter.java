package com.application.anthony.dartscoreboard.Adapter;

import com.application.anthony.dartscoreboard.ListViewItem;
import com.application.anthony.dartscoreboard.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by anthony on 2016. 11. 23..
 */

public class LazyAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<ListViewItem> data;
    private int layout;

    public LazyAdapter(Context context, int layout, ArrayList<ListViewItem> data) {
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public Object getItem(int position){
        return data.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView = inflater.inflate(layout, parent, false);
        }

        ListViewItem listViewItem = data.get(position);

        ImageView icon = (ImageView) convertView.findViewById(R.id.list_image);
        TextView nick = (TextView) convertView.findViewById(R.id.nick);
        TextView score = (TextView) convertView.findViewById(R.id.score);

        icon.setImageDrawable(listViewItem.getIcon());
        nick.setText(listViewItem.getNickName());
        score.setText(listViewItem.getGoal());

        return convertView;
    }

    public void addItem(Drawable icon, String nickName, String goal){
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setNickName(nickName);
        item.setGoal(goal);

        data.add(item);
    }
}
