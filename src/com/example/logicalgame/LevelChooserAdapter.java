package com.example.logicalgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by e on 2015.02.24..
 */
public class LevelChooserAdapter extends BaseAdapter {

    private ArrayList<String> levelNumArrayList ;
    Activity context;

    public LevelChooserAdapter(Activity context,ArrayList<String> levelNums){
        levelNumArrayList = levelNums;
        this.context = context;
    }

    @Override
    public int getCount() {
        return levelNumArrayList.size();
    }

    @Override
    public String getItem(int position) {
        return levelNumArrayList.get(position);
    }

    @Override
        public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.level_chooser_item, null);
            viewHolder = new ViewHolder();
            viewHolder.levelNumTextView = (TextView) convertView.findViewById(R.id.level_num);
            convertView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.levelNumTextView.setText(levelNumArrayList.get(position));

        if(position != 0){
            convertView.setClickable(false);
            convertView.setFocusable(false);
            convertView.setEnabled(false);
            convertView.setBackgroundColor(Color.GRAY);
        }else{
            convertView.setBackgroundColor(Color.BLACK);
        }

        return convertView;
    }


    private class ViewHolder {
        TextView levelNumTextView;
    }

}
