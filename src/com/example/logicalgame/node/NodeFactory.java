package com.example.logicalgame.node;

import android.content.Context;
import android.graphics.Rect;

/**
 * Created by Greg on 2/24/15.
 */
public class NodeFactory
{
    public static Node buildNode(Context context, Node.ENodeShape shape, Rect dimens)
    {
        return new Node(context); //nope
    }
}
