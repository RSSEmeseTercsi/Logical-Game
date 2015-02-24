package com.example.logicalgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Greg on 2/24/15.
 */
public class ScreenGame extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_game);

        bindAdapterToSelectionView();

        for (int c = 0; c < 4; c++)
            addRandomShit();

        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonClicked();
            }
        });
    }

    /* We should bind the available noes in this function
     * Note: It won't necessarily have a textview attached to it. */
    private void bindAdapterToSelectionView()
    {
        ListView levelListView = (ListView) findViewById(R.id.game_node_selection_view);
        ArrayAdapter arrayAdapter;

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            list.add("Node " + i);
        }
        arrayAdapter = new ArrayAdapter(this, R.layout.screen_game_picker_item, R.id.game_available_node, list);

        levelListView.setAdapter(arrayAdapter);
    }

    public void addRandomShit()
    {
        int randomRow = new Random().nextInt(5);
        Button btn = new Button(this);
        btn.setWidth(75);
        btn.setHeight(75);
        btn.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        btn.setText("R"+ randomRow);
        getGameRow(randomRow).addView(btn);
    }

    //starts with 0
    public LinearLayout getGameRow(int index)
    {
        LinearLayout retVal = null;
        switch (index)
        {
            case 0:
            {
                retVal = (LinearLayout)findViewById(R.id.game_row1);
            } break;
            case 1:
            {
                retVal = (LinearLayout)findViewById(R.id.game_row2);
            } break;
            case 2:
            {
                retVal = (LinearLayout)findViewById(R.id.game_row3);
            } break;
            case 3:
            {
                retVal = (LinearLayout)findViewById(R.id.game_row4);
            } break;
            case 4:
            {
                retVal = (LinearLayout)findViewById(R.id.game_row5);
            } break;
            case 5:
            {
                retVal = (LinearLayout)findViewById(R.id.game_row6);
            } break;
            case 6:
            {
                retVal = (LinearLayout)findViewById(R.id.game_row7);
            } break;
            default: retVal = (LinearLayout)findViewById(R.id.game_row5); break;
        }
        return retVal;
    }

    public void backButtonClicked(){
        this.finish();
        onBackPressed();
    }
}
