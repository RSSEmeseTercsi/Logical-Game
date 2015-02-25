package com.example.logicalgame.node;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.logicalgame.R;

import java.util.ArrayList;

/**
 * Created by Greg on 2/25/15.
 */
public class NodeViewAdapter extends ArrayAdapter<NodeView>
{
    LayoutInflater inflater;
    ArrayList<NodeView> nodeView;

    public NodeViewAdapter(Context context, ArrayList<NodeView> nodeViews) {
        super(context, 0, nodeViews);
        this.nodeView = nodeViews;
    }

    public View getView(final int position, View convertView,ViewGroup parent)
    {
        return nodeView.get(position);
    }
}