package com.example.logicalgame.node;

import android.content.Context;
import android.view.View;

/**
 * Created by Greg on 2/24/15.
 */
public class Node extends View
{
    public enum ENodeShape
    {
        node_shape_square,
        node_shape_circle,
        node_shape_none
    }

    public enum ENodeOwner
    {
        node_owner_static,
        node_owner_user
    }

    public enum ENodeType
    {
        node_type_addition,
        node_type_subtraction,
        node_type_multiplication,
        node_type_division
    }

    ENodeType  mType;
    ENodeOwner mOwner;
    ENodeShape mShape;

    public Node(Context context, ENodeType type, ENodeOwner owner, ENodeShape shape)
    {
        super(context);
        this.mType= type;
        this.mShape = shape;
        this.mOwner = owner;

     //   buildNode();
    }
}
