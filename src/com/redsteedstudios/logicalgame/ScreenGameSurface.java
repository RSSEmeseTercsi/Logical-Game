package com.redsteedstudios.logicalgame;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import com.redsteedstudios.logicalgame.data.LineData;
import com.redsteedstudios.logicalgame.node.Node;
import com.redsteedstudios.logicalgame.node.NodeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greg on 2/24/15.
 * This will hold the lines connecting the nodes.
 */
public class ScreenGameSurface extends View
{
    private final boolean DEBUG = false;
    private Paint mPaint;
    private List<LineData> mLineData;
    private List<LineData> mConnectedLineData;

    //should be loaded based on the level we are creating the view for
    private Bitmap mBackground;
    private Bitmap mSpaceShip;

    public ScreenGameSurface(Context context, AttributeSet attrSet)
    {
        super(context, attrSet);

        this.mPaint = new Paint();
        this.mPaint.setColor(Color.WHITE);
        this.mPaint.setStrokeWidth(4f);
        this.mPaint.setTextSize(48.0f);

        this.mBackground = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_1);
        this.mSpaceShip = BitmapFactory.decodeResource(context.getResources(), R.drawable.space_ship_1);

        this.mLineData = new ArrayList<LineData>();
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        this.mPaint.setColor(Color.WHITE);
        if (this.mBackground != null)
        {
            canvas.drawBitmap(this.mBackground, 0, 0, this.mPaint);
        }
        if (this.mSpaceShip != null)
        {
            Point canvasOrigin = new Point(canvas.getWidth() / 2, canvas.getHeight() / 2);
            canvas.drawBitmap(this.mSpaceShip, canvasOrigin.x - (this.mSpaceShip.getWidth() / 2),
                                               canvasOrigin.y - (this.mSpaceShip.getHeight() / 2), this.mPaint);
        }

        if (this.mConnectedLineData != null && !this.mConnectedLineData.isEmpty())
        {
            for (LineData line : this.mConnectedLineData)
            {
                canvas.drawLine(line.x1, line.y1, line.x2, line.y2, this.mPaint);
                canvas.drawCircle(line.x2, line.y2, 15.0f, this.mPaint);

                if (DEBUG)
                {
                    canvas.drawCircle(line.x1, line.y1, 5.0f, this.mPaint);
                    this.mPaint.setColor(Color.RED);
                    canvas.drawCircle(line.x2, line.y2, 7.0f, this.mPaint);
                }
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
                //can be null, since there might be some nodes without a connected node
                if (neighborNode != null)
                {
                    int neighborLocation[] = new int[2];
                    neighborNode.getAttachedView().getLocationOnScreen(neighborLocation);
                    neighborLocation[0] += neighborNode.getAttachedView().getWidth() / 2;

                    //and we create the line data for the relation
                    this.mConnectedLineData.add(new LineData(baseLocation, neighborLocation));
                }
            }
        }
        this.invalidate();
    }
}
