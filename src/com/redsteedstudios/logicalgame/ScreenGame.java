package com.redsteedstudios.logicalgame;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
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
public class ScreenGame extends Activity implements AdapterView.OnItemLongClickListener {
    private NodeSet nodeSet;

    private List<NodeView> nodeViews;
    private Node actualNode;
    private ArrayList<Node> dynamicNodes;
    private int countOfDynamicNodes;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_game);

        Bundle extras = this.getIntent().getExtras();
        String level = null;
        if (extras != null) {
            level = extras.getString("selected_level");
        }

        this.nodeViews = new ArrayList<NodeView>();

        this.nodeSet = new NodeSet();
        this.nodeSet.loadLevel(this, (level == null ? "level_1.json" : level)); //load the level here

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
    private void bindNodeSelector() {
        //todo select the dynamic nodes, and add them to this list

        ListView levelListView = (ListView) findViewById(R.id.game_node_selection_view);
        ArrayAdapter arrayAdapter;

        //dynamics
        dynamicNodes = new ArrayList<Node>();
        for (Node node : this.nodeSet.getNodes()) {
            if (node.getOwner() == Node.ENodeOwner.node_owner_dynamic) {
                dynamicNodes.add(node);
            }
        }

        levelListView.setAdapter(new NodeViewAdapter(this.getBaseContext(), dynamicNodes));
        levelListView.setOnItemLongClickListener(this);
    }

    public void bindNodeSet() {
        countOfDynamicNodes = 0;
        for (Node node : nodeSet.getNodes()) {
            NodeView nodeView = new NodeView(this, null);
            nodeView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            nodeView.bindNode(node);
            nodeView.setOnDragListener(new myDragEventListener());
            node.attachView(nodeView);
            nodeViews.add(nodeView);

            getGameRow(node.getRow()).addView(nodeView);
            if (node.getOwner() == (Node.ENodeOwner.node_owner_dynamic))
                countOfDynamicNodes++;
        }
    }

    /**
     * Returns the LinearLayout at the specified index
     *
     * @param index
     * @return
     */
    public LinearLayout getGameRow(int index) {
        LinearLayout retVal = null;
        switch (index) {
            case 0: {
                retVal = (LinearLayout) findViewById(R.id.game_row1);
            }
            break;
            case 1: {
                retVal = (LinearLayout) findViewById(R.id.game_row2);
            }
            break;
            case 2: {
                retVal = (LinearLayout) findViewById(R.id.game_row3);
            }
            break;
            case 3: {
                retVal = (LinearLayout) findViewById(R.id.game_row4);
            }
            break;
            case 4: {
                retVal = (LinearLayout) findViewById(R.id.game_row5);
            }
            break;
            case 5: {
                retVal = (LinearLayout) findViewById(R.id.game_row6);
            }
            break;
            case 6: {
                retVal = (LinearLayout) findViewById(R.id.game_row7);
            }
            break;
            default:
                retVal = (LinearLayout) findViewById(R.id.game_row5);
                break;
        }
        return retVal;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            ((ScreenGameSurface) findViewById(R.id.game_surface)).createRelations(nodeSet);
        }
    }

    public void backButtonClicked() {
        this.finish();
        onBackPressed();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        actualNode = dynamicNodes.get(position);
        view.startDrag(data, shadowBuilder, view, 0);
        return true;
    }

    protected class myDragEventListener implements View.OnDragListener {

        public myDragEventListener() {
            super();

        }

        public boolean onDrag(View v, DragEvent event) {

            NodeView nodeView = (NodeView) v;
            final int action = event.getAction();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    return true;
                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;
                case DragEvent.ACTION_DROP:

                    nodeViews.remove(nodeView.getNode().getID());
                    nodeView.bindAttach(actualNode);
                    nodeViews.add(nodeView.getNode().getID(), nodeView);

                    countOfDynamicNodes--;
                    if (countOfDynamicNodes == 0) {
                        if (validate(nodeViews)) {
                            Toast.makeText(ScreenGame.this, "WIN", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(ScreenGame.this, "Lose", Toast.LENGTH_SHORT).show();

                    }

                    return true;
                case DragEvent.ACTION_DRAG_ENTERED:
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    return true;
                default:
                    break;
            }
            return false;
        }
    }

    public boolean validate(List<NodeView> nodes) {
        boolean result = true;

        for (NodeView nodeView : nodes) {
            if (nodeView.getAttachedNode() != null) {
                if (!(nodeView.getNode().getID() == nodeView.getAttachedNode().getID())) {
                    result = false;
                    break;
                }
            }
        }

        return result;

    }
}
