package com.redsteedstudios.logicalgame.node;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        TextView btn = new TextView(this.context);
        btn.setWidth(75);
        btn.setHeight(75);
        btn.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        btn.setTextSize(8);
        btn.setTextColor(Color.RED);
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
                if (this._attachedNode == null)
                {
                    this._attachedNode = attached;
                    recreateNodeView(true);
                    retVal = true;
                }
                else
                {

                    retVal = false;
                }
            } break;
        }

        Log.e("retval", " " +retVal);
        return retVal;
    }
}
