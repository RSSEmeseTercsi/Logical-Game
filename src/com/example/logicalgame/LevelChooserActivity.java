package com.example.logicalgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by Emese Tercsi on 2015.02.23..
 */
public class LevelChooserActivity extends Activity {

    public static int MAX_LEVEL_NUM = 25;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_level_chooser);

        TextView levelNumsTextView = (TextView) findViewById(R.id.unblocked_and_sum_levelnums);
        levelNumsTextView.setText("1/" + MAX_LEVEL_NUM);

        bindLevelCooserLisView();

        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonClicked();
            }
        });
    }

    public void bindLevelCooserLisView(){

        ListView levelListView = (ListView) findViewById(R.id.level_list_view);
        ArrayAdapter arrayAdapter;

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i <= MAX_LEVEL_NUM; i++) {
            list.add("Level " + i);
        }
        arrayAdapter = new ArrayAdapter(this,R.layout.level_chooser_item, R.id.level_num, list);

        levelListView.setAdapter(arrayAdapter);
    }

    public void backButtonClicked(){
        this.finish();
        onBackPressed();
    }

}