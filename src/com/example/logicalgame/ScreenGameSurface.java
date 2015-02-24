package com.example.logicalgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.example.logicalgame.data.DirectedGraph;
import com.example.logicalgame.data.LineData;
import com.example.logicalgame.node.Node;
import com.example.logicalgame.node.NodeSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Greg on 2/24/15.
 * This will hold the lines connecting the nodes.
 */
public class ScreenGameSurface extends View
{
    private Paint mPaint;
    private List<LineData> mLineData;
    private List<LineData> mConnectedLineData;

    public ScreenGameSurface(Context context, AttributeSet attrSet)
    {
        super(context, attrSet);

        this.mPaint = new Paint();
        this.mPaint.setColor(Color.WHITE);
        this.mPaint.setStrokeWidth(4f);
        this.mPaint.setTextSize(48.0f);

        this.mLineData = new ArrayList<LineData>();
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if (this.mConnectedLineData != null && !this.mConnectedLineData.isEmpty())
        {
            for (LineData line :this.mConnectedLineData)
            {
                this.mPaint.setColor(Color.WHITE);
                canvas.drawLine(line.x1, line.y1, line.x2, line.y2, this.mPaint);
                canvas.drawCircle(line.x1, line.y1, 5.0f, this.mPaint);
                this.mPaint.setColor(Color.RED);
                canvas.drawCircle(line.x2, line.y2, 7.0f, this.mPaint);
            }
        }
    }

    public void createRelations(NodeSet nodeSet)
    {
        this.mLineData = new ArrayList<LineData>();
        this.mConnectedLineData = new ArrayList<LineData>();

        for (Node baseNode : nodeSet.getNodes()) {
            int baseLocation[] = new int[2];
            baseNode.getAttachedView().getLocationOnScreen(baseLocation);
            baseLocation[0] += baseNode.getAttachedView().getWidth() / 2;

            List<Node> neighborNodes = nodeSet.getNodeGraph().getNeighborsForNode(baseNode);
            for (Node neighborNode : neighborNodes)
            {
                int neighborLocation[] = new int[2];
                neighborNode.getAttachedView().getLocationOnScreen(neighborLocation);
                neighborLocation[0] += neighborNode.getAttachedView().getWidth() / 2;

                //and we create the line data for the relation
                this.mConnectedLineData.add(new LineData(baseLocation, neighborLocation));
            }
        }
        this.invalidate();
    }
}
