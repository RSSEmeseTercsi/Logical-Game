package com.redsteedstudios.logicalgame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.redsteedstudios.logicalgame.util.FileUtils;

import java.util.ArrayList;

/**
 * Created by Emese Tercsi on 2015.02.23..
 */
public class LevelChooserActivity extends Activity implements AdapterView.OnItemClickListener {

    public static int MAX_LEVEL_NUM = 25;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_level_chooser);

        TextView levelNumsTextView = (TextView) findViewById(R.id.unblocked_and_sum_levelnums);
        levelNumsTextView.setText("1/" + MAX_LEVEL_NUM);

        bindLevelChooserLisView();

        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonClicked();
            }
        });
    }

    public void bindLevelChooserLisView(){
        ListView levelListView = (ListView) findViewById(R.id.level_list_view);
        LevelChooserAdapter arrayAdapter;
        //ArrayList<String> list = new ArrayList<String>();
        //for (int i = 1; i <= MAX_LEVEL_NUM; i++) {
        //    list.add("Level " + i);
        //}
        ArrayList<String> list = FileUtils.getInstance().getLevelJSONLocations(this);

        arrayAdapter = new LevelChooserAdapter(this, list);
        levelListView.setAdapter(arrayAdapter);
        levelListView.setOnItemClickListener(this);
    }


    public void backButtonClicked(){
        this.finish();
        onBackPressed();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0){
            //available level
            Log.e("pos",""+position);
        }else{
            //blocked levels
            Log.e("pos2",""+position);
        }
    }
}