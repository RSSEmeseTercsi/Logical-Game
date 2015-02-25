package com.example.logicalgame.node;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Greg on 2/25/15.
 */
public class NodeView extends LinearLayout
{
    private View _view;
    private Node _node;
    private Node _attachedNode;

    private Context context;

    public NodeView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
    }

    public Node getNode()
    {
        return this._node;
    }

    public Node getAttachedNode()
    {
        return this._attachedNode;
    }

    public void recreateNodeView(boolean attached)
    {
        //based on the attr, we need create new a view
        //todo: move the view out, and remove the previous one if there is any

        Button btn = new Button(this.context);
        btn.setWidth(75);
        btn.setHeight(75);
        btn.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        btn.setTextSize(8);
        btn.setText("" + _node.getID());

        if (!attached) {
            if (_node.getOwner() == Node.ENodeOwner.node_owner_static)
            {
                btn.setBackgroundColor(Color.RED);
            }
            else
            {
                btn.setBackgroundColor(Color.WHITE);
            }
        }

        this.addView(btn);
    }

    public void bindNode(Node node)
    {
        this._node = node;
        //set the owner if static
        recreateNodeView(false);
    }

    public boolean bindAttach(Node attached)
    {
        boolean retVal = false;
        switch(this._node.getOwner())
        {
            case node_owner_static:
            {
                retVal = false;
            } break;
            case node_owner_dynamic:
            {
                retVal = false;
            } break;
            case node_owner_empty:
            {
                this._attachedNode = attached;
                recreateNodeView(true);
                retVal = true;
            } break;
        }
        return retVal;
    }
}
