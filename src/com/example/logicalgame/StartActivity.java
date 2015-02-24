package com.example.logicalgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_start);

        Button settings = (Button) findViewById(R.id.settings_button);
        Button newButton = (Button) findViewById(R.id.new_game_btn);

        newButton.setOnClickListener(this);
        settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.settings_button:
               startActivity(new Intent(this,ScreenSettings.class));
                break;
            case R.id.new_game_btn:
                startActivity(new Intent(this,LevelChooserActivity.class));
                break;
            default:
                break;
        }
    }
}
