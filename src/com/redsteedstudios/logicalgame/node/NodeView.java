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
import org.w3c.dom.Text;

/**
 * Created by Greg on 2/25/15.
 */
public class NodeView extends LinearLayout
{
    private TextView _view;
    private Node _node;
    private Node _attachedNode;
    public String _testString = "node";

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

        if (this._view != null)
        {
            this.removeView(this._view);
            this._view = null;
        }

        this._view = new TextView(this.context);
        this._view.setWidth(75);
        this._view.setHeight(75);
        this._view.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        this._view.setTextSize(8);
        this._view.setTextColor(Color.RED);
        this._view.setText("" + _node.getID());

        if (this._attachedNode == null) {
            if (_node.getOwner() == Node.ENodeOwner.node_owner_static)
            {
                this._view.setBackgroundColor(Color.RED);
            }
            else
            {
                this._view.setBackgroundColor(Color.WHITE);
            }
        }
        else
        {
            this._view.setBackgroundColor(Color.GREEN);
        }

        this.addView(this._view);
    }

    public TextView getView()
    {
        return _view;
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
