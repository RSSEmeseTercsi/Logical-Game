package com.example.logicalgame.data;

import android.util.Log;

/**
 * Created by Greg on 2/24/15.
 */
public class LineData
{
    public float x1;
    public float x2;
    public float y1;
    public float y2;

    public LineData()
    {
        x1 = 0.0f;
        x2 = 0.0f;
        y1 = 0.0f;
        y2 = 0.0f;
    }

    public LineData(int[] parent, int[] child)
    {
        x1 = parent[0];
        x2 = child[0];
        y1 = parent[1];
        y2 = child[1];

        Log.d("LineData", "x1: " + x1 + "  y1: " + y1);
        Log.d("LineData", "x2: " + x2 + "  y2: " + y2);
    }
}
