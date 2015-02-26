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
import com.redsteedstudios.logicalgame.R;
import org.w3c.dom.Text;

/**
 * Created by Greg on 2/25/15.
 */
public class NodeView extends LinearLayout
{
    private TextView _view;
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
        this._view.setTextColor(Color.BLACK);

        if (this._attachedNode == null) {
            if (_node.getOwner() == Node.ENodeOwner.node_owner_static)
            {
                this._view.setBackgroundResource(R.drawable.node_static_shape);
                this._view.setText("" + _node.getID());
            }
            else
            {
                this._view.setBackgroundResource(R.drawable.node_empty_shape);
            }
        }
        else
        {
            this._view.setBackgroundResource(R.drawable.node_placed_shape);
            this._view.setText("" + _node.getID());
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

        return retVal;
    }

    public void removeAttachment()
    {
        switch(this._node.getOwner())
        {
            case node_owner_dynamic: {
                if (this._attachedNode != null)
                {
                    this._attachedNode = null;
                    recreateNodeView(false);
                }
            } break;
            default: break;
        }
    }
}
