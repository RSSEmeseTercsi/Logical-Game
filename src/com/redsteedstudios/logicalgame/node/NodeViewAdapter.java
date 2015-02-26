package com.redsteedstudios.logicalgame.node;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.redsteedstudios.logicalgame.R;

import java.util.ArrayList;

/**
 * Created by Greg on 2/25/15.
 */
public class NodeViewAdapter extends ArrayAdapter<Node>
{
    LayoutInflater inflater;
    ArrayList<Node> nodes;
    Activity context;

    public NodeViewAdapter(Context context, ArrayList<Node> nodeViews) {
        super(context, 0, nodeViews);
        this.nodes = nodeViews;
    }

    public View getView(final int position, View convertView,ViewGroup parent)
    {
        NodeView nodeView = new NodeView(getContext(), null);
        nodeView.bindNode(this.nodes.get(position));
        nodeView.bindAttach(this.nodes.get(position));
        nodeView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));
        return nodeView;
    }
}