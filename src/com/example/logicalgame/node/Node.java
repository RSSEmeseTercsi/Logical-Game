package com.example.logicalgame.node;

import android.content.Context;
import android.graphics.Point;
import android.view.View;

/**
 * Created by Greg on 2/24/15.
 */
public class Node
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
        node_owner_dynamic,
        node_owner_empty,
        node_owner_none
    }

    public enum ENodeType
    {
        node_type_addition,
        node_type_subtraction,
        node_type_multiplication,
        node_type_division,
        node_type_none
    }

    private ENodeType mType;
    private ENodeOwner mOwner;
    private ENodeShape mShape;
    private int mSceneRow;

    private int _id;
    private View _attachedView;
    private Point _position;

    public Node(ENodeType type, ENodeOwner owner, ENodeShape shape)
    {
        this.mType = type;
        this.mShape = shape;
        this.mOwner = owner;
        this.mSceneRow = 0;
    }

    public Node(int id, int layer, ENodeShape shape, ENodeOwner owner, ENodeType type, String value)
    {
        this._id = id;
        this.mSceneRow = layer;
        this.mShape = shape;
        this.mOwner = owner;
        this.mType = type;

        //TODO: handle values
    }

    public Node(int row)
    {
        this.mType = ENodeType.node_type_none;
        this.mShape = ENodeShape.node_shape_none;
        this.mOwner = ENodeOwner.node_owner_none;
        this.mSceneRow = row;
    }

    public void setType(ENodeType type)
    {
        this.mType = type;
    }

    public void setOwner(ENodeOwner owner)
    {
        this.mOwner = owner;
    }

    public ENodeOwner getOwner()
    {
        return this.mOwner;
    }

    public void setShape(ENodeShape shape)
    {
        this.mShape = shape;
    }

    public void setRow(int row){ this.mSceneRow = row; }
    public int getRow()
    {
        return this.mSceneRow;
    }

    public void attachView(View view)
    {
        this._attachedView = view;
    }
    public View getAttachedView()
    {
        return this._attachedView;
    }

    public void setID(int id)
    {
        this._id = id;
    }
    public int getID()
    {
        return this._id;
    }

    public void setPosition(Point position)
    {
        this._position = position;
    }
    public Point getPosition()
    {
        return _position;
    }
}
