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

    public void recreateNodeView()
    {
        this.setPadding(20, 0, 20, 0);

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

        if (_attachedNode != null)
        {
            this._view.setBackgroundResource(_attachedNode.getNodeShapeID(true));
            this._view.setText(_attachedNode.getNodeDisplayValue(true));
        }
        else
        {
            this._view.setBackgroundResource(_node.getNodeShapeID(false));
            this._view.setText(_node.getNodeDisplayValue(false));
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
        recreateNodeView();
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
                    recreateNodeView();
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
                    recreateNodeView();
                }
            } break;
            default: break;
        }
    }
}
