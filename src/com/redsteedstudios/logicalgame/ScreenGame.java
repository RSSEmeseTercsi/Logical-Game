package com.redsteedstudios.logicalgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.redsteedstudios.logicalgame.node.Node;
import com.redsteedstudios.logicalgame.node.NodeSet;
import com.redsteedstudios.logicalgame.node.NodeView;
import com.redsteedstudios.logicalgame.node.NodeViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greg on 2/24/15.
 */
public class ScreenGame extends Activity
{
    private NodeSet nodeSet;

    private List<NodeView> nodeViews;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_game);

        this.nodeViews = new ArrayList<NodeView>();

        this.nodeSet = new NodeSet();
        this.nodeSet.loadLevel(this, "level_1.json"); //load the level here

        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonClicked();
            }
        });

        bindNodeSelector();
        bindNodeSet();
    }

    /* We should bind the available noes in this function
     * Note: It won't necessarily have a textview attached to it. */
    private void bindNodeSelector()
    {
        //todo select the dynamic nodes, and add them to this list

        ListView levelListView = (ListView) findViewById(R.id.game_node_selection_view);
        ArrayAdapter arrayAdapter;

        //dynamics
        ArrayList<NodeView> dynamicNodes = new ArrayList<NodeView>();
        for (Node node : this.nodeSet.getNodes())
        {
            if (node.getOwner() == Node.ENodeOwner.node_owner_dynamic)
            {
                NodeView nodeView = new NodeView(this, null);
                nodeView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                nodeView.bindNode(node);

                dynamicNodes.add(nodeView);
            }
        }

        levelListView.setAdapter(new NodeViewAdapter(this.getBaseContext(), dynamicNodes));
    }

    public void bindNodeSet()
    {
        int c = 0;
        for (Node node : nodeSet.getNodes())
        {
            NodeView nodeView = new NodeView(this, null);
            nodeView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            nodeView.bindNode(node);
            node.attachView(nodeView);

            getGameRow(node.getRow()).addView(nodeView);
            c++;
        }
    }

    /***
     * Returns the LinearLayout at the specified index
     * @param index
     * @return
     */
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            ((ScreenGameSurface)findViewById(R.id.game_surface)).createRelations(nodeSet);
        }
    }

    public void backButtonClicked(){
        this.finish();
        onBackPressed();
    }
}
