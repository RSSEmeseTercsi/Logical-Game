package com.example.logicalgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.logicalgame.data.DirectedGraph;
import com.example.logicalgame.node.NodeSet;

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
        Button continueButton = (Button) findViewById(R.id.continue_btn);

        newButton.setOnClickListener(this);
        settings.setOnClickListener(this);
        continueButton.setOnClickListener(this);

        createDummyGraph();
    }

    private void createDummyGraph() {
      /*  //DUMMMY
        DirectedGraph<String> graph = new DirectedGraph<String>();

        graph.add("#1","#2");
        graph.add("#1","#3");
        graph.add("#2","#4");
        graph.add("#3","#4");
        graph.add("#4","#5");
        graph.add("#6","#5");

        Log.d("GRAPH", graph.toString());
        */
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
            case R.id.continue_btn:
                startActivity(new Intent(this, ScreenGame.class));
                break;
            default:
                break;
        }
    }
}
